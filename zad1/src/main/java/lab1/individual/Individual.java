package lab1.individual;

import lab1.util.Loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Individual {
    private List<Integer> route;
    private List<City> cities;
    private double fitness;


    void loadCities( String path) {

        cities = Loader.load(path);
    }

    public Individual(Individual other){
        route = new ArrayList<>(other.route);
        cities = new ArrayList<>(other.cities);
    }

    public Individual(String path){
        cities = Loader.load(path);
        route = cities.stream()
                .map(City::getNumber)
                .collect(Collectors.toList());
        Collections.shuffle(route);
    }


    public Individual(List<City> cities) {
        this.cities = cities;
        route = cities.stream()
                .map(City::getNumber)
                .collect(Collectors.toList());
        Collections.shuffle(route);
    }

    public Individual( List<City> cities, List<Integer> route) {
        this.route = new ArrayList<>(route);
        this.cities = new ArrayList<>(cities);
    }


    // Czy najlepsza wartosc moze byc najlepsza wartoscia funkcji fitness
    public double countDistance(){
        double totalDistance = 0.0;
        for(int i =0; i<route.size(); i++){
            City a = getCityOfNumber(route.get(i));
            City b;
            if(i==route.size()-1) {
                b = getCityOfNumber(route.get(0));
            } else {
                b = getCityOfNumber(route.get(i + 1));
            }
            totalDistance += a.countDistanceBetweenCities(b);
        }
        fitness = totalDistance;
        return fitness;
    }

    public double getFitness() {
        return fitness;
    }

    private City getCityOfNumber(int number){
        return cities.stream().
                filter(city -> city.getNumber()==number)
                .findFirst()
                .get();
    }

//    public void swapCities(){
//        List<Integer> indexes = getIndices(2);
//        int index1 = indexes.get(0);
//        int index2 = indexes.get(1);
//        swapCities(index1,index2);
//    }
//    public void swapCities(int index1, int index2){
//        int cityNumber1 =route.get(index1);
//        int cityNumber2 = route.get(index2);
//        route.set(index1,cityNumber2);
//        route.set(index2,cityNumber1);
//    }
//
//    public void inverseCities(){
//        List<Integer> indexes = getIndices(2);
//        int index1 =Math.min(indexes.get(0), indexes.get(1));
//        int index2 = Math.max(indexes.get(0), indexes.get(1));
//        for(int i = 0; i< Math.ceil((double) (index2-index1)/2) ;i++){
//            swapCities(index1+i, index2-i);
//        }
//    }



//    public Individual crossOX(Individual parent2){
//
//        List<Integer> childRoute = new ArrayList<>(Collections.nCopies(route.size(),-1));
//        List<Integer> indexes = Draw.getIndices(2,route.size());
//        List<Integer> cityNumbers = new ArrayList<>();
//
//        int index1 =Math.min(indexes.get(0), indexes.get(1));
//        int index2 = Math.max(indexes.get(0), indexes.get(1));
//
//        for(int i = 0; i< index2-index1+1 ;i++){
//            cityNumbers.add(route.get(index1+i));
//            childRoute.set(index1+i, route.get(index1+i));
//        }
//
////        System.out.println("city numbers " + cityNumbers);
//        List<Integer> emptyChildIndexes = Draw.getIndices(route.size(),route.size())
//                .stream()
//                .filter(i-> i<index1 || i>index2)
//                .collect(Collectors.toList());
////        System.out.println("Empty indexes " + emptyChildIndexes);
//        List<Integer> parent2RouteWithoutSetValues = parent2.route
//                .stream()
//                .filter(c->!cityNumbers.contains(c))
//                .collect(Collectors.toList());
////        System.out.println("Parent " + parent2RouteWithoutSetValues);
//        Collections.sort(emptyChildIndexes);
//        int j = 0;
//        for(int i: emptyChildIndexes){
//            childRoute.set(i,parent2RouteWithoutSetValues.get(j));
//            j++;
//        }
//        return new Individual(cities,childRoute);
//    }

    public List<City> getCities() {
        return cities;
    }

    public List<Integer> getRoute(){
        return route;
    }

    public static void main(String[] args) {
        Individual individual = new Individual("src\\main\\resources\\berlin11_modified.tsp");
        Individual individual2 = new Individual("src\\main\\resources\\berlin11_modified.tsp");
        System.out.println(individual.getRoute());
        System.out.println(individual2.getRoute());
//        Individual individual3 = individual.crossOX(individual2);
//        System.out.println(individual3.getRoute());
    }

    @Override
    public String toString() {
        return "Individual{" +
                "route=" + route +
                '}';
    }
}
