package com.bl.demo.dao;

import com.bl.demo.model.IndianStatesCSV;

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
