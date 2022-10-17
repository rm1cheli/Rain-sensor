package ru.alishev.springcourse.FirstRestApp.util;

public class MeasurementsNotCreatedException extends RuntimeException{
    public MeasurementsNotCreatedException(String string){
        super(string);
    }
}