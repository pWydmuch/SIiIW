package si.si_zad3;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class GameEngine {

    private static final int MAX_DEPTH = 2;
    private Board board;
    private boolean isMaximizing;
    private char playerSign;
    Algorithm algorithm;


    public GameEngine() {
        this.isMaximizing = true;
        algorithm = new AlphaBeta();
    }

    public GameEngine(boolean isMaximizing, Algorithm algorithm) {
        this.isMaximizing = isMaximizing;
        this.algorithm =algorithm;
        if (isMaximizing){
            playerSign = Board.MAXIMIZING_PLAYER_SIGN;
        }else{
            playerSign = Board.MINIMIZING_PLAYER_SIGN;
        }
    }

    public char getPlayerSign() {
        return playerSign;
    }

    @FunctionalInterface
    private interface BoardPredicate{
        boolean check(char[][] boardGrid,int row,int column);
    }
    @FunctionalInterface
    private interface MovePredicate{
        boolean check(int row,int column);
    }

    public static int evaluationFunction(Board currentBoardState){
        int xPoints =0;
        int oPoints =0;
        if (currentBoardState.isWinningPlay()){
            char winner = currentBoardState.getWinner();
            if (winner == Board.MINIMIZING_PLAYER_SIGN) xPoints+=100;
            if(winner== Board.MAXIMIZING_PLAYER_SIGN) oPoints+=60;
        }
        xPoints = xPoints + threeInLineNumber(Board.MINIMIZING_PLAYER_SIGN,currentBoardState)*10 +twoInLineNumber(Board.MINIMIZING_PLAYER_SIGN,currentBoardState)*4;
        oPoints = oPoints + threeInLineNumber(Board.MAXIMIZING_PLAYER_SIGN,currentBoardState)*5 + twoInLineNumber(Board.MAXIMIZING_PLAYER_SIGN,currentBoardState);
        return oPoints - xPoints;
    }

    public static int threeInLineNumber(char playerSign,Board board){
        int matchTimes = 0;
        BoardPredicate checkInRow = (gameBoard,i,j) -> gameBoard[i][j] == gameBoard[i][j + 1] && gameBoard[i][j] == gameBoard[i][j + 2] && gameBoard[i][j] == playerSign;
        MovePredicate checkMoveInRow = (row, column) -> Board.canMove(row,column+2);
        matchTimes+=searchTheBoard(checkInRow,checkMoveInRow,board);

        BoardPredicate checkInColumn = (gameBoard,i,j) ->gameBoard[i][j] == gameBoard[i - 1][j] && gameBoard[i][j] == gameBoard[i - 2][j] && gameBoard[i][j] == playerSign;
        MovePredicate checkMoveInColumn = (row, column) -> Board.canMove(row-2,column);
        matchTimes+=searchTheBoard(checkInColumn,checkMoveInColumn,board);

        BoardPredicate checkInAscDiagonal = (gameBoard,i,j) ->gameBoard[i][j] == gameBoard[i + 1][j + 1] && gameBoard[i][j] == gameBoard[i + 2][j + 2] && gameBoard[i][j] == playerSign;
        MovePredicate checkMoveInAscDiagonal = (row, column) -> Board.canMove(row+2,column+2);
        matchTimes+=searchTheBoard(checkInAscDiagonal,checkMoveInAscDiagonal,board);

        BoardPredicate checkInDescDiagonal = (gameBoard,i,j) ->gameBoard[i][j] == gameBoard[i - 1][j + 1] && gameBoard[i][j] == gameBoard[i - 2][j + 2] && gameBoard[i][j] == playerSign ;
        MovePredicate checkMoveInDescDiagonal = (row, column) -> Board.canMove(row-2,column+2);
        matchTimes+=searchTheBoard(checkInDescDiagonal,checkMoveInDescDiagonal,board);

        return matchTimes;
    }

    public static int twoInLineNumber(char playerSign,Board board) {
        int matchTimes = 0;
        BoardPredicate checkInRow = (gameBoard,i,j) -> gameBoard[i][j] == gameBoard[i][j + 1] && gameBoard[i][j] == playerSign;
        MovePredicate checkMoveInRow = (row, column) -> Board.canMove(row,column+1);
        matchTimes+=searchTheBoard(checkInRow,checkMoveInRow,board);

        BoardPredicate checkInColumn = (gameBoard,i,j) ->gameBoard[i][j] == gameBoard[i - 1][j] && gameBoard[i][j] == playerSign;
        MovePredicate checkMoveInColumn = (row, column) -> Board.canMove(row-1,column);
        matchTimes+=searchTheBoard(checkInColumn,checkMoveInColumn,board);

        BoardPredicate checkInAscDiagonal = (gameBoard,i,j) ->gameBoard[i][j] == gameBoard[i + 1][j + 1] && gameBoard[i][j] == playerSign;
        MovePredicate checkMoveInAscDiagonal = (row, column) -> Board.canMove(row+1,column+1);
        matchTimes+=searchTheBoard(checkInAscDiagonal,checkMoveInAscDiagonal,board);

        BoardPredicate checkInDescDiagonal = (gameBoard,i,j) ->gameBoard[i][j] == gameBoard[i - 1][j + 1] && gameBoard[i][j] == playerSign ;
        MovePredicate checkMoveInDescDiagonal = (row, column) -> Board.canMove(row-1,column+1);
        matchTimes+=searchTheBoard(checkInDescDiagonal,checkMoveInDescDiagonal,board);

        return matchTimes;
    }

    public static int searchTheBoard(BoardPredicate boardPredicate,MovePredicate movePredicate,Board board) {
        int matchTimes = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (movePredicate.check(i,j)) {
                    if (boardPredicate.check(board.getGrid(),i,j)) {
                        matchTimes++;
                    }
                }
            }
        }
        return matchTimes;
    }

    public int getNextMoveColumn(Board board){
      return  algorithm.getNextMoveColumn(board,MAX_DEPTH,isMaximizing);
    }






}
