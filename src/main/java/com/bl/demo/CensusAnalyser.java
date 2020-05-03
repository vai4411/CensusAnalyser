package com.bl.demo;

import com.bl.demo.Exception.CensusAnalyserException;
import com.bl.demo.Exception.TestException;
import com.bl.demo.Exception.getBeanBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List<IndiaCensusCSV> censusCSVList = null;
    List<IndianStatesCSV> statesCSVList = null;

    public int loadIndiaCensusData(String csvFilePath, Class tClass) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            getBeanBuilder getBeanBuilder = CSVBuilderFactory.getBuilder();
            censusCSVList = getBeanBuilder.getCSVFileList(reader, tClass);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.Census.getException());
        }
    }

    public int loadIndianStatesCode(String csvFilePath, Class tClass) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            getBeanBuilder getBeanBuilder = CSVBuilderFactory.getBuilder();
            statesCSVList = getBeanBuilder.getCSVFileList(reader, tClass);
            return statesCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.States.getException());
        }
    }

    private <T> int getCount(Iterator<T> iterator) {
        Iterable<T> csvIterable = () -> iterator;
        int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEateries;
    }

    void sortStatePopulationWise() {
        Comparator<IndiaCensusCSV> comparator = Comparator.comparing(census -> census.state);
        dataSort(comparator,censusCSVList);
    }

    void sortStateCodeWise() {
        Comparator<IndianStatesCSV> comparator = Comparator.comparing(state -> state.stateCode);
        dataSort(comparator,statesCSVList);
    }

    String sortedData(List list) {
        if (list == null || list.size() == 0) {
            throw new CensusAnalyserException(TestException.DATA.getException());
        }
        if (list == censusCSVList)
            sortStatePopulationWise();
        else
            sortStateCodeWise();
        String sortedString = new Gson().toJson(list);
        return sortedString;
    }

    public <T>void dataSort(Comparator<T> comparator, List list) {
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
}