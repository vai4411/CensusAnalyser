package com.bl.demo.model;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "StateId", required = true)
    public String StateId;

    @CsvBindByName(column = "Population", required = true)
    public long areaInSqKm;
}
