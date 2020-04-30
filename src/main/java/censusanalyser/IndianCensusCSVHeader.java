package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianCensusCSVHeader {
    @CsvBindByName(column = "State Name", required = true)
    public int state;
}
