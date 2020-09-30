package lab1.selections;

import lab1.individual.Individual;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RouletteSelection implements Selection {
    @Override
    public Individual select(List<Individual> individuals) {
        Collections.shuffle(individuals);
        double maxDistance = individuals.stream()
                .mapToDouble(Individual::countDistance)
                .max()
                .getAsDouble();
//        System.out.println("Max distance: " + maxDistance);
        System.out.println(individuals.stream().map(Individual::countDistance).collect(Collectors.toList()));
        List<Double> weights = individuals.stream()
                .map(individual -> maxDistance-individual.countDistance())
                .collect(Collectors.toList());

        Double sum = weights.stream().mapToDouble(d->d).sum();
        System.out.println(weights);
//        System.out.println("Sum: " +sum);
        Double partialSum =0.0;
        int chosenIndividualIndex = 0;
        double rand = new Random().nextDouble()*sum;

        System.out.println("Rand: "+rand);
        for(int i=0; i<individuals.size();i++){
            partialSum+=weights.get(i);
            if(partialSum>=rand) {
                chosenIndividualIndex = i;
                break;
            }
        }

        return new Individual(individuals.get(chosenIndividualIndex));
    }

//    @Override
//    public List<Individual> select(List<Individual> individuals) {
//        return null;
//    }
}
