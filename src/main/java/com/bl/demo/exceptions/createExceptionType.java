package com.bl.demo.exceptions;

import com.opencsv.bean.CsvBindByName;

public class createExceptionType {
    @CsvBindByName(column = "State", required = true)
    public Integer state;
}
