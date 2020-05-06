package com.bl.demo;

import com.bl.demo.exceptions.CensusAnalyserException;
import com.bl.demo.exceptions.TestException;
import com.bl.demo.model.*;
import com.bl.demo.openCSVBuilder.CSVBuilderFactory;
import com.bl.demo.openCSVBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class LoadData {
    static HashMap<Class, List> map = new HashMap<>();
    static ArrayList statesCSVList;
    static ArrayList censusCSVList;

    public static  <T>int loadCensusData(String csvFilePath, Class csvClass,String csv) throws CensusAnalyserException {
        try {
            ArrayList<IndianCensusDAO> censusDAOArrayList = new ArrayList<>();
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder builder = CSVBuilderFactory.getBuilder();
            Iterator<T> iterator = builder.getCSVFileIterator(reader,csvClass);
            Iterable<T> censusIterable = () -> iterator;
            if ( csv == "IndiaCensusCSV") {
                StreamSupport.stream(censusIterable.spliterator(), false)
                        .forEach(csvState -> censusDAOArrayList.add(new IndianCensusDAO((IndiaCensusCSV) csvState)));
            }
            else {
                StreamSupport.stream(censusIterable.spliterator(), false)
                        .forEach(csvState -> censusDAOArrayList.add(new IndianCensusDAO((USCensusCSV) csvState)));
            }
            map.put(csvClass, censusDAOArrayList);
            censusCSVList = new ArrayList(map.get(csvClass));
            return map.get(csvClass).size();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.Census.getException());
        }
    }

    public static int loadIndianStatesCode(String csvFilePath, Class csvClass) throws CensusAnalyserException {
        try {
            ArrayList<IndianStatesDAO> statesDAOArrayList = new ArrayList<>();
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder builder = CSVBuilderFactory.getBuilder();
            Iterator<IndianStatesCSV> iterator = builder.getCSVFileIterator(reader,csvClass);
            Iterable<IndianStatesCSV> censusIterable = () -> iterator;
            StreamSupport.stream(censusIterable.spliterator(), false)
                    .forEach(csvState -> statesDAOArrayList.add(new IndianStatesDAO(csvState)));
            map.put(IndianStatesCSV.class, statesDAOArrayList);
            statesCSVList = new ArrayList(map.get(IndianStatesCSV.class));
            return map.get(IndianStatesCSV.class).size();
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.States.getException());
        }
    }
}
