package com.formation.velo.service.impl;

import com.formation.velo.api.parking.OpenDataNantesParkingClient;
import com.formation.velo.api.parking.OpenDataParkingNantes;
import com.formation.velo.api.pump.OpenDataNantesPumpClient;
import com.formation.velo.api.pump.OpenDataPumpNantes;
import com.formation.velo.model.Parking;
import com.formation.velo.model.Pump;
import com.formation.velo.repository.ParkingRepository;
import com.formation.velo.repository.PumpRepository;
import com.formation.velo.service.ParkingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingServiceImpl(ParkingRepository repository) {
        this.parkingRepository = repository;
    }

    @Override
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Override
    public Optional<Parking> findById(Integer id) {
        return parkingRepository.findById(id);
    }

    @Override
    public Parking save(Parking parking) {
        return parkingRepository.save(parking);
    }

    @Override
    public void deleteById(Integer id) {
        parkingRepository.deleteById(id);
    }

    @Override
    public void delete(Parking parking) {
        parkingRepository.delete(parking);
    }

    @Override
    public void getRecord() {
        String urlBase = "https://data.nantesmetropole.fr/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase).addConverterFactory(GsonConverterFactory.create(gson)).build();

        OpenDataNantesParkingClient client = retrofit.create(OpenDataNantesParkingClient.class);
        Call<OpenDataParkingNantes> openDataParkingNantesCall = client.getRecords();

        try {
            OpenDataParkingNantes openDataParkingNantes = openDataParkingNantesCall.execute().body();

            Arrays.stream(openDataParkingNantes.getRecords()).forEach(record -> {
                Optional<Parking> parking = findByRecordId(record.getRecordId());
                if(parking.isPresent()) {
                    parking.get().setVoitureElectriqueCapacite(record.getField().getVoitureElectriqueCapacite());
                    parking.get().setVoitureCapacite(record.getField().getVoitureCapacite());
                    parking.get().setVeloCapacite(record.getField().getVeloCapacite());
                    parking.get().setMotoCapacite(record.getField().getMotoCapacite());
                    parking.get().setPmrCapacite(record.getField().getPmrCapacite());

                    save(parking.get());
                } else {
                    Parking newParking = Parking.builder()
                            .recordId(record.getRecordId())
                            .nom(record.getField().getNom())
                            .voitureElectriqueCapacite(record.getField().getVoitureElectriqueCapacite())
                            .voitureCapacite(record.getField().getVoitureCapacite())
                            .veloCapacite(record.getField().getVeloCapacite())
                            .motoCapacite(record.getField().getMotoCapacite())
                            .pmrCapacite(record.getField().getPmrCapacite())
                            .accesPMR(record.getField().getAccesPMR())
                            .adresse(record.getField().getAdresse())
                            .telephone(record.getField().getTelephone())
                            .site(record.getField().getSite())
                            .payement(record.getField().getPayement())
                            .longitude(record.getField().getPosition()[1])
                            .lattitude(record.getField().getPosition()[0])
                            .build();

                    save(newParking);
                }
            });
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Parking> findByRecordId(String recordId) {
        return parkingRepository.findByRecordId(recordId);
    }

}
