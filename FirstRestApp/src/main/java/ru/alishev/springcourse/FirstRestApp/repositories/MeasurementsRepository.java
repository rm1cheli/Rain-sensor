package ru.alishev.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements,Integer> {
    public List<Measurements> findByRain(boolean b);
}