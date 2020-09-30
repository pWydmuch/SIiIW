package si.zad2.variable_heuristics;



public class VariableHeuristicFactory {

    public static VariableHeuristic getVariableHeuristic(String heuristic){
        switch (heuristic){
            case "order":
                return new OrderVariableHeuristic();
            case "random":
                return  new RandomVariableHeuritic();
            default:
                return new LeastDomainVarableHeuristic();
        }
    }
}
