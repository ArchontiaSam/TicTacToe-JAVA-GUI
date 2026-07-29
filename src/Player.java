import javax.swing.JButton;

public class Player {
	private int symbol;      // 1 για τον παίκτη
    private String text;     // "X"

    // Constructor
    public Player() {
        this.symbol = 1;
        this.text = "X";
    }

    // player submit move
    public void makeMove(int[][] board, JButton[][] ticTacToeBoard, int row, int col) {
        //update board
        board[row][col] = this.symbol;
        
        // update Submit button
        ticTacToeBoard[row][col].setText(this.text);
        ticTacToeBoard[row][col].setEnabled(false); // locks button
    }
}
