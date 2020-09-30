package si.zad2.algorithms;

import si.zad2.csp.Constraint;
import si.zad2.csp.Variable;
import si.zad2.util.Loader;
import si.zad2.util.Mapper;
import si.zad2.value_heuritics.OrderValueHeuristic;
import si.zad2.variable_heuristics.OrderVariableHeuristic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ForwardAlgorithm extends Algorithm {

    List<Variable> variables;
    int ile;

    private boolean startAlgorithm() {
        ile++;
        List<Variable> prev = variables.stream().map(Variable::new).collect(Collectors.toList());
        if (solutionsNr == 0) firsNodesNr++;
        int[][] arrayVariables = Mapper.mapToArray(variables);
        allNodesNr++;
        int row = -1;
        int col = -1;
        int[] cords = new OrderVariableHeuristic().chooseVariable(arrayVariables);
        row = cords[0];
        col = cords[1];
        if (row == -1) {
            logger.log(arrayVariables, solutionsNr);
            if (solutionsNr == 0) {
                firstBacktracksNr++;
                firstSolutionTime = System.currentTimeMillis() - startTime;
            }
            solutionsNr++;
        } else {// else for each-row backtrack
            int row2 = row;
            int col2 = col;
            Variable current = variables.stream().filter(v -> v.getRow() == row2 && v.getColumn() == col2).findFirst().get();
            System.out.println("current: " +current);
//            if (current.getDomain().size() == 0) {
//                System.out.println("domain empty");
//                return false;
//            }
//            System.out.println(current);
            int[] values = new OrderValueHeuristic().chooseValues(current.getDomain().stream().mapToInt(i -> i).toArray());
            System.out.println(Arrays.toString(values));
            for (int num :values) {
//                variables = prev.stream().map(Variable::new).collect(Collectors.toList());
//                System.out.println(prev);
                System.out.println(variables);
                List<Variable> placeholder = variables.stream().map(Variable::new).collect(Collectors.toList());
                placeholder.stream().filter(v -> !Constraint.check(Mapper.mapToArray(placeholder), v.getRow(), v.getColumn(), num)).forEach(v -> v.removeFromDomain(num));
                System.out.println(placeholder);
                List<Integer> sizes = placeholder.stream().map(v -> v.getDomain().size()).collect(Collectors.toList());
//                System.out.println(sizes);
                System.out.println(num);

                System.out.println("current  " +current);
                System.out.println("var  " +variables);
                if (placeholder.stream().map(Variable::getDomain).noneMatch(d -> d.size() == 0)) {
                    for (int i = 0; i < variables.size(); i++) {
                        if (variables.get(i).getRow() == row && variables.get(i).getColumn() == col) {
                            System.out.println(row + " " + col + " " + num);
                            variables.get(i).setValue(num);
                            break;
                        }
                    }
                    variables.stream().filter(v -> !Constraint.check(Mapper.mapToArray(variables), v.getRow(), v.getColumn(), num)).forEach(v -> v.removeFromDomain(num));

                    System.out.println(variables);
                    if (!startAlgorithm()) {
                        System.out.println("hier");
                        current.setValue(0);
                        variables = prev;
//                    startAlgorithm();

                    } else {
                        System.out.println("else");
                        variables = prev;
                    }



                }else{
                    System.out.println("domain empty " + current );

//                    return false;
                }
            }
        }
        System.out.println("koniec");
        return true;
    }

    @Override
    public int[][] findSolution(int instance) {
        int[][] variablesx = Loader.load(instance, path, true);

        variables = Mapper.mapToList(variablesx);

        for (int i = 0; i < variables.size(); i++) {
            for (int i1 = 1; i1 <= 9; i1++) {
                if (!Constraint.check(Mapper.mapToArray(variables), variables.get(i).getRow(), variables.get(i).getColumn(), i1))
                    variables.get(i).removeFromDomain(i1);
            }
        }

        startTime = System.currentTimeMillis();
        startAlgorithm();
        allSolutionsTime = (System.currentTimeMillis() - startTime);
        System.out.println(ile);
        return Mapper.mapToArray(variables);
    }


}
