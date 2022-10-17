package ru.alishev.springcourse.FirstRestApp.dto;

import ru.alishev.springcourse.FirstRestApp.models.Sensor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MeasurementsDTO {
    private int sensorId;

    @Min(value = 0)
    @NotNull
    private Double temperature;

    @NotNull
    private Sensor sensor;

    @NotNull
    private Boolean rain;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Boolean isRain() {
        return rain;
    }

    public void setRain(Boolean rain) {
        this.rain = rain;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public Boolean getRain() {
        return rain;
    }

    @Override
    public String toString() {
        return "MeasurementsDTO{" +
                "sensorId=" + sensorId +
                ", temperature=" + temperature +
                ", sensor=" + sensor +
                ", rain=" + rain +
                '}';
    }
}