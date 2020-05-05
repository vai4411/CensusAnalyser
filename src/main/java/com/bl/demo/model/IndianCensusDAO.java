package com.bl.demo.model;

public class IndianCensusDAO {
    public String state;
    public int population;
    public int densityPerSqKm;
    public long areaInSqKm;
    public String stateId;

    public <T>IndianCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
    }

    public <T>IndianCensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        stateId = usCensusCSV.StateId;
        areaInSqKm = usCensusCSV.areaInSqKm;
    }
}
