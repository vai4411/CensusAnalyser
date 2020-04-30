package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianCensusCSVType {
    @CsvBindByName(column = "State", required = true)
    public Integer state;
}
