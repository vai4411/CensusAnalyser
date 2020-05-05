package com.bl.demo;

import com.bl.demo.exceptions.CensusAnalyserException;
import com.bl.demo.exceptions.TestException;
import com.bl.demo.exceptions.createExceptionHeader;
import com.bl.demo.exceptions.createExceptionType;
import com.bl.demo.model.IndiaCensusCSV;
import com.bl.demo.model.IndianStatesCSV;
import com.bl.demo.model.USCensusCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATES_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
    private static final String SEPERATOR = "src/test/resources/Sepeartor.csv";
    private static final String File1 = "./src/test/resources/SortedData.json";
    private static final String File2 = "./src/test/resources/AreaWiseData.json";

    //TC-1.1
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, IndiaCensusCSV.class, "IndiaCensusCSV");
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
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH,IndiaCensusCSV.class,"IndianCensusCSV");
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
                censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, createExceptionType.class,"IndiaCensusCSV");
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
            }
    }

    //TC-1.4
    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SEPERATOR, IndiaCensusCSV.class,"IndiaCensusCSV");
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
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, createExceptionHeader.class,"IndiaCensusCSV");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }

    //TC-2.1
    @Test
    public void givenIndianStatesCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianStatesCode(INDIA_STATES_CSV_FILE_PATH, IndianStatesCSV.class);
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
            censusAnalyser.loadIndiaCensusData(INDIA_STATES_CSV_FILE_PATH, createExceptionType.class,"IndiaCensusCSV");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
        }
    }

    //TC-2.4
    @Test
    public void givenIndiaStatesData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SEPERATOR, IndiaCensusCSV.class,"IndiaCensusCSV");
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
            censusAnalyser.loadIndiaCensusData(INDIA_STATES_CSV_FILE_PATH, createExceptionHeader.class,"IndiaCensusCSV");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }

    @Test
    public void givenData_SortStateCensusNameWise_DisplayStateNames() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,IndiaCensusCSV.class,"IndiaCensusCSV");
            String sortedList = censusAnalyser.printSortedData(censusAnalyser.censusCSVList, "Name");
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedList,IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh",censusCSV[0].state);
        }catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenData_SortStateCodesWise_DisplayStateCodes() {
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
    public void givenData_SortStatePopulationWise_WriteInJsonFormat() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,IndiaCensusCSV.class,"IndiaCensusCSV");
            censusAnalyser.writeStatesPopulationWise_InFile(File1,"Population");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.FILE.getException(),e.getMessage());
        }
    }

    @Test
    public void givenData_SortStateDensityWise_DisplayStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,IndiaCensusCSV.class,"IndiaCensusCSV");
            String sortedList = censusAnalyser.printSortedData(censusAnalyser.censusCSVList, "Density");
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedList,IndiaCensusCSV[].class);
            Assert.assertEquals("Arunachal Pradesh",censusCSV[0].state);
        }catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenData_SortStateAreaWise_WriteInJsonFormat() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,IndiaCensusCSV.class,"IndiaCensusCSV");
            censusAnalyser.writeStatesPopulationWise_InFile(File2,"Area");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.FILE.getException(),e.getMessage());
        }
    }

    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(US_CENSUS_CSV_FILE_PATH, USCensusCSV.class, "USCensusCSV");
            Assert.assertEquals(51,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }
}
