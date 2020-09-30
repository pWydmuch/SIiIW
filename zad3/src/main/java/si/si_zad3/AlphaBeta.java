package si.si_zad3;

import java.util.Collections;
import java.util.List;

public class AlphaBeta implements Algorithm {

    @Override
    public int getNextMoveColumn(Board board,int maxDepth,boolean isMaximizing) {
        PossibleMove bestMove = findBestMove(board,maxDepth,Integer.MIN_VALUE,Integer.MAX_VALUE,isMaximizing);

        return bestMove.column;
    }

    private PossibleMove findBestMove(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            PossibleMove possibleMove = new PossibleMove();
            possibleMove.value = GameEngine.evaluationFunction(board);
            possibleMove.column = board.getLastColumnNumber();
//            System.out.println(possibleMove.column +" "+ possibleMove.value);
            return possibleMove;
        }
        if (maximizingPlayer) {
            PossibleMove possibleMove = new PossibleMove();
            possibleMove.value = Integer.MIN_VALUE;
            List<Board> children = board.getChildren(Board.MINIMIZING_PLAYER_SIGN);
            Collections.shuffle(children);
            for (Board child : children) {
                PossibleMove childPosiibleMove = findBestMove(child, depth - 1, alpha, beta, false);
                if (possibleMove.value < childPosiibleMove.value)
                    possibleMove = childPosiibleMove;
                alpha = Math.max(alpha, possibleMove.value);
                if (alpha >= beta) break;
            }
            return possibleMove;
        } else {
            PossibleMove possibleMove = new PossibleMove();
            possibleMove.value = Integer.MAX_VALUE;
            List<Board> children = board.getChildren(Board.MAXIMIZING_PLAYER_SIGN);
            Collections.shuffle(children);
            for (Board child : children) {
                PossibleMove childPosiibleMove = findBestMove(child, depth - 1, alpha, beta, true);
                if (possibleMove.value > childPosiibleMove.value)
                    possibleMove = childPosiibleMove;
                beta = Math.min(beta, possibleMove.value);
                if (alpha >= beta) break;
            }
            return possibleMove;
        }
    }
}