package com.bl.demo;

import java.util.ArrayList;

public class IndianCensusAdapter extends CensusAdapter {

    @Override
    public ArrayList loadCVSData(Class csvClass, String csv, String csvFilePath) {
        censusCSVList = super.loadCensusData(csv,csvClass,csvFilePath);
        return censusCSVList;
    }
}