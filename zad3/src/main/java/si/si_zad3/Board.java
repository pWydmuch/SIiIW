package si.si_zad3;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Board {
    private static final int WIDTH = 7;
    private static final int HEIGHT = 6;
    public static final char MINIMIZING_PLAYER_SIGN = 'X';
    public static final char MAXIMIZING_PLAYER_SIGN = 'O';
    private static final char EMPTY ='.';
    private static final char[] PLAYERS = {'O', 'X'};
    private char [][] grid;
    private int lastRow;
    private int lastColumn;
    private char lastPlayerSign = 'O';

    public Board() {
        grid = new char[HEIGHT][];
        lastColumn =-1;
        lastRow=-1;
        for (int i = 0; i< HEIGHT; i++) {
            Arrays.fill(grid[i] = new char[WIDTH],EMPTY);
        }
    }

    public Board(Board board){
        grid = new char[HEIGHT][WIDTH];
        for (int i = 0; i < board.grid.length; i++) {
            for (int j = 0; j < board.grid[i].length; j++) {
                grid[i][j] = board.grid[i][j];
            }
        }
        lastColumn=board.lastColumn;
        lastRow = board.lastRow;
    }

    public char getLastPlayerSign(){
        return lastPlayerSign;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean makeMove(int column, char playerSign){
        if (checkIfColumnIsNotFull(column)){
            int lowestEmptyCell = getLowestEmptyCellIndex(column);
            grid[lowestEmptyCell][column] = playerSign;
            lastRow=lowestEmptyCell;
            lastColumn=column;
            lastPlayerSign = playerSign;
            return true;
        }else{
            System.out.println("Ta kolumna jest już pełna");
            return false;
        }
    }

    public boolean checkIfColumnIsNotFull(int column){
        for(int i = HEIGHT -1; i>=0; i--){
            if (grid[i][column]==EMPTY) return true;
        }
        return false;
    }

    private int getLowestEmptyCellIndex(int column){
        for(int i = HEIGHT -1; i>=0; i--){
            if (grid[i][column]==EMPTY) return i;
        }
        return -1;
    }


    public List<Board> getChildren(char playerSign){
       List<Board> children = new ArrayList<>();
        for(int col=0; col<7; col++) {
            if(checkIfColumnIsNotFull(col)) {
                Board child = new Board(this);
                child.makeMove(col, playerSign);
                children.add(child);
            }
        }
        return children;
    }

    public boolean isWinningPlay() {
        if (lastColumn == -1) {
//            System.err.println("No move has been made yet");
            return false;
        }

        char symbol = grid[lastRow][lastColumn];
        // winning streak with the last play symbol
        String streak = String.format("%c%c%c%c", symbol, symbol, symbol, symbol);

        return getLastRow().contains(streak) ||
                getLastColumn().contains(streak) ||
                getLastAscDiagonal().contains(streak)||
                getLastDescDiagonal().contains(streak);
    }

    public char getWinner(){
        if(isWinningPlay()) return grid[lastRow][lastColumn];
        else return EMPTY;
    }

    public static boolean canMove(int row, int col) {
        //We evaluate mainly the limits of the board
        if ((row <= -1) || (col <= -1) || (row > 5) || (col > 6)) {
            return false;
        }
        return true;
    }//end CanMove

    public int getLastColumnNumber(){
        return lastColumn;
    }

    private String getLastColumn(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < HEIGHT; i++) {
            stringBuilder.append(grid[i][lastColumn]);
        }
        return stringBuilder.toString();
    }

    private String getLastRow(){
        return new String(grid[lastRow]);
    }

    private String getLastAscDiagonal(){
        return getLastDiagonal(lastColumn+lastRow);
    }

    private String getLastDescDiagonal(){
        return getLastDiagonal(lastColumn-lastRow);
    }

    private String getLastDiagonal(int lastColRowFactor){
        StringBuilder stringBuilder = new StringBuilder();
        for (int rowNr = 0; rowNr < HEIGHT; rowNr++) {
            int colNr = lastColRowFactor- rowNr;

            if (0 <= colNr && colNr < WIDTH) {
                stringBuilder.append(grid[rowNr][colNr]);
            }
        }
        return stringBuilder.toString();
    }

    public boolean isGameOver(){
        if (isWinningPlay()) return true;
        if (checkIfGirdIsFull()) return true;
        return false;
    }

    public boolean checkIfGirdIsFull(){
        for (int i = 0; i< WIDTH; i++){
            if(checkIfColumnIsNotFull(i)) return false;
        }
        return true;
    }

    @Override
    public String toString() {

       return IntStream.range(1,  WIDTH +1).
                mapToObj(Integer::toString).
                collect(Collectors.joining()) +
                "\n" +
                Arrays.stream(grid).
                        map(String::valueOf).
                        collect(Collectors.joining("\n"));
    }

    void print() {
        System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        System.out.println("-----------------------------");
        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                if (grid[i][j] == 'X') {
                    System.out.print("| " + "X "); //Blue for user
                } else if (grid[i][j] == 'O') {
                    System.out.print("| " + "O "); //Red for computer
                } else {
                    System.out.print("| " + "-" + " ");
                }
            }
            System.out.println("|"); //End of each row
        }
    }

    public String[][] transform(){
        String[][] boardStringArr = new String[HEIGHT][WIDTH];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if (grid[i][j]=='.') boardStringArr[i][j]="0";
                if (grid[i][j]==MINIMIZING_PLAYER_SIGN) boardStringArr[i][j]="black";
                if (grid[i][j]==MAXIMIZING_PLAYER_SIGN) boardStringArr[i][j]="red";
            }
        }
        return boardStringArr;
    }

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
             int moves = HEIGHT * WIDTH;

            Board board = new Board();

//            System.out.println("Use 0-" + (WIDTH - 1) + " to choose a column");

            System.out.println(board);

            for (int player = 0; moves-- > 0; player = 1 - player) {

                char symbol = PLAYERS[player];
                System.out.println("Wybierz kolumne");
                int col = input.nextInt()-1;
                // we ask user to choose a column
                board.makeMove(col,symbol);
                System.out.println(board.lastColumn);
                System.out.println(board);
                if (board.isWinningPlay()) {
                    System.out.println("\nWygrywa gracz: " );
                    return;
                }
            }

            System.out.println("Remis");
        }
    }
}
