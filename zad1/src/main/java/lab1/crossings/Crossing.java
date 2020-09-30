package lab1.crossings;

import lab1.individual.Individual;

public interface Crossing {
    Individual cross(Individual parent1, Individual parent2);
}
