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
            Iterator<IndiaCensusCSV> censusCSVIterator = getBeanBuilder.getCSVFileIterator(reader, tClass);
            return getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.States.getException());
        }
    }

    private <T> int getCount(Iterator<T> iterator) {
        Iterable<T> csvIterable = () -> iterator;
        int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEateries;
    }

    String sortStatePopulationWise() {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException(TestException.DATA.getException());
        }
        Comparator<IndiaCensusCSV> comparator = Comparator.comparing(census -> census.state);
        this.dataSort(comparator);
        String sortedString = new Gson().toJson(censusCSVList);
        return sortedString;

    }


    public void dataSort(Comparator<IndiaCensusCSV> comparator) {
        for (int i=0; i< censusCSVList.size(); i++){
            for (int j=0; j <censusCSVList.size()-1; j++) {
                IndiaCensusCSV census1 = censusCSVList.get(j);
                IndiaCensusCSV census2 = censusCSVList.get(j+1);
                if (comparator.compare(census1,census2) > 0){
                    censusCSVList.set(j,census2);
                    censusCSVList.set(j+1,census1);
                }
            }
        }
    }
}
