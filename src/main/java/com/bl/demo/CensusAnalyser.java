package com.bl.demo;

import com.bl.demo.dao.IndianStatesDAO;
import com.bl.demo.enums.TestException;
import com.bl.demo.exceptions.CensusAnalyserException;
import com.bl.demo.model.IndianStatesCSV;
import com.bl.demo.openCSVBuilder.CSVBuilderFactory;
import com.bl.demo.openCSVBuilder.ICSVBuilder;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import static com.bl.demo.CensusAdapter.*;
import static com.bl.demo.SortData.*;

public class CensusAnalyser {

    public static <T> ArrayList loadIndianStatesCode(String csvFilePath, Class csvClass) throws CensusAnalyserException {
        try {
            ArrayList<IndianStatesDAO> statesDAOArrayList = new ArrayList<>();
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder builder = CSVBuilderFactory.getBuilder();
            Iterator<T> iterator = builder.getCSVFileIterator(reader,csvClass);
            Iterable<T> censusIterable = () -> iterator;
           StreamSupport.stream(censusIterable.spliterator(), false)
                    .forEach(csvState -> statesDAOArrayList.add(new IndianStatesDAO((IndianStatesCSV) csvState)));
            map.put(csvClass, statesDAOArrayList);
        censusCSVList = new ArrayList(map.get(csvClass));
        return censusCSVList;
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.States.getException());
        }
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
                sortStateNameWise(list);
                break;
            case "State Code" :
                sortStateCodeWise(list);
                break;
            case "Population" :
                sortStatePopulationWise(list);
                break;
            case "Density" :
                sortStateDensityWise(list);
                break;
            case "Area" :
                sortStateAreaWise(list);
                break;
            case "Us Area" :
                sortStateAreaDensityWise(list);
                break;
            case "Population Density" :
                sortStatePopulationDensityWise(list);
                break;
            default:
                System.out.println("Invalid choice...");
                break;
        }
        String sortedString = new Gson().toJson(list);
        return sortedString;
    }
}
