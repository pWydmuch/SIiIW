package lab1.crossings;

import lab1.individual.Individual;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrossPMXTest {

    final static String BASIC_PATH = "src\\main\\resources\\berlin11_modified.tsp";

    Crossing crossing;
    Individual parent1;
    Individual parent2;

    @BeforeEach
     void setUp(){
        crossing = new CrossPMX();
        parent1 = new Individual(BASIC_PATH);
        parent2 = new Individual(BASIC_PATH);
    }

    @Test
    @DisplayName("Test pmx crossing")
    void shouldSth(){
        System.out.println(parent1.getRoute());
        System.out.println(parent2.getRoute());

        System.out.println(crossing.cross(parent1,parent2));
    }

}