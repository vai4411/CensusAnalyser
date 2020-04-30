package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianCensusCSVType {
    @CsvBindByName(column = "State", required = true)
    public int state;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State='" + state + '\'' +
                '}';
    }
}
