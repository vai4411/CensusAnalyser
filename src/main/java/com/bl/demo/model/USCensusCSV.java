package com.bl.demo.model;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "HousingDensity", required = true)
    public double density;

    @CsvBindByName(column = "TotalArea", required = true)
    public double area;

    @CsvBindByName(column = "Population", required = true)
    public long population;
}
