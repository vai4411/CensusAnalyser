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

    public int loadCVSData(String csvFilePath, Class csvClass, String csv) {
     if ( csv == "IndiaCensusCSV" || csv == "USCensusCSV")
         return LoadData.loadCensusData(csvFilePath,csvClass,csv);
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
        if (parameter == "Name")
            sortStateNameWise();
        else if (parameter == "State Code")
            sortStateCodeWise();
        else if (parameter == "Population")
            sortStatePopulationWise();
        else if (parameter == "Density")
            sortStateDensityWise();
        else if (parameter == "Area")
            sortStateAreaWise();
        else if (parameter == "Us Area")
            sortStateAreaDensityWise();
        else
            sortStatePopulationDensityWise();
        String sortedString = new Gson().toJson(list);
        return sortedString;
    }
}