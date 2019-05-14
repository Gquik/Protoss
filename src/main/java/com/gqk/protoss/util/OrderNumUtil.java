package com.gqk.protoss.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderNumUtil {
    public static String createOrderNum(){
        String[] strList = {"A","B","C","D","E","F","G","H","I","J"};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmSSS");
        String oderNum = strList[LocalDate.now().getYear()-2019]+LocalDateTime.now().format(formatter)+((int)(Math.random()*900)+100);
        return oderNum;
    }
}
