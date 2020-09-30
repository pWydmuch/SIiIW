package si.zad2.csp;

public class Constraint {

    public static boolean check(int[][] sudoku, int row, int column, int num) {

            for (int j = 0; j < 9; j++) {

                if (sudoku[row][j] == num) return false;

            }
            for (int i = 0; i < 9; i++) {
                if (sudoku[i][column] == num) return false;
            }
        return isNotInSquare(sudoku,row,column,num);
    }


    private static boolean isNotInSquare(int[][] sudoku, int row, int column, int num) {

        int[][] squere = new int[3][3];
        int[] xIndexes = getIndexes(row);
        int[] yIndexes = getIndexes(column);
        int iSquere = 0;
        int jSquere = 0;
        for (int i = xIndexes[0]; i < xIndexes[1]; i++) {
            for (int j = yIndexes[0]; j < yIndexes[1]; j++) {
                squere[iSquere][jSquere] = sudoku[i][j];
                jSquere++;
            }
            jSquere = 0;
            iSquere++;
        }

        for (int[] currentRow : squere) {
            for (int value : currentRow) {
                if (value == num) return false;
            }
        }
        return true;
    }

    private static int[] getIndexes(int num) {
        int[] indexes = new int[2];
        if (num >= 0 && num < 3) {
            indexes[0] = 0;
            indexes[1] = 3;
        }
        else if (num >= 3 && num < 6) {
            indexes[0] = 3;
            indexes[1] = 6;
        }
        else if (num >= 6 && num < 9) {
            indexes[0] = 6;
            indexes[1] = 9;
        }
        return indexes;
    }

}
