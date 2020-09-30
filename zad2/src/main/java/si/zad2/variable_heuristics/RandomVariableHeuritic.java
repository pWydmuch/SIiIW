package si.zad2.variable_heuristics;

import java.util.Random;

public class RandomVariableHeuritic implements VariableHeuristic {
    private static final int ROWS_NUM =9 ;
    private static final int COLUMNS_NUM =9 ;

    @Override
    public int[] chooseVariable(int[][] sudoku){
        int zeros = 0;
        for (int[] ints : sudoku) {
            for (int anInt : ints) {
                if(anInt==0) zeros++;
            }
        }
        Random random = new Random();
        int row = random.nextInt(sudoku.length);
        int col = random.nextInt(sudoku.length);
        int i =0;
        while(sudoku[row][col]!=0){
             row = random.nextInt(sudoku.length);
             col = random.nextInt(sudoku.length);
             i++;
             if(i>ROWS_NUM*COLUMNS_NUM){
                  row = -1;
                  col = -1;
                 break;
             }
        }
//        System.out.println(zeros +" "+ i +" " +col +" "+ row);

        return new int[]{row,col};
    }
}
