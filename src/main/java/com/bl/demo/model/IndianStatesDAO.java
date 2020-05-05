package com.bl.demo.model;

public class IndianStatesDAO {
    public String stateName;
    public String stateCode;
    public int srNO;
    public int tin;

    public IndianStatesDAO(IndianStatesCSV indianStatesCSV) {
        stateName = indianStatesCSV.stateName;
        stateCode = indianStatesCSV.stateCode;
        srNO = indianStatesCSV.srNo;
        tin = indianStatesCSV.tin;
    }
}
