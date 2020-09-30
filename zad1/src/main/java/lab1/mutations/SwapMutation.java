package lab1.mutations;

import lab1.util.Draw;
import lab1.individual.Individual;

import java.util.List;

public class SwapMutation  implements Mutation{

    @Override
    public Individual mutate(Individual individual) {
//        List<Integer> indexes = individual.getIndices(2);
        Individual newIndividual = new Individual(individual);
        List<Integer> indexes = Draw.getIndices(2,individual.getRoute().size());
        int index1 = indexes.get(0);
        int index2 = indexes.get(1);
        return swapCities(index1,index2,newIndividual);
    }

}
