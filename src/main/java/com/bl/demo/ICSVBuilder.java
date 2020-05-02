package com.bl.demo;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<T> {
    public Iterator<T> getCSVFileIterator(Reader reader, Class<T> tClass);
    public List<T> getCSVFileList(Reader reader, Class<T> tClass);
}
