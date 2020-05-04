package com.bl.demo;

import com.bl.demo.Exception.CensusAnalyserException;
import com.bl.demo.Exception.TestException;
import com.bl.demo.Exception.createExceptionHeader;
import com.bl.demo.Exception.createExceptionType;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATES_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String SEPERATOR = "src/test/resources/Sepeartor.csv";

    //TC-1.1
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, IndiaCensusCSV.class);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    //TC-1.2
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH,IndiaCensusCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.Census.getException(),e.getMessage());
        }
    }

    //TC-1.3
    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
                censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, createExceptionType.class);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
            }
    }

    //TC-1.4
    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SEPERATOR, IndiaCensusCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.DELIMITER.getException(),e.getMessage());
        }
    }

    //TC-1.5
    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, createExceptionHeader.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }

    //TC-2.1
    @Test
    public void givenIndianStatesCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianStatesCode(INDIA_STATES_CSV_FILE_PATH,IndianStatesCSV.class);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    //TC-2.2
    @Test
    public void givenIndiaStatesCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndianStatesCode(WRONG_CSV_FILE_PATH,IndianStatesCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.States.getException(), e.getMessage());
        }
    }

    //TC-2.3
    @Test
    public void givenIndiaStatesData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATES_CSV_FILE_PATH, createExceptionType.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
        }
    }

    //TC-2.4
    @Test
    public void givenIndiaStatesData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SEPERATOR, IndiaCensusCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.DELIMITER.getException(),e.getMessage());
        }
    }

    //TC-2.5
    @Test
    public void givenIndiaStatesData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATES_CSV_FILE_PATH, createExceptionHeader.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }

    @Test
    public void givenData_SortedStateCensus_InJsonFormat() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,IndiaCensusCSV.class);
            String sortedList = censusAnalyser.printSortedData(censusAnalyser.censusCSVList, "Name");
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedList,IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh",censusCSV[0].state);
        }catch (CensusAnalyserException e) {
            Assert.assertEquals("",e.getMessage());
        }
    }

    @Test
    public void givenData_SortedStateCodes_InJsonFormat() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianStatesCode(INDIA_STATES_CSV_FILE_PATH,IndianStatesCSV.class);
            String sortedList = censusAnalyser.printSortedData(censusAnalyser.statesCSVList,"State Code");
            IndianStatesCSV[] statesCSV = new Gson().fromJson(sortedList,IndianStatesCSV[].class);
            Assert.assertEquals("AD",statesCSV[0].stateCode);
        }catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenData_SortedStatePopulation_WriteInJsonFormat() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,IndiaCensusCSV.class);
            censusAnalyser.writeStatesPopulationWise_InFile("./src/test/resources/SortedData.json");
        }catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
