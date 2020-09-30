package si.si_zad3;

import java.util.Random;

public class Main {

    ////////////////////

    private void test(Algorithm algorithm, int gamesNr){
        int moveSum =0;
        long startTime = System.currentTimeMillis();
        for (int i=0;i<gamesNr;i++){
             moveSum+=playAGame(algorithm);
        }
        long allSolutionsTime = (System.currentTimeMillis() - startTime);
        System.out.println("Sredni czas dla "+ gamesNr + " rozgrywek dla algorytmu "+ algorithm.getClass().getSimpleName() + ": " + (double)(allSolutionsTime/gamesNr));
        System.out.println("Srednia ilosc ruchow gracza wygrywajacego dla "+ gamesNr + " rozgrywek dla algorytmu "+ algorithm.getClass().getSimpleName() + ": " + (double)(moveSum/gamesNr));
    }

    private int playAGame(Algorithm algorithm){
        GameEngine firstPlayer;
        GameEngine secondPlayer;
        int winnerMoves =0;
        int firstPlayerMoves = 0;
        int secondPlayerMoves = 0;
        Board board = new Board();
        if(Math.random() <= 0.5){
            firstPlayer = new GameEngine(true,algorithm);
            secondPlayer = new GameEngine(false,algorithm);
        }else{
            firstPlayer = new GameEngine(false,algorithm);
            secondPlayer = new GameEngine(true,algorithm);
        }

        while (!board.isWinningPlay() && !board.checkIfGirdIsFull()) {
            int bestCol1 = firstPlayer.getNextMoveColumn(board);
            board.makeMove(bestCol1, firstPlayer.getPlayerSign());
            firstPlayerMoves++;
            System.out.println("Gracz " + firstPlayer.getPlayerSign() +" wybiera kolumnę "+(bestCol1+1));
//            board.print();
            if (!board.isWinningPlay()) {
                int bestCol2 = secondPlayer.getNextMoveColumn(board);
                board.makeMove(bestCol2, secondPlayer.getPlayerSign());
                secondPlayerMoves++;
                System.out.println("Gracz " + secondPlayer.getPlayerSign() +" wybiera kolumnę "+(bestCol2+1));
//                board.print();
            }
        }
        if (board.checkIfGirdIsFull() && !board.isWinningPlay()) {
            System.out.println("Remis");
            winnerMoves = firstPlayerMoves;
        } else {
            System.out.println("Zwycięzcą jest " + board.getWinner());
            if(firstPlayer.getPlayerSign()==board.getWinner()) winnerMoves = firstPlayerMoves;
            else winnerMoves = secondPlayerMoves;
        }
    return winnerMoves;

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.test(new AlphaBeta(),50);
    }
}