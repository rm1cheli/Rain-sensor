package com.khabibov.FirstRestApp.services;

import com.khabibov.FirstRestApp.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.khabibov.FirstRestApp.models.Sensor;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Optional<Sensor> findByName(String name){
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor){
        sensorsRepository.save(sensor);
    }
}