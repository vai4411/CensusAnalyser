package com.bl.demo;

import com.opencsv.bean.CsvBindByName;

public class IndianStatesCSV {

    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

}
