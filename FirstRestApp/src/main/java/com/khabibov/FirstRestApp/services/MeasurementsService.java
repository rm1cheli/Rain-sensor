package com.khabibov.FirstRestApp.services;

import com.khabibov.FirstRestApp.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.khabibov.FirstRestApp.models.Measurements;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurements> findAll(){
        return measurementsRepository.findAll();
    }

    public List<Measurements> findByRain(boolean bool){
        return measurementsRepository.findByRain(bool);
    }

    @Transactional
    public void save(Measurements measurements){
        measurements.setSensor(sensorsService.findByName(measurements.getSensor().getName()).get());
        measurements.setTime(LocalDateTime.now());
        measurementsRepository.save(measurements);
    }
}