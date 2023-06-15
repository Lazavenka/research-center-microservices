package com.roger.microservices.exception;

public class ServiceLayerExceptionCodes {
    private ServiceLayerExceptionCodes(){}

    public static final String INCORRECT_REQUEST = "40000";
    public static final String INCORRECT_CLIENT_ID = "40001";
    public static final String INCORRECT_EQUIPMENT_ID = "40002";
    public static final String INCORRECT_ID = "40003";
    public static final String END_TIME_BEFORE_START_TIME = "40004";
    public static final String INCORRECT_NAME = "40005";
    public static final String INCORRECT_DESCRIPTION = "40006";
    public static final String INCORRECT_PRICE = "40007";
    public static final String INCORRECT_RESEARCH_TIME = "40008";
    public static final String INCORRECT_ADDRESS = "40009";
    public static final String INCORRECT_LOCATION = "40010";
    public static final String EQUIPMENT_EXISTS = "40011";
    public static final String LABORATORY_EXISTS = "40012";
    public static final String NOT_AVAILABLE = "40013";

    public static final String NOT_FOUND_ID = "40401";
    public static final String NOT_FOUND_EQUIPMENT_ID = "40402";
    public static final String NOT_FOUND_LABORATORY_ID = "40403";
    public static final String NOT_FOUND_EQUIPMENT_TYPE_ID = "40404";
    public static final String NOT_FOUND_DEPARTMENT_ID = "40405";
    public static final String NOT_UPDATED_ID = "40406";
    public static final String NOT_FOUND_EQUIPMENT = "40408";
    public static final String NOT_FOUND_ORDERS = "40411";

    public static final String INTERNAL_SERVICE_ERROR = "50099";


}
