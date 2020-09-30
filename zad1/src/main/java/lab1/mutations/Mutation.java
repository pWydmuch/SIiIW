package lab1.mutations;

import lab1.individual.Individual;

import java.util.List;

public interface Mutation {
    Individual mutate(Individual individual);

    default Individual swapCities(int index1, int index2, Individual individual){

        List<Integer> route = individual.getRoute();
        int cityNumber1 =route.get(index1);
        int cityNumber2 = route.get(index2);
        route.set(index1,cityNumber2);
        route.set(index2,cityNumber1);
        return individual;
    }
    default void swapCities(int index1, int index2, List<Integer> route){

//        List<Integer> route = individual.getRoute();
        int cityNumber1 =route.get(index1);
        int cityNumber2 = route.get(index2);
        route.set(index1,cityNumber2);
        route.set(index2,cityNumber1);

    }


}
