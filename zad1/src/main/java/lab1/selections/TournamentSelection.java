package lab1.selections;

import lab1.individual.Individual;
import lab1.util.Draw;

import java.util.List;
import java.util.stream.Collectors;

public class TournamentSelection implements Selection {

    private int tourSize;

//    public TournamentSelection(int tourSize) {
//        this.tourSize = tourSize;
//    }


    public void setTourSize(int tourSize) {
        this.tourSize = tourSize;
    }

    @Override
    public Individual select(List<Individual> population) {
        int populationSize = population.size();
        List<Integer> indexes = Draw.getIndices(tourSize,populationSize);
        List<Individual> individuals = indexes.stream().
                map(population::get)
                .sorted((i1, i2) -> (int) (i1.countDistance() - i2.countDistance()))
                .collect(Collectors.toList());
//        individuals.stream().map(Individual::countDistance).forEach(System.out::println);
        return individuals.get(0);
    }
}
