package com.bl.demo.Exception;

import com.bl.demo.ICSVBuilder;
import com.bl.demo.IndiaCensusCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class getBeanBuilder<T> implements ICSVBuilder {

    public Iterator getCSVFileIterator(Reader reader, Class csvClass) {
        try {
            return this.getBeanBuilder(reader,csvClass).iterator();
        } catch (RuntimeException e) {
            if (csvClass == createExceptionHeader.class)
                throw new CensusAnalyserException(TestException.HEADER.getException());
            else if (csvClass == createExceptionType.class)
                throw new CensusAnalyserException(TestException.TYPESET.getException());
            else
                throw new CensusAnalyserException(TestException.DELIMITER.getException());
        }
    }

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) {
        try {
            return this.getBeanBuilder(reader, csvClass).parse();
        }catch(RuntimeException e){
                if (csvClass == createExceptionHeader.class)
                    throw new CensusAnalyserException(TestException.HEADER.getException());
                else if (csvClass == createExceptionType.class)
                    throw new CensusAnalyserException(TestException.TYPESET.getException());
                else
                    throw new CensusAnalyserException(TestException.DELIMITER.getException());
            }
        }

    public CsvToBean getBeanBuilder(Reader reader, Class csvClass) {
        return (CsvToBean) new CsvToBeanBuilder<>(reader)
                   .withType(csvClass)
                   .withIgnoreLeadingWhiteSpace(true)
                   .withSeparator(',')
                   .build();
        }
}