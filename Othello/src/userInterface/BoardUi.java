/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Board;
import core.Constants;
import core.Disc;
import core.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Austin
 */
public class BoardUi extends JPanel {
    
    JButton[][] board;
    BoardListener listener;
    Game game;
    GameUi gameUi;
    
    public BoardUi(Game game, GameUi gameUi){
        this.game = game;
        this.gameUi = gameUi;
        initComponents();
        listener.updateUi();
    }
    
    private void initComponents(){
        
        //creates the default size of the BoardUi
        this.setPreferredSize(new Dimension(800, 300));
        this.setMinimumSize(new Dimension(800, 300));
        this.setLayout(new GridLayout(8, 8));
       
        //creates the JButton array
        board = new JButton[8][8];
        
        //creates a new BoardListener object
        listener = new BoardListener();
        
        int i, j;
        
        //loops through each spot on the board and gives each box different properties
        for(i = 0; i<8; i++){
            for(j=0; j<8; j++){
                
                //Instantiates each of the boxes on the board
                board[i][j] = new JButton();
                
                //adds the client property to each individual button of the button array
                board[i][j].putClientProperty("row", i);
                board[i][j].putClientProperty("col", j);
                board[i][j].putClientProperty("color", Constants.EMPTY);
                board[i][j].setBackground(Color.PINK);
                board[i][j].addActionListener(listener);
                
                //adds each button to the BoardUi
                this.add(board[i][j]);
            }
        }
        
        
    }
    
    private class BoardListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            //if the button clicked is a button, then execute this code
          
            if(ae.getSource() instanceof JButton){
                //grabs the location of the selected button
                JButton button = (JButton) ae.getSource();
                int row = (int)button.getClientProperty("row");
                int col = (int)button.getClientProperty("col");
                Color color = (Color)button.getClientProperty("color");
                
                //if a color hasn't been filled on this button, post the black disc image
                if(isValidMove(row, col, game.getCurrentPlayer().getDiscColor())){
                    updateUi();
                    changePlayer();
                }
                else{
                    JOptionPane.showMessageDialog(button, "Move is not valid, select another.");
                }
                
            }
        }
        
        public void updateUi(){
            
            //gets the array of the discs
            Disc [][] discs = game.getBoard().getBoard();
            ImageIcon disc =  null;
            
            int j, k;
            
            for(j=0; j<Constants.ROWS; j++){
                for(k=0; k<Constants.COLUMNS; k++){
                    //checks the color of the button
                    if(discs[j][k].getDiscolor() == Constants.DARK){
                        disc = new ImageIcon(getClass().getResource("/images/blackDisc.png"));
                        disc = imageResize(disc);
                        board[j][k].setIcon(disc);
                    }
                    else if(discs[j][k].getDiscolor() == Constants.LIGHT){
                        disc = new ImageIcon(getClass().getResource("/images/whiteDisc.png"));
                        disc = imageResize(disc);
                        board[j][k].setIcon(disc);
                    }
                }
            }
            //updates the score of both of the players
            gameUi.getScoreOne().setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_ONE).getScore()));
            gameUi.getScoreTwo().setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_TWO).getScore()));
        }
        
        public boolean isValidMove(int row, int col, Color color){
            
            boolean isValid = false;
            //creates a new object of class board
            Board board = new Board(game);
            
            //if there is no color in the spot, this isn't a valid move
            if(color == Constants.EMPTY){
                isValid = false;
            }
            //calls the isValidMove
            if(game.getBoard().isValidMove(row, col, color, Constants.CHECK_NO)){
                isValid = true;
            }
            return isValid;
        }
        
        public void changePlayer(){
            //changes the player after a move has ended
            if(game.getCurrentPlayer() == game.getPlayers().get(Constants.PLAYER_ONE)){
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_TWO));
            }else{
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_ONE));
            }
        }
        
        private ImageIcon imageResize(ImageIcon icon){
            
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            return icon;
        }
        
    }
    
}
