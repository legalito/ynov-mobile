package com.formation.velo.service;

import com.formation.velo.model.Pump;
import com.formation.velo.model.Station;

import java.util.List;
import java.util.Optional;

public interface PumpService {

    List<Pump> findAll();
    Optional<Pump> findById(Integer id);
    Pump save(Pump pump);

    void deleteById(Integer id);

    void delete(Pump pump);

    void getRecord();

    Optional<Pump> findByRecordId(String recordId);
}
