package com.bl.demo.dao;

import com.bl.demo.model.IndiaCensusCSV;
import com.bl.demo.model.USCensusCSV;

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
        density = indiaCensusCSV.density;
        area = indiaCensusCSV.area;
    }

    public <T>IndianCensusDAO(USCensusCSV usCensusCSV) {
        density = usCensusCSV.density;
        area = usCensusCSV.area;
        population = usCensusCSV.population;
        state = usCensusCSV.state;
    }
}
