package lab1.crossings;

import lab1.individual.Individual;
import lab1.util.Draw;

import java.util.*;
import java.util.stream.Collectors;

public class CrossPMX implements Crossing {

    @Override
    public Individual cross(Individual parent1, Individual parent2) {
        List<Integer> route = new ArrayList<>(parent1.getRoute());
        List<Integer> route2 = new ArrayList<>(parent2.getRoute());
        List<Integer> childRoute2 = new ArrayList<>(Collections.nCopies(route.size(), -1));
        Map<Integer, Integer> corespondingCities = new HashMap<>();
        List<Integer> indexes = Draw.getIndices(2, route.size());
        int index1 = Math.min(indexes.get(0), indexes.get(1));
        int index2 = Math.max(indexes.get(0), indexes.get(1));

        for (int i = 0; i < index2 - index1 + 1; i++) {
            childRoute2.set(index1 + i, route.get(index1 + i));
        }
        for (int i = 0; i < index2 - index1 + 1; i++) {
            corespondingCities.put(route.get(index1 + i), route2.get(index1 + i));
        }

        Map<Integer, Integer> citiesMapping = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : corespondingCities.entrySet()) {
            if (entry.getValue().equals(entry.getKey()))
                citiesMapping.put(entry.getKey(), entry.getValue());
            else
                citiesMapping.put(entry.getKey(), getMappedCity(corespondingCities, entry.getValue(),0));
        }
        for (int i = 0; i < index1; i++) {
            if (citiesMapping.containsKey(route2.get(i)))
                childRoute2.set(i, citiesMapping.get(route2.get(i)));
            else
                childRoute2.set(i, route2.get(i));
        }
        for (int i = index2+1;  i < route.size(); i++) {
            if (citiesMapping.containsKey(route2.get(i)))
                childRoute2.set(i, citiesMapping.get(route2.get(i)));
            else
                childRoute2.set(i, route2.get(i));
        }
        return new Individual(parent1.getCities(),childRoute2);
    }


    private Integer getMappedCity(Map<Integer, Integer> map, Integer value, int cycle) {
        if(cycle>map.size()) return -1;
        if (!map.containsKey(value)) return value;

        return getMappedCity(map, map.get(value),cycle+1);
    }


}
