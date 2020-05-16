package com.bl.demo;

import com.bl.demo.dao.IndianCensusDAO;
import com.bl.demo.dao.IndianStatesDAO;

import java.util.ArrayList;
import java.util.Comparator;

public class SortData {
    public static void sortStateNameWise(ArrayList list) {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(census -> census.state);
        dataSort(comparator,list,"Descending");
    }

    public static void sortStateCodeWise(ArrayList list) {
        Comparator<IndianStatesDAO> comparator = Comparator.comparing(state -> state.stateCode);
        dataSort(comparator,list,"Ascending");
    }

    public static void sortStatePopulationWise(ArrayList list) {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.population);
        dataSort(comparator,list,"Descending");
    }

    public static void sortStateDensityWise(ArrayList list) {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.densityPerSqKm);
        dataSort(comparator,list,"Ascending");
    }

    public static void sortStatePopulationDensityWise(ArrayList list) {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.density);
        dataSort(comparator,list,"Ascending");
    }

    public static void sortStateAreaWise(ArrayList list) {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.areaInSqKm);
        dataSort(comparator,list,"Descending");
    }

    public static void sortStateAreaDensityWise(ArrayList list) {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.area);
        dataSort(comparator,list,"Descending");
    }

    public static <T>void swap(ArrayList list, int j, T census2, T census1) {
        list.set(j, census2);
        list.set(j + 1, census1);
    }

    public static <T>void dataSort(Comparator<T> comparator, ArrayList list, String sort) {
        for (int i=0; i< list.size(); i++){
            for (int j=0; j <list.size()-1; j++) {
                T census1 = (T) list.get(j);
                T census2 = (T) list.get(j+1);
                if (sort.equals("Ascending")) {
                    if (comparator.compare(census1, census2) > 0) {
                        swap(list,j,census2,census1);
                    }
                }
                else {
                    if (comparator.compare(census1, census2) < 0) {
                        swap(list,j,census2,census1);
                    }
                }
            }
        }
    }
}
