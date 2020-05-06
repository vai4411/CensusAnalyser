package com.bl.demo;

import com.bl.demo.model.IndianCensusDAO;
import com.bl.demo.model.IndianStatesDAO;

import java.util.ArrayList;
import java.util.Comparator;

import static com.bl.demo.LoadData.censusCSVList;
import static com.bl.demo.LoadData.statesCSVList;

public class SortData {
    public static void sortStateNameWise() {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(census -> census.state);
        dataSort(comparator,censusCSVList,"Ascending");
    }

    public static void sortStateCodeWise() {
        Comparator<IndianStatesDAO> comparator = Comparator.comparing(state -> state.stateCode);
        dataSort(comparator,statesCSVList,"Ascending");
    }

    public static void sortStatePopulationWise() {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.population);
        dataSort(comparator,censusCSVList,"Descending");
    }

    public static void sortStateDensityWise() {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.densityPerSqKm);
        dataSort(comparator,censusCSVList,"Ascending");
    }

    public static void sortStatePopulationDensityWise() {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.density);
        dataSort(comparator,censusCSVList,"Ascending");
    }

    public static void sortStateAreaWise() {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.areaInSqKm);
        dataSort(comparator,censusCSVList,"Descending");
    }

    public static void sortStateAreaDensityWise() {
        Comparator<IndianCensusDAO> comparator = Comparator.comparing(state -> state.area);
        dataSort(comparator,censusCSVList,"Descending");
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
                if (sort == "Ascending") {
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
