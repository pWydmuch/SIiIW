package lab1.algorithms;

import lab1.individual.City;
import lab1.individual.Individual;

import java.util.*;
import java.util.stream.Collectors;

public class GreedyAlgorithm extends Algorithm {


    public GreedyAlgorithm() {
    }

    public GreedyAlgorithm(String path) {
        loadCities(path);
    }

    private double getDistanceStartingOfCity(int i) {
        List<Integer> route = new ArrayList<>();
        City cityStart = cities.get(i);
        route.add(cityStart.getNumber());
        List<City> otherCities = getCitiesWithoutACity(cities, cityStart);
        City city = null;
        while (!otherCities.isEmpty()) {
            city = getNextCity(cityStart, otherCities);
            route.add(city.getNumber());
            otherCities = getCitiesWithoutACity(otherCities, city);
            cityStart = city;
        }
        Individual individual = new Individual(cities,route);
        population.add(individual);
        return individual.countDistance();
    }

    private List<Integer> drawIndices(){
        Set<Integer> indices = new HashSet<>();
        System.out.println(cities.size());
        while(indices.size()!=cities.size()) {
           indices.add(new Random().nextInt(cities.size()));
        }
        return new ArrayList<>(indices);
    }

    private List<City> getCitiesWithoutACity(List<City> cities, City city) {
        return cities.stream()
                .filter(c -> c.getNumber() != city.getNumber())
                .collect(Collectors.toList());
    }

    private City getNextCity(City city, List<City> otherCities) {
        List<Double> distances = otherCities.stream()
                .map(city::countDistanceBetweenCities)
                .collect(Collectors.toList());
        int indexOfShortest = distances.indexOf(Collections.min(distances));
        return otherCities.get(indexOfShortest);
    }

    public double getBestSolution(){
        List<Double> distances = new ArrayList<>();
        List<Integer> startingIndices = drawIndices();
        for(Integer i: startingIndices){
            double distance = getDistanceStartingOfCity(i);
            distances.add(distance);
        }
        double best = Collections.min(distances);
        int indexBest = distances.indexOf(best);
        System.out.println(best);
        return best;
//        Individual
    }

    private List<Double> getDistancesOfDrownCities(){
        List<Double> distances = new ArrayList<>();
        List<Integer> startingIndices = drawIndices();
        for(Integer i: startingIndices){
            double distance = getDistanceStartingOfCity(i);
            distances.add(distance);
        }
        return distances;
    }


    public static void main(String[] args) {

        GreedyAlgorithm al = new GreedyAlgorithm();
        String path = "src\\main\\resources\\fl417.tsp";
        al.loadCities(path);
        List<Double> fitnesses = new ArrayList<>();
//        List<Integer> startingIndices = al.drawIndices();
        List<Integer> startingIndices = al.cities.stream().map(i->al.cities.indexOf(i)).collect(Collectors.toList());
        for(Integer i: startingIndices){
            double fitness = al.getDistanceStartingOfCity(i);
            fitnesses.add(fitness);
        }
        double averege = fitnesses.stream().mapToDouble(d->d).average().orElse(-1);
        System.out.println(Collections.min(fitnesses));
        System.out.println(Collections.max(fitnesses));
        System.out.println(averege);
        System.out.println(al.getStandardDeviation(averege,fitnesses));
    }
}
