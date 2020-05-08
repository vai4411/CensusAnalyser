package com.bl.demo;

import com.bl.demo.exceptions.createExceptionHeader;
import com.bl.demo.exceptions.createExceptionType;
import com.bl.demo.model.IndiaCensusCSV;
import com.bl.demo.model.IndianStatesCSV;
import com.bl.demo.model.USCensusCSV;

public enum AnalyserDTO {
    IndianCensus(IndiaCensusCSV.class),
    USCensus(USCensusCSV.class),
    IndianState(IndianStatesCSV.class),
    CreateExceptionType(createExceptionType.class),
    CreateExceptionHeader(createExceptionHeader.class);

    private Class className;

    AnalyserDTO(Class className) {
        this.className = className;
    }

    public Class getClassName() {
        return className;
    }

}
