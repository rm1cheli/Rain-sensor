package com.khabibov.FirstRestApp.controllers;

import com.khabibov.FirstRestApp.util.MeasurementsErrorResponse;
import com.khabibov.FirstRestApp.util.MeasurementsNotCreatedException;
import com.khabibov.FirstRestApp.util.MeasurementsNotFoundException;
import com.khabibov.FirstRestApp.util.MeasurementsValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.khabibov.FirstRestApp.dto.MeasurementsDTO;
import com.khabibov.FirstRestApp.models.Measurements;
import com.khabibov.FirstRestApp.services.MeasurementsService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementsValidator measurementsValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementsValidator measurementsValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementsValidator = measurementsValidator;
    }

    @GetMapping("")
    public List<MeasurementsDTO> show(){
        return measurementsService.findAll().stream().map(this::convertToMeasurementsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int rainyDays(){
        return measurementsService.findByRain(true).size();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                          BindingResult bindingResult){
        measurementsValidator.validate(convertToMeasurements(measurementsDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                stringBuilder.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementsNotCreatedException(stringBuilder.toString());
        }

        measurementsService.save(convertToMeasurements(measurementsDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResponse> handleException(MeasurementsNotCreatedException e){
        MeasurementsErrorResponse response = new MeasurementsErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResponse> handleException(MeasurementsNotFoundException e){
        MeasurementsErrorResponse response = new MeasurementsErrorResponse(
                "Measurement не был найден",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public Measurements convertToMeasurements(MeasurementsDTO measurementsDTO){
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    public MeasurementsDTO convertToMeasurementsDTO(Measurements measurements){
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }
}