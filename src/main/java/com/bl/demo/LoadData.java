package com.bl.demo;

import com.bl.demo.dao.IndianCensusDAO;
import com.bl.demo.dao.IndianStatesDAO;
import com.bl.demo.exceptions.CensusAnalyserException;
import com.bl.demo.enums.TestException;
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

import static com.bl.demo.constants.CensusAnalyserConstants.INDIAN_CENSUS;

public class LoadData {
    static HashMap<Class, List> map = new HashMap<>();
    static ArrayList statesCSVList;
    static ArrayList censusCSVList;

    public static  <T>int loadCensusData(String csv, Class csvClass, String csvFilePath) throws CensusAnalyserException {
        try {
            ArrayList<IndianCensusDAO> censusDAOArrayList = new ArrayList<>();
            Iterable censusIterable = (Iterable) iterator(csvClass,csvFilePath);
            if ( csv.equals(INDIAN_CENSUS)) {
                StreamSupport.stream(censusIterable.spliterator(), false)
                        .forEach(csvState -> censusDAOArrayList.add(new IndianCensusDAO((IndiaCensusCSV) csvState)));
            }
            else {
                StreamSupport.stream(censusIterable.spliterator(), false)
                        .forEach(csvState -> censusDAOArrayList.add(new IndianCensusDAO((USCensusCSV) csvState)));
            }
            return count(csvClass,censusDAOArrayList);
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.Census.getException());
        }
    }

    public static <T>int loadIndianStatesCode(String csvFilePath, Class csvClass) throws CensusAnalyserException {
        try {
            ArrayList<IndianStatesDAO> statesDAOArrayList = new ArrayList<>();
            Iterable censusIterable = (Iterable) iterator(csvClass,csvFilePath);
            StreamSupport.stream(censusIterable.spliterator(), false)
                    .forEach(csvState -> statesDAOArrayList.add(new IndianStatesDAO((IndianStatesCSV) csvState)));
            return count(IndianStatesCSV.class, statesDAOArrayList);
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.States.getException());
        }
    }

    public static <T>Object iterator(Class csvClass, String csvFilePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        ICSVBuilder builder = CSVBuilderFactory.getBuilder();
        Iterator<T> iterator = builder.getCSVFileIterator(reader,csvClass);
        Iterable<T> censusIterable = () -> iterator;
        return censusIterable;
    }

    public static int count(Class csvClass, ArrayList arrayList) {
        map.put(csvClass, arrayList);
        censusCSVList = new ArrayList(map.get(csvClass));
        return map.get(csvClass).size();
    }
}
