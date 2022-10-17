package ru.alishev.springcourse.FirstRestApp.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String str){
        super(str);
    }
}