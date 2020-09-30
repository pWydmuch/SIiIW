package si.si_zad3;

import java.util.Collections;
import java.util.List;

public class Minimax  implements Algorithm{

    @Override
    public int getNextMoveColumn(Board board, int maxDepth, boolean isMaximizing) {
        PossibleMove bestMove = findBestMove(board,maxDepth,isMaximizing);
        return bestMove.column;
    }

    public PossibleMove findBestMove(Board board, int depth, boolean maximizingPlayer) {
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
                PossibleMove childPosiibleMove = findBestMove(child, depth - 1,  false);
                if (possibleMove.value < childPosiibleMove.value)
                    possibleMove = childPosiibleMove;

            }
            return possibleMove;
        } else {
            PossibleMove possibleMove = new PossibleMove();
            possibleMove.value = Integer.MAX_VALUE;
            List<Board> children = board.getChildren(Board.MAXIMIZING_PLAYER_SIGN);
            Collections.shuffle(children);
            for (Board child : children) {
                PossibleMove childPosiibleMove = findBestMove(child, depth - 1,  true);
                if (possibleMove.value > childPosiibleMove.value)
                    possibleMove = childPosiibleMove;

            }
            return possibleMove;
        }
    }
}
