package com.bl.demo.model;

import com.opencsv.bean.CsvBindByName;

public class IndianStatesCSV {

    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

}
