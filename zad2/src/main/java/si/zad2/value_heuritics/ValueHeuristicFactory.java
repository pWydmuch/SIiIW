package si.zad2.value_heuritics;

public class ValueHeuristicFactory {

    public static ValueHeuristic getValueHeuristic(String heuristic){
        switch (heuristic){
            case "order":
                return new OrderValueHeuristic();
            case "random":
                return  new RandomValueHeuritic();
            default:
                return new LeastConstraintedValueHeuristic();
        }
    }

}
