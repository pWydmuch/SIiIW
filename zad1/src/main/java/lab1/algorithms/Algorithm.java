package lab1.algorithms;

import lab1.individual.City;
import lab1.individual.Individual;
import lab1.util.Loader;


import java.util.ArrayList;
import java.util.List;

public abstract class Algorithm {


    List<City> cities;
    List<Individual> population = new ArrayList<>();
    final static String BASIC_PATH = "src\\main\\resources\\";
    String path;

    abstract public double getBestSolution();

    double getStandardDeviation(double average, List<Double> distances){
        double sumPow = 0;
        for(double distance : distances){
            sumPow+=Math.pow(distance-average,2);
        }
        return Math.sqrt(sumPow/distances.size());
    }

    void loadCities(String path) {

        cities = Loader.load(path);
    }

}
