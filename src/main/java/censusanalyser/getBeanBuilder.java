package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class getBeanBuilder<T> implements ICSVBuilder{

    public Iterator<T> getCSVFileIterator(Reader reader, Class csvClass, char seperater) {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<T>((Reader) reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            csvToBeanBuilder.withSeparator(seperater);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (RuntimeException e) {
            if (csvClass == createExceptionHeader.class)
                throw new CensusAnalyserException(TestException.HEADER.getException());
            else
                throw new CensusAnalyserException(TestException.TYPESET.getException());
        }
    }
}
