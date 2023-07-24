package com.roger.researchcenterservice.validator;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ValidationConstants {

    private ValidationConstants() {
    }

    public static final int MAX_VARCHAR255_LENGTH = 255;
    public static final int MIN_TEXT_LENGTH = 4;
    public static final BigDecimal MIN_PRICE = new BigDecimal(0);
    public static final BigDecimal MAX_PRICE = new BigDecimal("99999.99");
    public static final LocalTime MAX_RESEARCH_TIME = LocalTime.of(12,0);
    public static final LocalTime MIN_RESEARCH_TIME = LocalTime.of(0,1);

}
