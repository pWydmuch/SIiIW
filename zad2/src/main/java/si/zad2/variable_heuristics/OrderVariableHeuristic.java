package si.zad2.variable_heuristics;

public class OrderVariableHeuristic implements VariableHeuristic {

    private static final int ROWS_NUM =9 ;
    private static final int COLUMNS_NUM =9 ;

    public int[] chooseVariable(int[][] sudoku){
        int row = -1;
        int col = -1;
        loop:
        for (int i = 0; i < ROWS_NUM; i++) {
            for (int j = 0; j < COLUMNS_NUM; j++) {
                if (sudoku[i][j] == 0) {
                    row = i;
                    col = j;
                    break loop;
                }
            }
        }
        return new int[]{row,col};
    }


}
