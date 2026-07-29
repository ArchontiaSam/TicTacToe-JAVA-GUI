
public class Main {
	public static int size=3; //board size remain the same 
	
	public static void main(String[] args) {
		 
		int board[][]=new int [size][size]; //creation of Board array. It contains player and computer moves.
		
		for(int i=0; i<size; i++) 
			for(int j=0; j<size; j++)
			 board[i][j]=0;    //initialization board that hold moves to zero
		
		
		//1 stands for player and 2 for computer
		
		Player player= new Player(); //create both objects 
		Computer computer = new Computer();
		CheckForWinner checkWinner= new CheckForWinner();
	//		//Game starts
		GUITicTacToe gui=new GUITicTacToe(board,player,computer,checkWinner);
		

		
	}

}
