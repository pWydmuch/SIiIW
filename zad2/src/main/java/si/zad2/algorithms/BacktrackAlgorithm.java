package si.zad2.algorithms;

import si.zad2.csp.Constraint;
import si.zad2.util.Loader;


public class BacktrackAlgorithm extends Algorithm {


    private boolean startAlgorithm(int[][] variables) {
        if (solutionsNr == 0) firsNodesNr++;
        allNodesNr++;
        int row = -1;
        int col = -1;
        int[] cords = variableHeuristic.chooseVariable(variables);
        row = cords[0];
        col = cords[1];
        if (row == -1) {
            logger.log(variables, solutionsNr);
            if (solutionsNr == 0) {
                firstBacktracksNr++;
                firstSolutionTime = System.currentTimeMillis() -startTime ;
            }
            solutionsNr++;
        } else {// else for each-row backtrack
            int[] values = valueHeuristic.chooseValues(DOMAIN);
            for (int num : values) {
                if (Constraint.check(variables, row, col, num)) {
                    variables[row][col] = num;
                    if (!startAlgorithm(variables)) {
                        variables[row][col] = 0; // replace it
//                    return true;
                    } else {
                        logger.log(variables, solutionsNr);
                        if (solutionsNr == 0) {
                            firstSolutionTime = System.currentTimeMillis() - startTime;
                        }
                        solutionsNr++;
                        return false;
                    }
                    if (solutionsNr == 0) firstBacktracksNr++;
                    allBacktracksNr++;
                }
            }
        }
        return false;
    }

    @Override
    public int[][] findSolution(int instance) {
        int[][] variables = Loader.load(instance, path, true);
        startTime = System.currentTimeMillis();
        startAlgorithm(variables);
        allSolutionsTime = (System.currentTimeMillis() - startTime);
        return variables;
    }

}
