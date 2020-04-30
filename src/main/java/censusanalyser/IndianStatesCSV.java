package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianStatesCSV {
    @CsvBindByName(column = "SrNo", required = true)
    public int number;

    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "Sr No='" + number + '\'' +
                ", State Name='" + stateName + '\'' +
                ", TIN='" + tin + '\'' +
                ", State Code='" + stateCode + '\'' +
                '}';
    }
}
