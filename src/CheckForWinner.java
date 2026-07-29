
public class CheckForWinner {
	public static int size=3; //board size remain the same 
	
	public int [][] ThereisNoWinnerKeepPlaying(int board[][]) {
		 	int j=0;
		 	
				
			 for(int i=0; i<size; i++) {
				// rows
				if(board[i][j]!=0 && board[i][j]==board[i][j+1] && board[i][j]==board[i][j+2]){ 
				    return new int[][] {{i,j},{i,j+1},{i,j+2}};
				}
				
				//columns
				if(board[j][i]!=0 &&board[j][i]==board[j+1][i] && board[j][i]==board[j+2][i]) {
					return new int[][] {{j,i},{j+1,i},{j+2,i}};
				}
			 }
				//main diagonal
				if(board[j][j]!=0 &&board[j][j]==board[j+1][j+1] && board[j][j]==board[j+2][j+2])
					return new int[][] {{j,j},{j+1,j+1},{j+2,j+2}};
				
				//secondary diagonal
				if(board[j+1][j+1]!=0 &&board[j][j+2]==board[j+1][j+1] && board[j+1][j+1]==board[j+2][j])
					return new int[][] {{j,j+2},{j+1,j+1},{j+2,j}};
				
				return null; //no winner / keep playing 
	
	}
	
	public int CheckForFullBoard(int count) {
		
				 if(count==(size*size)) //means full board game is over
					 return 3;
				  return 1; //keep playing 

		 }
	
}
