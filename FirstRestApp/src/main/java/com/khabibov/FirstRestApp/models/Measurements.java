package com.khabibov.FirstRestApp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurements {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature")
    @Min(value = 0)
    @NotNull
    private Double temperature;

    @Column(name = "time")
    @NotNull
    private LocalDateTime time;

    @Column(name = "rain")
    @NotNull
    private Boolean rain;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull
    private Sensor sensor;

    public Measurements() {
    }

    public Measurements(int sensorId, Double temperature, LocalDateTime time) {
        this.temperature = temperature;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "Measurements{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", time=" + time +
                ", rain=" + rain +
                ", sensor=" + sensor +
                '}';
    }
}