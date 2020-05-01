package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATES_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";

    //TC-1.1
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, IndiaCensusCSV.class,',');
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
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH,IndiaCensusCSV.class,',');
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
                censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, createExceptionType.class,',');
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
            }
    }

    //TC-1.4
    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, IndiaCensusCSV.class,':');
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
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH, createExceptionHeader.class,',');
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }

    //TC-2.1
    @Test
    public void givenIndianStatesCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianStatesCode(INDIA_STATES_CSV_FILE_PATH,IndianStatesCSV.class,',');
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
            censusAnalyser.loadIndianStatesCode(WRONG_CSV_FILE_PATH,IndianStatesCSV.class,',');
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
            censusAnalyser.loadIndiaCensusData(INDIA_STATES_CSV_FILE_PATH, createExceptionType.class,',');
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.TYPESET.getException(),e.getMessage());
        }
    }

    //TC-2.4
    @Test
    public void givenIndiaStatesData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_STATES_CSV_FILE_PATH, IndiaCensusCSV.class,':');
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
            censusAnalyser.loadIndiaCensusData(INDIA_STATES_CSV_FILE_PATH, createExceptionHeader.class,',');
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(TestException.HEADER.getException(),e.getMessage());
        }
    }
}
