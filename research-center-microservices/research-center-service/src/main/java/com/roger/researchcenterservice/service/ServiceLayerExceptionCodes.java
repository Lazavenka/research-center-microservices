package com.roger.researchcenterservice.service;

public class ServiceLayerExceptionCodes {



    private ServiceLayerExceptionCodes() {
    }

    public static final String INCORRECT_REQUEST = "40000";
    public static final String INCORRECT_NAME = "40001";
    public static final String INCORRECT_DESCRIPTION = "40002";
    public static final String INCORRECT_PRICE = "40003";
    public static final String INCORRECT_ID = "40004";
    public static final String INCORRECT_RESEARCH_TIME = "40005";
    public static final String INCORRECT_ADDRESS = "40006";
    public static final String INCORRECT_LOCATION = "40007";
    public static final String EQUIPMENT_EXISTS = "40008";
    public static final String LABORATORY_EXISTS = "40009";

    public static final String NOT_FOUND_EQUIPMENT_ID = "40401";
    public static final String NOT_FOUND_LABORATORY_ID = "40402";
    public static final String NOT_FOUND_EQUIPMENT_TYPE_ID = "40403";
    public static final String NOT_FOUND_DEPARTMENT_ID = "40404";

    public static final String NOT_UPDATED_ID = "40407";
    public static final String NOT_FOUND_EQUIPMENT = "40408";
    public static final String INTERNAL_SERVICE_ERROR = "50099";
}
