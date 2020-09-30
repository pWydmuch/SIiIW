package lab1.selections;

import lab1.individual.Individual;

import java.util.List;

public interface Selection {
//    List<Individual> select(List<Individual> individuals);
    Individual select(List<Individual> individuals);
}
