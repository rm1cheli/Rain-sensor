package ru.alishev.springcourse.FirstRestApp.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull(message = "give the sensor a name")
    @Size(min = 3, max = 100, message = "name must be between 3 and 100 characters")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurements> measurements;

    public Sensor(){}

    public Sensor(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurements> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurements> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurements=" + measurements +
                '}';
    }
}