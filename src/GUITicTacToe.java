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
	
	private JButton SubmitButton;
	private JPanel GamePanel;
	private JLabel DisplayWinner;
	private JButton[][] TicTacToeBoard=new JButton[size][size];
	private int board[][];
	
	private Player player; 
	private Computer computer;
    private CheckForWinner checkWinner;
    
    private int count=0;
	private int selectedRow=-1;
	private int selectedCol=-1;
	
	
	public GUITicTacToe(int board[][],Player player,Computer computer,CheckForWinner checkWinner){
		//initialize particular attributes of GUI class
		this.player=player;
		this.board=board;
		this.computer=computer;
		this.checkWinner=checkWinner;
		
		GamePanel= new JPanel();  this.setLayout(new BorderLayout(10, 10)); // 10px space between panels
		DisplayWinner=new JLabel("Game started");
		SubmitButton=new RoundedButton("Submit",34);
		SubmitButton.setFont(new Font("Arial", Font.BOLD, 20));
		
		GamePanel.setLayout(new GridLayout(3,3,5,5));
		
		
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
		
	    JPanel bottomPanel=new JPanel();
	    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,10)); //center
	    
        DisplayWinner.setFont(new Font("Arial",Font.BOLD,16));
        
		ButtonListener listener=new ButtonListener();
		SubmitButton.addActionListener(listener);
	
		bottomPanel.add(DisplayWinner);
		bottomPanel.add(SubmitButton);
		
		this.add(GamePanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		
	//action listener for the buttons board
	for (int i=0; i<size; i++) {
	    for (int j=0; j <size; j++) {
	        TicTacToeBoard[i][j].addActionListener(listener);
	    }
	}
	
	
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

	            if (selectedRow!=-1 && selectedCol!=-1) {

	                //player move
	                player.makeMove(board,TicTacToeBoard,selectedRow,selectedCol);
	                count++;

	                // check if player won
	                int[][] winCoords=checkWinner.ThereisNoWinnerKeepPlaying(board);
	                if (winCoords!=null) {
	                    DisplayWinner.setText("Player Won!");
	                    highlightWinningCells(winCoords,Color.GREEN);
	                    SubmitButton.setEnabled(false); // Lock submit
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

	                // check if computer won
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

	                // initilize pointers
	                selectedRow=-1;
	                selectedCol=-1;
	            }
	        } 
	        //a button was pressed from the board of buttons
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