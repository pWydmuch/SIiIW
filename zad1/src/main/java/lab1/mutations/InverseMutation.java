package lab1.mutations;

import lab1.individual.Individual;
import lab1.util.Draw;

import java.util.List;

public class InverseMutation implements Mutation {
    @Override
    public Individual mutate(Individual individual) {
        Individual newIndividual = new Individual(individual);
        List<Integer> indexes = Draw.getIndices(2,newIndividual.getRoute().size());
        int index1 =Math.min(indexes.get(0), indexes.get(1));
        int index2 = Math.max(indexes.get(0), indexes.get(1));
        for(int i = 0; i< Math.ceil((double) (index2-index1)/2) ;i++){
             swapCities(index1+i, index2-i, newIndividual.getRoute());
        }
        return newIndividual;
    }
}
