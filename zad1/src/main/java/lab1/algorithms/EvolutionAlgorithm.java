package lab1.algorithms;

import lab1.crossings.CrossingFactory;
import lab1.individual.Individual;
import lab1.crossings.CrossOX;
import lab1.crossings.Crossing;
import lab1.mutations.Mutation;
import lab1.mutations.MutationFactory;
import lab1.mutations.SwapMutation;
import lab1.selections.Selection;
import lab1.selections.TournamentSelection;
import lab1.util.Logger;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EvolutionAlgorithm extends Algorithm {

    private int populationSize;
    private int generationsNumber;
    private double crossProbability;
    private double mutationProbability;
    private Mutation mutation;
    private Crossing crossing;
    private Selection selection;
    private Random random;
    private Logger logger;



    public EvolutionAlgorithm(int populationSize) {
        this.populationSize = populationSize;
    }



    public EvolutionAlgorithm(String instance,int populationSize, int generationNumber, double crossProbability, double mutationProbability,
                              Mutation mutation, Crossing crossing, Selection selection) {
        this.path = BASIC_PATH+instance+".tsp";
        this.populationSize = populationSize;
        this.generationsNumber = generationNumber;
        this.crossProbability = crossProbability;
        this.mutationProbability = mutationProbability;
        random = new Random();
        this.mutation = mutation;
        this.selection = selection;
        this.crossing = crossing;
        logger = new Logger();
    }


    public Individual start() {
        generatePopulation();
        log(0);
        Individual best = findBest();
        for (int i = 1; i < generationsNumber; i++) {
            List<Individual> newPopulation = new ArrayList<>();
            while (newPopulation.size() != population.size()) {

                Individual parent1 = select();
                Individual parent2 = select();
                Individual child;
                if (random.nextDouble() <= crossProbability) {
                    child = cross(parent1, parent2);
                } else
                    child = new Individual(parent1);

                if (random.nextDouble() <= mutationProbability) {
                    child = mutate(child);
                }
                newPopulation.add(child);
                best = best.countDistance()
                        < child.countDistance()
                        ? best : child;

            }
            population = new ArrayList<>(newPopulation);
            log(i);
        }

        return best;
    }

    private void log(int generationNumber) {
        String strBest = String.format("%.0f",findBest().countDistance());
        String strAverage = String.format("%.0f",findAverage());
        String strWorst = String.format("%.0f",findWorst().countDistance());

        logger.log(generationNumber + "," + strBest + "," + strAverage + "," + strWorst);
    }

    private Individual findBest(Individual oldBest) {
        Individual newBest = population.stream()
                .min((i1, i2) -> (int) (i1.countDistance() - i2.countDistance()))
                .get();
        return newBest.countDistance() < oldBest.countDistance() ? newBest : oldBest;
    }

    private Individual findBest() {
        return population.stream()
                .min((i1, i2) -> (int) (i1.countDistance() - i2.countDistance()))
                .get();
    }

    private Individual findWorst() {
        return population.stream()
                .max((i1, i2) -> (int) (i1.countDistance() - i2.countDistance()))
                .get();
    }

    private Double findAverage() {
       double i = 123e-12;
        return population
                .stream()
                .mapToDouble(Individual::countDistance)
                .average()
                .orElse(-1);
    }


    private Individual select() {
        return selection.select(population);
    }


    private Individual cross(Individual parent1, Individual parent2) {
        return crossing.cross(parent1, parent2);
    }

    private Individual mutate(Individual individual) {
        return mutation.mutate(individual);
    }

    private void generatePopulation() {
        population = Stream.generate(() -> new Individual(path)).
                limit(populationSize).
                collect(Collectors.toList());
    }


    @Override
    public double getBestSolution() {
        return 0;
    }

    public static void main(String[] args) {
        TournamentSelection tournamentSelection = new TournamentSelection();
        Algorithm algorithm = new EvolutionAlgorithm("fl417",
                30,
                100,
                0.7,
                0.1,
                new SwapMutation(),
                new CrossOX(),
                tournamentSelection
        );
        tournamentSelection.setTourSize(5);
        List<Double> bestDistances = new ArrayList<>();
        List<Double> worstDistances = new ArrayList<>();
        double average = -1;
        for(int i=0;i<2;i++){
            bestDistances.add(((EvolutionAlgorithm) algorithm).start().countDistance());
            worstDistances.add(((EvolutionAlgorithm) algorithm).findWorst().countDistance());
        }
        double best = Collections.min(bestDistances);
        double worst = Collections.max(worstDistances);
        average = bestDistances.stream().mapToDouble(d->d).average().getAsDouble();

        System.out.println("best: " + best);
        System.out.println("worst: " + worst);
        System.out.println("average: " + average);
        System.out.println(algorithm.getStandardDeviation(average,bestDistances));
    }

}


