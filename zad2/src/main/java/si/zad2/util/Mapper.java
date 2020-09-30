package si.zad2.util;

import si.zad2.csp.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mapper {
    static List<Integer>  domain = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList());

    public static List<Variable> mapToList(int[][] points) {
        List<Variable> variableList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (points[i][j] == 0)
                    variableList.add(new Variable(i, j, points[i][j], domain));
                else
                    variableList.add(new Variable(i, j, points[i][j], Collections.singletonList(points[i][j])));
            }
        }
        return variableList;
    }

    public static  int[][] mapToArray(List<Variable> variables) {
        int[][] array = new int[9][9];
        for (Variable variable : variables) {
            array[variable.getRow()][variable.getColumn()] = variable.getValue();
        }

        return array;
    }


}
