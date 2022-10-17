package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.services.SensorsService;
import ru.alishev.springcourse.FirstRestApp.util.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorsService sensorsService, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                stringBuilder.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(stringBuilder.toString());
        }

        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResponse> handleException(SensorNotCreatedException e){
        MeasurementsErrorResponse response = new MeasurementsErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResponse> handleException(SensorNotFoundException e){
        MeasurementsErrorResponse response = new MeasurementsErrorResponse(
                "Measurement не был найден",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensor(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}