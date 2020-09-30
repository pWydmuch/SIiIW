package lab1.crossings;

import lab1.util.Draw;
import lab1.individual.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CrossOX implements Crossing {
    @Override
    public Individual cross(Individual parent1, Individual parent2) {

        List<Integer> route = new ArrayList<>(parent1.getRoute());
        List<Integer> childRoute = new ArrayList<>(Collections.nCopies(route.size(),-1));
        List<Integer> cityNumbers = new ArrayList<>();
        List<Integer> indexes = Draw.getIndices(2,route.size());
        int index1 =Math.min(indexes.get(0), indexes.get(1));
        int index2 = Math.max(indexes.get(0), indexes.get(1));
        for(int i = 0; i< index2-index1+1 ;i++){
            cityNumbers.add(route.get(index1+i));
            childRoute.set(index1+i, route.get(index1+i));
        }
        List<Integer> emptyChildIndexes = Draw.getIndices(route.size(),route.size())
                .stream()
                .filter(i-> i<index1 || i>index2)
                .collect(Collectors.toList());
        List<Integer> parent2RouteWithoutSetValues = parent2.getRoute()
                .stream()
                .filter(c->!cityNumbers.contains(c))
                .collect(Collectors.toList());
        Collections.sort(emptyChildIndexes);
        int j = 0;
        for(int i: emptyChildIndexes){
            childRoute.set(i,parent2RouteWithoutSetValues.get(j));
            j++;
        }
        return new Individual(parent1.getCities(),childRoute);
    }
}
