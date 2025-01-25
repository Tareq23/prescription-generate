package com.tareq23.prescription.util;

import java.time.LocalDate;

public class DataTimeUtil {


    public static LocalDate convertToDateMothYear(LocalDate date){
        return date.withDayOfMonth(1);
    }

}
