package si.zad2.algorithms;

import si.zad2.util.Logger;
import si.zad2.value_heuritics.ValueHeuristic;
import si.zad2.variable_heuristics.VariableHeuristic;

public abstract class Algorithm  {
    static final int[] DOMAIN = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    final static int ROWS_NUM = 9;
    final static int COLUMNS_NUM = 9;
    int solutionsNr = 0;
    long startTime;
    long firstSolutionTime;
    int firsNodesNr;
    int firstBacktracksNr;
    long allSolutionsTime;
    int allNodesNr;
    int allBacktracksNr;
    final static String path = "src\\main\\resources\\static\\Sudoku.csv";
    Logger logger = new Logger();
    VariableHeuristic variableHeuristic;
    ValueHeuristic valueHeuristic;

    public VariableHeuristic getVariableHeuristic() {
        return variableHeuristic;
    }

    public void setVariableHeuristic(VariableHeuristic variableHeuristic) {
        this.variableHeuristic = variableHeuristic;
    }

    public ValueHeuristic getValueHeuristic() {
        return valueHeuristic;
    }

    public void setValueHeuristic(ValueHeuristic valueHeuristic) {
        this.valueHeuristic = valueHeuristic;
    }

    public int getSolutionsNr() {
        return solutionsNr;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getFirstSolutionTime() {
        return firstSolutionTime;
    }

    public int getFirsNodesNr() {
        return firsNodesNr;
    }

    public int getFirstBacktracksNr() {
        return firstBacktracksNr;
    }

    public long getAllSolutionsTime() {
        return allSolutionsTime;
    }

    public int getAllNodesNr() {
        return allNodesNr;
    }

    public int getAllBacktracksNr() {
        return allBacktracksNr;
    }

    public abstract int[][] findSolution(int instance);
}
