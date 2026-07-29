import javax.swing.JButton;

public class Computer {
	 public static int size=2;

	public void makeMove(int[][] board, JButton[][] ticTacToeBoard) {

		
			int []winningMove = findCriticalCell(board,2);
			
			if(winningMove!=null) {
				applyMove(board,ticTacToeBoard,winningMove[0],winningMove[1]);
				return;
			}
			// Defence, checking if player is going to win and block him
	        int[] blockingMove = findCriticalCell(board,1);
	        if (blockingMove!=null) {
	            applyMove(board,ticTacToeBoard,blockingMove[0],blockingMove[1]);
	            return;
	        }

	        // center, take the center if its empty
	        if (board[1][1]==0) {
	            applyMove(board,ticTacToeBoard,1,1);
	            return;
	        }

	        // otherwise play randomly
	        for (int i=0; i<3; i++) {
	            for (int j=0; j<3; j++) {
	                if (board[i][j]==0) {
	                    applyMove(board, ticTacToeBoard,i,j);
	                    return;
	                }
	            }
	        }
	    }
		
			private int[] findCriticalCell(int[][] board, int playerSymbol) {
		 
				int result[];
				int j=0;
					for(int i=0; i<size; i++) {
						//row
						result=getEmptyIndex(board,i,j,i,j+1,i,j+2,playerSymbol);
							if(result!=null)
								return result;
						//column
						result=getEmptyIndex(board,j,i,j+1,i,j+2,i,playerSymbol);
							if(result!=null)
								return result;
					}
						//main diagonal
						result=getEmptyIndex(board,j,j,j+1,j+1,j+2,j+2,playerSymbol);
							if(result!=null)
								return result;
						//secondary diagonal
							result=getEmptyIndex(board,j,j+2,j+1,j+1,j+2,j,playerSymbol);
							if(result!=null)
								return result;
						
						
				return null;
	}

			private int[] getEmptyIndex(int[][] board,int r1,int c1,int r2,int c2,int r3,int c3,int playerSymbol) {
			    // if there is an empty cell and the other two are moves of the player 
			    if (board[r1][c1] + board[r2][c2] + board[r3][c3] == 2 * playerSymbol) {
			        // return position of the empty cell
			        if(board[r1][c1]==0) return new int[]{r1,c1};
			        if(board[r2][c2]==0) return new int[]{r2,c2};
			        if(board[r3][c3]==0) return new int[]{r3,c3};
			    }
			    return null; //when there is no situation like this
			}
			
			//Update board and GUI
		    private void applyMove(int[][] board, JButton[][] ticTacToeBoard, int row, int col) {
		        board[row][col] = 2; //update board
		        ticTacToeBoard[row][col].setText("O"); //Update GUI
		        ticTacToeBoard[row][col].setEnabled(false); //disable button
		    }

}
