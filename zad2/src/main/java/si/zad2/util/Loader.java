package si.zad2.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private final static int ROWS_NUM = 9;
    private final static int COLUMNS_NUM = 9;
//    String[] linie;


    public static int[][] load(int sudokuBoardNum, String path, boolean header) {
        int [][] numbers = new int[9][9];
        try(BufferedReader reader = new BufferedReader(
                new FileReader(path))){
            String line = null;
            boolean isLineNode = false;
            if (header)line = reader.readLine();
            for(int i=0;i<sudokuBoardNum;i++) {
                line = reader.readLine();
            }
            String[] columns = line.split(";");
            String num = columns[2];
            char[] carArr = num.toCharArray();
            for(int i=0;i<ROWS_NUM;i++){
                for(int j=0;j<COLUMNS_NUM;j++){
                    if(carArr[i*COLUMNS_NUM+j] == '.')  numbers[i][j] = 0;
                    else numbers[i][j] = Integer.parseInt(String.valueOf(carArr[i*COLUMNS_NUM+j]));
                }
            }
        }catch(Exception ex) {ex.printStackTrace();}
        return numbers;
    }

    public static int[][][] loadAllSolutions(int solutionsNr,String path){
//        List<Integer[][]> solutions = new ArrayList<>();
        int [][][] solutions = new int [solutionsNr][][];
        for(int i = 0; i<solutionsNr;i++){
            solutions[i] = load(i+1,path,false);
        }
        return solutions;
    }

}


