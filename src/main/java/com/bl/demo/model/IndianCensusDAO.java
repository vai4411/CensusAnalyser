package com.bl.demo.model;

public class IndianCensusDAO {
    public String state;
    public long population;
    public int densityPerSqKm;
    public String stateId;
    public long Population;
    public long areaInSqKm;

    public <T>IndianCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
    }

    public <T>IndianCensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        stateId = usCensusCSV.StateId;
        population = usCensusCSV.population;
    }
}
