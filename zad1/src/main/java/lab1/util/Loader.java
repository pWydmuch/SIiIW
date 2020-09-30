package lab1.util;

import lab1.individual.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Loader {
//    private String path = "src\\main\\resources\\berlin11_modified.tsp";
//    String[] linie;


    public static List<City> load(String path) {

        List<City> cities = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(
                new FileReader(path))){
            String line = null;
            boolean isLineNode = false;

            while((line = reader.readLine())!=null){
                if(line.contains("NODE_COORD")) {
                    isLineNode = true;
                    continue;
                }
                if(line.contains("EOF")) {
                    isLineNode = false;
                    break;
                }
                if(isLineNode) {
                    String[] cityValues = line.split(" ");
                    City city = new City(Integer.parseInt(cityValues[0]),
                                         Double.parseDouble(cityValues[1]),
                                         Double.parseDouble(cityValues[2]));
                    cities.add(city);
                }
            }
        }catch(Exception ex) {ex.printStackTrace();}
        return cities;
    }



}


