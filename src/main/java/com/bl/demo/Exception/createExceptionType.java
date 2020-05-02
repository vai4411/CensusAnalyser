package com.bl.demo.Exception;

import com.opencsv.bean.CsvBindByName;

public class createExceptionType {
    @CsvBindByName(column = "State", required = true)
    public Integer state;
}
