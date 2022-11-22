package com.formation.velo.service.impl;

import com.formation.velo.api.velo.OpenDataNantesClient;
import com.formation.velo.api.velo.OpenDataVeloNantes;
import com.formation.velo.model.Station;
import com.formation.velo.repository.StationRepository;
import com.formation.velo.service.StationService;
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
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository repository) {
        this.stationRepository = repository;
    }

    @Override
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<Station> findById(Integer id) {
        return stationRepository.findById(id);
    }

    @Override
    public Station save(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public void deleteById(Integer id) {
        stationRepository.deleteById(id);
    }

    @Override
    public void delete(Station station) {
        stationRepository.delete(station);
    }

    @Override
    public void getRecord() {
        String urlBase = "https://data.nantesmetropole.fr/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlBase).addConverterFactory(GsonConverterFactory.create()).build();

        OpenDataNantesClient client = retrofit.create(OpenDataNantesClient.class);
        Call<OpenDataVeloNantes> openDataVeloNantesCall = client.getRecords();

        try {
            OpenDataVeloNantes openDataVeloNantes = openDataVeloNantesCall.execute().body();

            Arrays.stream(openDataVeloNantes.getRecords()).forEach(record -> {
                Optional<Station> station = findByRecordId(record.getRecordId());
                if(station.isPresent()) {
                    if(!station.get().isEstModifier()) {
                        station.get().setStatus(record.getField().getStatus());
                    }
                    station.get().setAvailableBikeStands(record.getField().getAvailableBikeStands());
                    station.get().setAvailableBikes(record.getField().getAvailableBikes());
                    station.get().setBikeStands(record.getField().getBikeStands());
                    station.get().setLongitude(record.getField().getPosition()[1]);
                    station.get().setLattitude(record.getField().getPosition()[0]);
                    save(station.get());
                } else {
                    Station newStation = Station.builder()
                            .recordId(record.getRecordId())
                            .name(record.getField().getName())
                            .adresse(record.getField().getAddress())
                            .status(record.getField().getStatus())
                            .longitude(record.getField().getPosition()[0])
                            .lattitude(record.getField().getPosition()[1])
                            .availableBikes(record.getField().getAvailableBikes())
                            .availableBikeStands(record.getField().getAvailableBikeStands())
                            .bikeStands(record.getField().getBikeStands())
                            .estModifier(false)
                            .build();

                    save(newStation);
                }
            });
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Station> findByRecordId(String recordId) {
        return stationRepository.findByRecordId(recordId);
    }
}
