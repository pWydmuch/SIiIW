package si.si_zad3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class MyController {

    @Autowired
    Board board;

    @Autowired
    GameEngine gameEngine;
//
//    @GetMapping("/")
//    public String getIndex() {
//
//        return "index";
//    }

    @GetMapping("/reset")
    public void reset(){
        board = new Board();
    }

    @GetMapping("/{player}/{column}")
    @ResponseBody
    public BoardDto get(@PathVariable char player,@PathVariable int column) {

        BoardDto boardDto = new BoardDto();
        board.makeMove(column,Board.MAXIMIZING_PLAYER_SIGN);
        if (board.isWinningPlay()){
            boardDto.setCheckIfWinner(1);
            char winner = board.getWinner();
            if (winner=='X') boardDto.winner="black";
            if (winner=='O') boardDto.winner="red";
        }else{
            boardDto.setCheckIfWinner(0);
        }

        if(!board.isWinningPlay()) {
            int bestCol = gameEngine.getNextMoveColumn(board);
            board.makeMove(bestCol, Board.MINIMIZING_PLAYER_SIGN);
            System.out.println(board);
        }

//        boardDto.setIsWinner(board.isWinningPlay());
        if(boardDto.checkIfWinner==0) {
            if (board.isWinningPlay()) {
                boardDto.setCheckIfWinner(1);
                char winner = board.getWinner();
                if (winner == 'X') boardDto.winner = "black";
                if (winner == 'O') boardDto.winner = "red";
            } else {
                boardDto.setCheckIfWinner(0);
            }
        }
        boardDto.setGrid(board.transform());
        return boardDto;
    }

}
