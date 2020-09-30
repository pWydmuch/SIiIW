package lab1.crossings;

import lab1.individual.Individual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrossCXTest {

    final static String BASIC_PATH = "src\\main\\resources\\berlin11_modified.tsp";

    Crossing crossing;
    Individual parent1;
    Individual parent2;

    @BeforeEach
    void setUp(){
        crossing = new CrossCX();
        parent1 = new Individual(BASIC_PATH);
        parent2 = new Individual(BASIC_PATH);
    }
    @Test
    void cross() {
        crossing.cross(parent1,parent2);
    }
}