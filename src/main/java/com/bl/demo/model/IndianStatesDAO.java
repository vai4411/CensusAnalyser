package com.bl.demo.model;

public class IndianStatesDAO {
    public String stateName;
    public String stateCode;
    public int tin;

    public IndianStatesDAO(IndianStatesCSV indianStatesCSV) {
        stateName = indianStatesCSV.stateName;
        stateCode = indianStatesCSV.stateCode;
        tin = indianStatesCSV.tin;
    }
}
