package com.bl.demo;

import com.bl.demo.Exception.CensusAnalyserException;
import com.bl.demo.Exception.TestException;
import com.bl.demo.Exception.getBeanBuilder;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CensusAnalyser {

    static HashMap<Class, List> map = new HashMap<>();
    static ArrayList statesCSVList;
    static ArrayList censusCSVList;

    public int loadIndiaCensusData(String csvFilePath, Class csvClass) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            getBeanBuilder getBeanBuilder = CSVBuilderFactory.getBuilder();
            map.put(IndiaCensusCSV.class,getBeanBuilder.getCSVFileList(reader,csvClass));
            censusCSVList = new ArrayList(map.get(IndiaCensusCSV.class));
            return map.get(IndiaCensusCSV.class).size();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.Census.getException());
        }
    }

    public int loadIndianStatesCode(String csvFilePath, Class csvClass) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            getBeanBuilder getBeanBuilder = CSVBuilderFactory.getBuilder();
            map.put(IndianStatesCSV.class,getBeanBuilder.getCSVFileList(reader,csvClass));
            statesCSVList = new ArrayList(map.get(IndianStatesCSV.class));
            return map.get(IndianStatesCSV.class).size();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.States.getException());
        }
    }

    void sortStateNameWise() {
        Comparator<IndiaCensusCSV> comparator = Comparator.comparing(census -> census.state);
        dataSort(comparator,censusCSVList,"Ascending");
    }

    void sortStateCodeWise() {
        Comparator<IndianStatesCSV> comparator = Comparator.comparing(state -> state.stateCode);
        dataSort(comparator,statesCSVList,"Ascending");
    }

    void sortStatePopulationWise() {
        Comparator<IndiaCensusCSV> comparator = Comparator.comparing(state -> state.population);
        dataSort(comparator,censusCSVList,"Descending");
    }

    void sortStateDensityWise() {
        Comparator<IndiaCensusCSV> comparator = Comparator.comparing(state -> state.densityPerSqKm);
        dataSort(comparator,censusCSVList,"Ascending");
    }

    void writeStatesPopulationWise_InFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            new Gson().toJson(printSortedData(censusCSVList,"Population"),fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.FILE.getException());
        }
    }

    public <T>void swap(ArrayList list, int j, T census2, T census1) {
        list.set(j, census2);
        list.set(j + 1, census1);
    }

    public <T>void dataSort(Comparator<T> comparator, ArrayList list, String sort) {
        for (int i=0; i< list.size(); i++){
            for (int j=0; j <list.size()-1; j++) {
                T census1 = (T) list.get(j);
                T census2 = (T) list.get(j+1);
                if (sort == "Ascending") {
                    if (comparator.compare(census1, census2) > 0) {
                        swap(list,j,census2,census1);
                    }
                }
                else {
                    if (comparator.compare(census1, census2) < 0) {
                        swap(list,j,census2,census1);
                    }
                }
            }
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
        else
            sortStateDensityWise();
        String sortedString = new Gson().toJson(list);
        return sortedString;
    }
}