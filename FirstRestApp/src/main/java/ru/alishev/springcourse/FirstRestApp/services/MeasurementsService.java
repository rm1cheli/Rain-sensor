package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasurementsRepository;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementsNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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