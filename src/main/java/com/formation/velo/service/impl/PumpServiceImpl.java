package com.formation.velo.service.impl;

import com.formation.velo.api.pump.OpenDataNantesPumpClient;
import com.formation.velo.api.pump.OpenDataPumpNantes;
import com.formation.velo.model.Pump;
import com.formation.velo.repository.PumpRepository;
import com.formation.velo.service.PumpService;
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
public class PumpServiceImpl implements PumpService {

    private final PumpRepository PumpRepository;

    public PumpServiceImpl(PumpRepository repository) {
        this.PumpRepository = repository;
    }

    @Override
    public List<Pump> findAll() {
        return PumpRepository.findAll();
    }

    @Override
    public Optional<Pump> findById(Integer id) {
        return PumpRepository.findById(id);
    }

    @Override
    public Pump save(Pump pump) {
        return PumpRepository.save(pump);
    }

    @Override
    public void deleteById(Integer id) {
        PumpRepository.deleteById(id);
    }

    @Override
    public void delete(Pump pump) {
        PumpRepository.delete(pump);
    }

    @Override
    public void getRecord() {
        String urlBase = "https://data.economie.gouv.fr/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase).addConverterFactory(GsonConverterFactory.create(gson)).build();

        OpenDataNantesPumpClient client = retrofit.create(OpenDataNantesPumpClient.class);
        Call<OpenDataPumpNantes> openDataPumpNantesCall = client.getRecords();

        try {
            OpenDataPumpNantes openDataPumpNantes = openDataPumpNantesCall.execute().body();

            Arrays.stream(openDataPumpNantes.getRecords()).forEach(record -> {
                Optional<Pump> pump = findByRecordId(record.getRecordId());
                if(pump.isPresent()) {
                    pump.get().setLongitude(record.getField().getPosition()[1]);
                    pump.get().setLattitude(record.getField().getPosition()[0]);
                    pump.get().setDateMaj(record.getField().getDateMaj());
                    pump.get().setPrix(record.getField().getPrix());
                    pump.get().setCarburant(record.getField().getCarburant());

                    save(pump.get());
                } else {
                    Pump newPump = Pump.builder()
                            .recordId(record.getRecordId())
                            .regionName(record.getField().getRegionName())
                            .longitude(record.getField().getPosition()[1])
                            .lattitude(record.getField().getPosition()[0])
                            .prix(record.getField().getPrix())
                            .dateMaj(record.getField().getDateMaj())
                            .adresse(record.getField().getAdresse())
                            .ville(record.getField().getVille())
                            .carburant(record.getField().getCarburant())
                            .build();

                    save(newPump);
                }
            });
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Pump> findByRecordId(String recordId) {
        return PumpRepository.findByRecordId(recordId);
    }
}
