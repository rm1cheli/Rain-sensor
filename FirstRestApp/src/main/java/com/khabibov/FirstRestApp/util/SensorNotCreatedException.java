package com.khabibov.FirstRestApp.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String str){
        super(str);
    }
}