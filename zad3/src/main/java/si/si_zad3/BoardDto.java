package si.si_zad3;

public class BoardDto {
    String[][] grid;
    int checkIfWinner;
    String winner;


    public BoardDto() {
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public int getCheckIfWinner() {
        return checkIfWinner;
    }

    public void setCheckIfWinner(int checkIfWinner) {
        this.checkIfWinner = checkIfWinner;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
