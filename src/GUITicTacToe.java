import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GUITicTacToe extends JFrame implements ActionListener{
	int size=3;
	
	private JButton SubmitButton; //button for player to submit his move
	private JPanel GamePanel;  //panel where the game stands
	private JLabel DisplayWinner; //label for displaying texts when game is up
	private JButton[][] TicTacToeBoard=new JButton[size][size]; //buttons for player to play tictactoe
	private int board[][];  //board to hold moves 1 is for player and 2 is for computer 0 is when cell is empty
	
	private Player player; //this object stands for player
	private Computer computer; //this object stands for computer
    private CheckForWinner checkWinner; //this object checks for winners or when there is no space left to end the game
    
    private int count=0;
	private int selectedRow=-1; //pointers
	private int selectedCol=-1;
	
	
	public GUITicTacToe(int board[][],Player player,Computer computer,CheckForWinner checkWinner){
		//initialize particular attributes of GUI class
		this.player=player;
		this.board=board;
		this.computer=computer;
		this.checkWinner=checkWinner;
		
		GamePanel= new JPanel();  this.setLayout(new BorderLayout(10, 10)); // 10px space between panels
		DisplayWinner=new JLabel("Game started"); //display text
		SubmitButton=new RoundedButton("Submit",34); //put a title on the button
		SubmitButton.setFont(new Font("Arial", Font.BOLD, 20)); //adding font to button
		
		GamePanel.setLayout(new GridLayout(3,3,5,5)); //add a layout inside the panel in order to put everything 
		
		
		//border line
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK,2);
		
		// Initialize every button of board TicTacToeBoard
	    for (int i=0; i<size; i++) {
	        for (int j=0; j<size; j++) {
	            if (TicTacToeBoard[i][j]==null) {
	            	
	                TicTacToeBoard[i][j] =new JButton("");
	                TicTacToeBoard[i][j].setPreferredSize(new Dimension(80,80));
	                TicTacToeBoard[i][j].setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,34));
	                TicTacToeBoard[i][j].setBorder(lineBorder);
	                GamePanel.add(TicTacToeBoard[i][j]); // add to panel
	            }
	        }
	    }
		
	    JPanel bottomPanel=new JPanel();  //create bottom panel for button and label
	    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,10)); //center it
	    
        DisplayWinner.setFont(new Font("Arial",Font.BOLD,16));
        
		ButtonListener listener=new ButtonListener(); //add button listener to make moves
		SubmitButton.addActionListener(listener);
	
		bottomPanel.add(DisplayWinner); //add label and button to bottom panel
		bottomPanel.add(SubmitButton);
		
		this.add(GamePanel,BorderLayout.CENTER); //add borders
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		
	//action listener for the buttons board
	for (int i=0; i<size; i++) {
	    for (int j=0; j <size; j++) {
	        TicTacToeBoard[i][j].addActionListener(listener);
	    }
	}
	
	//set some essential info for the frame
	this.setVisible(true);
	this.setSize(450, 520);
	this.setLocationRelativeTo(null);
	this.setTitle("TicTacToe Game");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        //empty
    }
	
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//when submit button is pressed 
	        if (e.getSource()==SubmitButton) {
	        	//when game has just started
	            if (selectedRow!=-1 && selectedCol!=-1) {

	                //player move
	                player.makeMove(board,TicTacToeBoard,selectedRow,selectedCol);
	                count++;

	                //check if player won
	                int[][] winCoords=checkWinner.ThereisNoWinnerKeepPlaying(board);
	                if (winCoords!=null) {
	                    DisplayWinner.setText("Player Won!");
	                    highlightWinningCells(winCoords,Color.GREEN);
	                    SubmitButton.setEnabled(false); //Lock submit
	                    return;
	                }

	                //check if there is a draw
	                if (checkWinner.CheckForFullBoard(count)==3) {
	                    DisplayWinner.setText("Game Over! It's a Draw!");
	                    SubmitButton.setEnabled(false);
	                    return;
	                }

	                //computer move
	                computer.makeMove(board,TicTacToeBoard);
	                count++;

	                //check if computer won
	                winCoords=checkWinner.ThereisNoWinnerKeepPlaying(board);
	                if (winCoords!=null) {
	                    DisplayWinner.setText("Computer Won!");
	                    highlightWinningCells(winCoords,Color.RED);
	                    SubmitButton.setEnabled(false);
	                    return;
	                }

	                //check for a draw
	                if (checkWinner.CheckForFullBoard(count)==3) {
	                    DisplayWinner.setText("Game Over! It's a Draw!");
	                    SubmitButton.setEnabled(false);
	                    return;
	                }

	                //initialize pointers 
	                selectedRow=-1;
	                selectedCol=-1;
	            }
	        } 
	        //a button was pressed from the board of buttons so there is a possible move
	        else {
	            for(int i=0; i<size; i++) {
	                for(int j=0; j<size; j++) {
	                    //check if the button was pressed and if it is still empty
	                    if(e.getSource()==TicTacToeBoard[i][j]&&board[i][j]==0) {

	                        // clear the button if player wants to pick a different one
	                        if(selectedRow!=-1 && selectedCol!=-1 && board[selectedRow][selectedCol]==0) {
	                            TicTacToeBoard[selectedRow][selectedCol].setText("");
	                        }

	                        // update players local move
	                        selectedRow=i;
	                        selectedCol=j;
	                        TicTacToeBoard[i][j].setText("X"); 
	                    }
	                }
	            }
	        }
	    }
	}

	//this function colors the cells with a given color based on the coordinators
	private void highlightWinningCells(int[][] winCoords, Color color) {
		    for (int[] c: winCoords) {
		        TicTacToeBoard[c[0]][c[1]].setBackground(color);
		        TicTacToeBoard[c[0]][c[1]].setOpaque(true);
		    }
		}
		
	}