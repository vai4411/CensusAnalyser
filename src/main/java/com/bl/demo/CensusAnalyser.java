package com.bl.demo;

import com.bl.demo.Exception.CensusAnalyserException;
import com.bl.demo.Exception.TestException;
import com.bl.demo.Exception.getBeanBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CensusAnalyser {

    static HashMap<Class,List> map = new HashMap<>();
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
        dataSort(comparator,censusCSVList);
    }

    void sortStateCodeWise() {
        Comparator<IndianStatesCSV> comparator = Comparator.comparing(state -> state.stateCode);
        dataSort(comparator,statesCSVList);
    }

    public <T>void dataSort(Comparator<T> comparator, ArrayList list) {
        for (int i=0; i< list.size(); i++){
            for (int j=0; j <list.size()-1; j++) {
                T census1 = (T) list.get(j);
                T census2 = (T) list.get(j+1);
                if (comparator.compare(census1,census2) > 0){
                    list.set(j,census2);
                    list.set(j+1,census1);
                }
            }
        }
    }

    String printSortedData(ArrayList list ) {
        if (list == null || list.size() == 0) {
            throw new CensusAnalyserException(TestException.DATA.getException());
        }
        if (list == censusCSVList)
            sortStateNameWise();
        else
            sortStateCodeWise();
        String sortedString = new Gson().toJson(list);
        return sortedString;
    }
}