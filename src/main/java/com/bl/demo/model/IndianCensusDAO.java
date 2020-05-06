package com.bl.demo.model;

public class IndianCensusDAO {
    public String state;
    public long population;
    public int densityPerSqKm;
    public long areaInSqKm;
    public double density;
    public double area;

    public <T>IndianCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
    }

    public <T>IndianCensusDAO(USCensusCSV usCensusCSV) {
        density = usCensusCSV.density;
        area = usCensusCSV.area;
        population = usCensusCSV.population;
        state = usCensusCSV.state;
    }
}
