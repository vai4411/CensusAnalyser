package com.bl.demo;

import com.bl.demo.exceptions.CensusAnalyserException;

import java.util.List;

import static com.bl.demo.constants.CensusAnalyserConstants.INDIAN_CENSUS;
import static com.bl.demo.constants.CensusAnalyserConstants.US_CENSUS;

public class CensusAdapterFactory {
    public List loadCVSData(Class csvClass, String csv, String csvFilePath) {
        if ( csv.equals(INDIAN_CENSUS))
            return new  IndianCensusAdapter().loadCVSData(csvClass, csv, csvFilePath);
        else if (csv.equals(US_CENSUS))
            return new USCensusAdapter().loadCVSData(csvClass, csv, csvFilePath);
        else
            throw new CensusAnalyserException("Country_Not_Available");
    }
}
