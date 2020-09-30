package lab1.algorithms;

import lab1.individual.Individual;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomAlgorithm extends Algorithm{


    private int populationSize;
    private int generationNumber;
    private int cyclesNumber;
    String path;

    public RandomAlgorithm(int populationSize, int generationNumber) {
        this.populationSize = populationSize;
        this.generationNumber = generationNumber;
        cyclesNumber = populationSize*generationNumber;
        path = "src\\main\\resources\\berlin11_modified.tsp";
    }
    public RandomAlgorithm(int populationSize, int generationNumber, String path) {
        this.populationSize = populationSize;
        this.generationNumber = generationNumber;
        cyclesNumber = populationSize*generationNumber;
        this.path = path;
        loadCities(path);
    }

    private void generatePopulation(){
        for(int i =0; i< cyclesNumber; i++){
            population.add(new Individual(path));
        }
    }

    private double getBestSolution(List<Double> fitnesses){
        double best = Collections.min(fitnesses);
        int index = fitnesses.indexOf(best);
        Individual bestInd = population.get(index);
//        System.out.println(bestInd.getRoute());
        System.out.println(best);
        return best;
    }
    private  double getWorstSolution(List<Double> fitnesses){
        double worst = Collections.max(fitnesses);
        int index = fitnesses.indexOf(worst);
        Individual worstInd = population.get(index);
//        System.out.println(worstInd.getRoute());
        System.out.println(worst);
        return worst;
    }
    private  double getAverageSolution(List<Double> fitnesses){
        double average = fitnesses.stream().mapToDouble(d->d).average().orElse(-1);
        System.out.println(average);
        return average;
    }

    public double getBestSolution(){
        generatePopulation();
        List<Double> fitnesses = population.stream()
                .map(Individual::countDistance)
                .collect(Collectors.toList());
        return getBestSolution(fitnesses);
    }

    public static void main(String[] args) {

        String path = "src\\main\\resources\\fl417.tsp";
        RandomAlgorithm ra = new RandomAlgorithm(100,100,path);
//        ra.loadCities(path);
        ra.generatePopulation();
        List<Double> fitnesses = ra.population.stream()
                .map(Individual::countDistance)
                .collect(Collectors.toList());
        ra.getBestSolution(fitnesses);
        ra.getWorstSolution(fitnesses);
        double avg = ra.getAverageSolution(fitnesses);
        System.out.println(ra.getStandardDeviation(avg, ra.population.stream().map(Individual::countDistance).collect(Collectors.toList())));
    }
}

