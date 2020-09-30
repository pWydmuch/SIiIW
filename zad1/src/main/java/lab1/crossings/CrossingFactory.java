package lab1.crossings;

import lab1.mutations.InverseMutation;
import lab1.mutations.Mutation;
import lab1.mutations.SwapMutation;

public class CrossingFactory {
    public static Crossing createCrossing(String cross){
        switch (cross){
            case "ox":
                return new CrossOX();
            case "cx":
                return new CrossCX();
            case "pmx":
                return new CrossPMX();
            default:
                return new CrossOX();
        }
    }
}
