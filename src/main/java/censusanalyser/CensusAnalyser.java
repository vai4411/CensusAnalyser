package censusanalyser;

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
            getBeanBuilder getBeanBuilder = new getBeanBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = getBeanBuilder.getBeanBuilder(reader, tClass, seperater);
            return getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(TestException.Census.getException());
        }
    }

    public int loadIndianStatesCode(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            getBeanBuilder getBeanBuilder = new getBeanBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = getBeanBuilder.getBeanBuilder(reader,IndianStatesCSV.class,',');
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
}
