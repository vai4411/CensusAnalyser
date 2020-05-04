package com.bl.demo;

public class indianCensusDAO {
    public String state;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;

    public indianCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
    }
}
