package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianStatesCSV {

    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State Name='" + stateName + '\'' +
                ", State Code='" + stateCode + '\'' +
                '}';
    }
}
