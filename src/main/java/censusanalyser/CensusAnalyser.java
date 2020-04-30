package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath, Class tClass, char seperater) throws CensusAnalyserException {
        try {
            if (seperater != ',')
                throw new CensusAnalyserException(TestException.DELIMITER.getException());
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndiaCensusCSV> censusCSVIterator = getCSVIterator(reader, tClass, seperater);
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.Census.getException());
        }
    }

    public int loadIndianStatesCode(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndianStatesCSV> censusCSVIterator = getCSVIterator(reader, IndianStatesCSV.class, ',');
            Iterable<IndianStatesCSV> csvIterable = () -> censusCSVIterator;
            int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.States.getException());
        }
    }

    public <T> Iterator<T> getCSVIterator(Readable reader, Class<T> csvClass, char seperater) {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<T>((Reader) reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            csvToBeanBuilder.withSeparator(seperater);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (Exception e) {
            throw new CensusAnalyserException(TestException.TYPESET.getException());
        }
    }
}
