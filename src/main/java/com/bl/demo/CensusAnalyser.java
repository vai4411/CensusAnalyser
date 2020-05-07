package com.bl.demo;

import com.bl.demo.exceptions.CensusAnalyserException;
import com.bl.demo.exceptions.TestException;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.bl.demo.LoadData.censusCSVList;
import static com.bl.demo.SortData.*;

public class CensusAnalyser {

    public int loadCVSData(Class csvClass, String csv, String csvFilePath) {
     if ( csv == "IndiaCensusCSV" || csv == "USCensusCSV")
         return LoadData.loadCensusData(csv, csvClass, csvFilePath);
     else
         return LoadData.loadIndianStatesCode(csvFilePath,csvClass);
    }

    void writeStatesPopulationWise_InFile(String filePath, String parameter) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            new Gson().toJson(printSortedData(censusCSVList,parameter),fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.FILE.getException());
        }
    }


    String printSortedData(ArrayList list, String parameter) {
        if (list == null || list.size() == 0) {
            throw new CensusAnalyserException(TestException.DATA.getException());
        }
        switch (parameter) {
            case "Name" :
                sortStateNameWise();
                break;
            case "State Code" :
                sortStateCodeWise();
                break;
            case "Population" :
                sortStatePopulationWise();
                break;
            case "Density" :
                sortStateDensityWise();
                break;
            case "Area" :
                sortStateAreaWise();
                break;
            case "Us Area" :
                sortStateAreaDensityWise();
                break;
            case "Population Density" :
                sortStatePopulationDensityWise();
                break;
            default:
                System.out.println("Invalid choice...");
                break;
        }
        String sortedString = new Gson().toJson(list);
        return sortedString;
    }
}