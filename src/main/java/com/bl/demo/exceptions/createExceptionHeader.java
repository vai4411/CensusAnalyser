package com.bl.demo.exceptions;

import com.opencsv.bean.CsvBindByName;

public class createExceptionHeader {
    @CsvBindByName(column = "State Name", required = true)
    public int state;
}
