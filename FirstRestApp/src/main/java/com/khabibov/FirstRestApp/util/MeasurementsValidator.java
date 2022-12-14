package com.khabibov.FirstRestApp.util;

import com.khabibov.FirstRestApp.models.Measurements;
import com.khabibov.FirstRestApp.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementsValidator implements Validator {

    private  final SensorsService sensorsService;

    @Autowired
    public MeasurementsValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurements.class.equals((aClass));
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurements measurements = (Measurements) o;

        if(measurements.getSensor() == null){
            return;
        }

        if(sensorsService.findByName(measurements.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "Нет зарегистрированного сенсора с этим именем");
    }
}