package com.roger.orderservice.service;

public class ServiceLayerExceptionCodes {

    private ServiceLayerExceptionCodes() {
    }

    public static final String INCORRECT_CLIENT_ID = "40001";
    public static final String INCORRECT_EQUIPMENT_ID = "40002";
    public static final String END_TIME_BEFORE_START_TIME = "40004";
    public static final String NOT_AVAILABLE = "40011";
    public static final String NOT_FOUND_ID = "40401";
    public static final String NOT_UPDATED_ID = "40404";
    public static final String NOT_FOUND_EQUIPMENT = "40405";
    public static final String INTERNAL_SERVICE_ERROR = "50099";
}
