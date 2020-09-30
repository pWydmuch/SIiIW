package lab1.crossings;

import lab1.individual.Individual;
import lab1.util.Draw;

import java.util.*;

public class CrossCX implements Crossing {

    @Override
    public Individual cross(Individual parent1, Individual parent2) {
        List<Integer> route = new ArrayList<>(parent1.getRoute());
        List<Integer> route2 = new ArrayList<>(parent2.getRoute());
        List<Integer> childRoute = new ArrayList<>(Collections.nCopies(route.size(), -1));
        int randomIndex = new Random().nextInt(route.size());
        List<Integer> cycle = new ArrayList<>();
        List<Integer> cycleIndexes = new ArrayList<>();
        int currentCityNumber;
        currentCityNumber = route.get(randomIndex);
        cycleIndexes.add(randomIndex);
        int index = -1;
        while (!cycle.contains(currentCityNumber)) {
            cycle.add(currentCityNumber);
            currentCityNumber = route2.get(route.indexOf(currentCityNumber));
            index = route.indexOf(currentCityNumber);
            cycleIndexes.add(index);
        }
        cycleIndexes.remove(cycleIndexes.size()-1);
        for (int i =0;i<route.size();i++){
            if(cycleIndexes.contains(i))
                childRoute.set(i,route.get(i));
            else
                childRoute.set(i,route2.get(i));
        }
        return new Individual(parent1.getCities(),childRoute);
    }


}
