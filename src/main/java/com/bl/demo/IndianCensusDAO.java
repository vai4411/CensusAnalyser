package com.bl.demo;

public class IndianCensusDAO {
    public String state;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;

    public IndianCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
    }
}
