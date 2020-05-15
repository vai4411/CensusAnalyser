package com.bl.demo.enums;

import com.bl.demo.exceptions.createExceptionHeader;
import com.bl.demo.exceptions.createExceptionType;
import com.bl.demo.model.IndiaCensusCSV;
import com.bl.demo.model.IndianStatesCSV;
import com.bl.demo.model.USCensusCSV;

public enum AnalyserClassSet {
    IndianCensus(IndiaCensusCSV.class),
    USCensus(USCensusCSV.class),
    IndianState(IndianStatesCSV.class),
    CreateExceptionType(createExceptionType.class),
    CreateExceptionHeader(createExceptionHeader.class);

    private Class className;

    AnalyserClassSet(Class className) {
        this.className = className;
    }

    public Class getClassName() {
        return className;
    }

}
