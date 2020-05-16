package com.bl.demo;

import com.bl.demo.enums.AnalyserClassSet;
import com.bl.demo.enums.TestException;
import com.bl.demo.exceptions.CensusAnalyserException;
import com.bl.demo.model.IndiaCensusCSV;
import com.bl.demo.model.IndianStatesCSV;
import com.bl.demo.model.USCensusCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static com.bl.demo.CensusAdapter.statesCSVList;
import static com.bl.demo.constants.CensusAnalyserConstants.INDIAN_CENSUS;
import static com.bl.demo.constants.CensusAnalyserConstants.US_CENSUS;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATES_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
    private static final String SEPARATOR = "src/test/resources/Sepeartor.csv";
    private static final String File1 = "./src/test/resources/SortedData.json";
    private static final String File2 = "./src/test/resources/AreaWiseData.json";

    //TC-1.1
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List numOfRecords = new CensusAdapterFactory().loadCVSData(AnalyserClassSet.IndianCensus.getClassName(), INDIAN_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords.size());
        } catch (CensusAnalyserException e) { }
    }

    //TC-1.2
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.IndianCensus.getClassName(), INDIAN_CENSUS, WRONG_CSV_FILE_PATH);
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
            new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.CreateExceptionType.getClassName(), INDIAN_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
            }
    }

    //TC-1.4
    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.IndianCensus.getClassName(), INDIAN_CENSUS, SEPARATOR);
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
            new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.CreateExceptionHeader.getClassName(), INDIAN_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }

    //TC-2.1
    @Test
    public void givenIndianStatesCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List statesCode = censusAnalyser.loadIndianStatesCode(INDIA_STATES_CSV_FILE_PATH, AnalyserClassSet.IndianState.getClassName());
            Assert.assertEquals(37,statesCode.size());
        } catch (CensusAnalyserException e) { }
    }

    //TC-2.2
    @Test
    public void givenIndiaStatesCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndianStatesCode(WRONG_CSV_FILE_PATH, AnalyserClassSet.IndianState.getClassName());
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
            censusAnalyser.loadIndianStatesCode( INDIA_STATES_CSV_FILE_PATH,AnalyserClassSet.CreateExceptionType.getClassName());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
        }
    }

    //TC-2.4
    @Test
    public void givenIndiaStatesData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianStatesCode(SEPARATOR, AnalyserClassSet.IndianCensus.getClassName());
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
            censusAnalyser.loadIndianStatesCode(INDIA_STATES_CSV_FILE_PATH, AnalyserClassSet.CreateExceptionHeader.getClassName());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }

    @Test
    public void givenData_SortStateCensusNameWise_DisplayStateNames() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List censusCSVList = new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.IndianCensus.getClassName(), INDIAN_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedList = censusAnalyser.printSortedData((ArrayList) censusCSVList, "Name");
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedList,IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh",censusCSV[censusCSVList.size()-1].state);
        }catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenData_SortStateCodesWise_DisplayStateCodes() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList censusCSVList = (ArrayList) CensusAnalyser.loadIndianStatesCode(INDIA_STATES_CSV_FILE_PATH, AnalyserClassSet.IndianState.getClassName());
            String sortedList = censusAnalyser.printSortedData(censusCSVList,"State Code");
            IndianStatesCSV[] statesCSV = new Gson().fromJson(sortedList,IndianStatesCSV[].class);
            Assert.assertEquals("AD",statesCSV[0].stateCode);
        }catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenData_SortStatePopulationWise_WriteInJsonFormat() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List stateCode = new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.IndianCensus.getClassName(), INDIAN_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.writeStatesPopulationWise_InFile(File1,"Population");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.FILE.getException(),e.getMessage());
        }
    }

    @Test
    public void givenData_SortStateDensityWise_DisplayStateName() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List stateCode = new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.IndianCensus.getClassName(), INDIAN_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String sortedList = censusAnalyser.printSortedData((ArrayList) stateCode, "Density");
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedList,IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh",censusCSV[0].state);
        }catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenData_SortStateAreaWise_WriteInJsonFormat() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List stateCode = new  CensusAdapterFactory().loadCVSData(AnalyserClassSet.IndianCensus.getClassName(), INDIAN_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.writeStatesPopulationWise_InFile(File2,"Area");
        }catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.FILE.getException(),e.getMessage());
        }
    }

    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList usCensus = new USCensusAdapter().loadCVSData(AnalyserClassSet.USCensus.getClassName(), US_CENSUS, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51,usCensus.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenData_SortUSStatePopulationWise_DisplayState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList usCensus = new USCensusAdapter().loadCVSData(AnalyserClassSet.USCensus.getClassName(), US_CENSUS, US_CENSUS_CSV_FILE_PATH);
            String sortedList = censusAnalyser.printSortedData(usCensus,"Population");
            USCensusCSV[] statesCSV = new Gson().fromJson(sortedList,USCensusCSV[].class);
            Assert.assertEquals("Wyoming",statesCSV[50].state);
        }catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenData_SortUSStatePopulationDensityWise_DisplayState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList usCensus = new USCensusAdapter().loadCVSData(AnalyserClassSet.USCensus.getClassName(), US_CENSUS, US_CENSUS_CSV_FILE_PATH);
            String sortedList = censusAnalyser.printSortedData(usCensus," Density");
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedList,IndiaCensusCSV[].class);
            Assert.assertEquals("Alabama",censusCSV[0].state);
        }catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenData_SortUSStateAreaDensityWise_DisplayState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ArrayList usCensus = new USCensusAdapter().loadCVSData(AnalyserClassSet.USCensus.getClassName(), US_CENSUS, US_CENSUS_CSV_FILE_PATH);
            String sortedList = censusAnalyser.printSortedData(usCensus,"US Area");
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedList,USCensusCSV[].class);
            Assert.assertEquals("Alabama",censusCSV[0].state);
        }catch (CensusAnalyserException e) {
        }
    }
}
