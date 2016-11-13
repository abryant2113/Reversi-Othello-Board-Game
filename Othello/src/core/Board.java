/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Austin
 */
public class Board {
    
    private Disc[][] board;
    private int darkCount;
    private int lightCount;
    private ArrayList<Player> players;
    private final Game game;
    
    public Board(Game game){
        this.game = game;
        //constructor for the class board
        initObjects();
    }

    /**
     * @return the board
     */
    public Disc[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Disc[][] board) {
        this.board = board;
    }
    
    private void initObjects(){
        
        //instantiate the member variable board
        board = new Disc[Constants.ROWS][Constants.COLUMNS];
        
        int i, j;
        
        //loops through the board and creates a disc object for each spot on the board
        for(i=0; i<Constants.ROWS; i++){
            for(j=0; j<Constants.COLUMNS; j++){
                board[i][j] = new Disc();
            }
        }
        
        //sets up the initial state of the board
        board[3][3].setDiscolor(Constants.LIGHT);
        board[3][4].setDiscolor(Constants.DARK);
        board[4][3].setDiscolor(Constants.DARK);
        board[4][4].setDiscolor(Constants.LIGHT);
        
    }

    public void calculateScore(){
        
        int k, l;
        
        for(k=0; k<Constants.ROWS; k++){
            for(l=0; l<Constants.COLUMNS; l++){
                //checks the color of the current tile, and increments the counter variable accordingly
                if(board[k][l].getDiscolor() == Constants.DARK){
                    darkCount++;
                }
                //if the current tile isn't dark, incrememnt lightCount
                else if(board[k][l].getDiscolor() == Constants.LIGHT){
                    lightCount++;
                }
            }
        }
        //code to update scores of players
        players.get(Constants.PLAYER_ONE).setScore(darkCount);
        players.get(Constants.PLAYER_TWO).setScore(lightCount);
        darkCount = 0;
        lightCount = 0;
    }
    
    public boolean isValidMove(int row, int col, Color color, int check){
        
        //boolean variable to monitor the validity of the move
        boolean result = false;
        
       //if there is already a color in this spot, you can not play here.
        if(board[row][col].getDiscolor() != Constants.EMPTY && check == Constants.CHECK_NO){
            gameOver();
            return result;
        }
        
        //checks all of the possible positions for a valid move
        if(checkUp(row, col, color, check)){
            result = true;
        }
        if(checkUpLeft(row, col, color, check)){
            result = true;
        }
        if(checkLeft(row, col, color, check)){
            result = true;
        }
        if(checkDownLeft(row, col, color, check)){
            result = true;
        }
        if(checkDown(row, col, color, check)){
            result = true;
        }
        if(checkDownRight(row, col, color, check)){
            result = true;
        }
        if(checkRight(row, col, color, check)){
            result = true;
        }
        if(checkUpRight(row, col, color, check)){
            result = true;
        }
        
        
        //if the move is valid, update the current board space to have the color of the pla
        if(result == true && check == Constants.CHECK_NO)
            board[row][col].setDiscolor(color);
        
        //updates the current score
        calculateScore();
        
        //checks to see if the game is over, but only if we aren't checking for valid moves
        if(check == Constants.CHECK_NO && gameOver() == true){
            //if player one score higher, show that they won
            if(players.get(Constants.PLAYER_ONE).getScore() > players.get(Constants.PLAYER_TWO).getScore()){
                JOptionPane.showMessageDialog(null, players.get(Constants.PLAYER_ONE).getName() + " wins!");
                System.exit(0);
            }
            //if player two scored higher, show that they instead won
            else if(players.get(Constants.PLAYER_TWO).getScore() > players.get(Constants.PLAYER_ONE).getScore()){
                JOptionPane.showMessageDialog(null, players.get(Constants.PLAYER_TWO).getName() + " wins!");
                System.exit(0);
            }
            //if the players tied...
            else{
                JOptionPane.showMessageDialog(null, "Both players tied with the same number of disks!");
                System.exit(0);
            }
        }
        return result;
    }
    
    public boolean checkUp(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkRow = row-1;
        boolean sameColor = false;
        boolean isValid = false;
        boolean emptySpace = false;
        
        while(checkRow >= 0 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[checkRow][col].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[checkRow][col].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkRow--;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
            //do while loop
            do{
                row--;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean checkUpLeft(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkRow = row-1;
        int checkCol = col-1;
        boolean sameColor = false;
        boolean isValid = false;
        boolean emptySpace = false;
     
        while(checkRow >= 0 && checkCol >= 0 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[checkRow][checkCol].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[checkRow][checkCol].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkCol--;
            checkRow--;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
           
            //do while loop
            do{
                row--;
                col--;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean checkLeft(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkCol = col-1;
        boolean sameColor = false;
        boolean isValid;
        boolean emptySpace = false;
        
        while(checkCol >= 0 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[row][checkCol].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[row][checkCol].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkCol--;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
            //do while loop
            do{
                col--;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
                
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean checkDownLeft(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkRow = row+1;
        int checkCol = col-1;
        boolean sameColor = false;
        boolean isValid;
        boolean emptySpace = false;
        
        while(checkRow <= 7 && checkCol >=0 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[checkRow][checkCol].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[checkRow][checkCol].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkRow++;
            checkCol--;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
            //do while loop
            do{
                row++;
                col--;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean checkDown(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkRow = row+1;
        boolean sameColor = false;
        boolean isValid;
        boolean emptySpace = false;
        
        while(checkRow <= 7 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[checkRow][col].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[checkRow][col].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkRow++;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
            //do while loop
            do{
                row++;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean checkDownRight(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkRow = row+1;
        int checkCol = col+1;
        boolean sameColor = false;
        boolean isValid;
        boolean emptySpace = false;
        
        while(checkRow <= 7 && checkCol <= 7 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[checkRow][checkCol].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[checkRow][checkCol].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkCol++;
            checkRow++;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
            //do while loop
            do{
                row++;
                col++;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean checkRight(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkCol = col+1;
        boolean sameColor = false;
        boolean isValid;
        boolean emptySpace = false;
        
        while(checkCol <= 7 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[row][checkCol].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[row][checkCol].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkCol++;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
            //do while loop
            do{
                col++;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean checkUpRight(int row, int col, Color color, int check){
        
        int squaresFlipped = 0;
        int checkCol = col+1;
        int checkRow = row-1;
        boolean sameColor = false;
        boolean isValid;
        boolean emptySpace = false;
        
        while(checkRow >= 0 && checkCol <= 7 && sameColor == false){
            //checks to see if the current color of the square is null
            if(board[checkRow][checkCol].getDiscolor() == Constants.EMPTY){
                isValid = false;
                emptySpace = true;
            }
            //if the current tile color doesn't equal  the color passed, increment the squaresFlipped variable
            else if(board[checkRow][checkCol].getDiscolor() != color){
                squaresFlipped++;
            }
            else{
                sameColor = true;
            }
            //decrements the looping variable by one
            checkCol++;
            checkRow--;
        }
        
        //if we are simply checking the validity of a move and it is true, return true without updating discs
        if(check == Constants.CHECK_YES && sameColor == true && squaresFlipped > 0 && emptySpace == false){
            return true;
        }
        
        if(sameColor == true && squaresFlipped > 0 && emptySpace == false){
            //do while loop
            do{
                row--;
                col++;
                squaresFlipped--;
                board[row][col].setDiscolor(color);
            }while(squaresFlipped > 0);
            //updates the isValid variable
            isValid = true;
        }
        //otherwise, set the isValid variable to false
        else{
            isValid = false;
        }
        return isValid;
    }
    
    public boolean gameOver(){
        
        int a, b;
        boolean gameOver= true;
        Color currentColor = game.getCurrentPlayer().getDiscColor();
      
        //checks to see if all of the tiles are filled
        for(a=0; a<Constants.ROWS; a++){
            for(b=0; b<Constants.COLUMNS; b++){
                //if there are still available locations to play a disc, the game will continue
                if(board[a][b].getDiscolor() == Constants.EMPTY){
                    if(isValidMove(a, b, currentColor, Constants.CHECK_YES)){
                        return false;
                    }
                }
            }
        }
        
        
        //changes the current player and then checks if the other player can perform any moves
        changePlayer();
        
        //if it gets to this point, switch players and check to see if they can make a move, if they
        //can't, then the game is over.
        //checks to see if all of the tiles are filled
        for(a=0; a<Constants.ROWS; a++){
            for(b=0; b<Constants.COLUMNS; b++){
                //if there are still available locations to play a disc, the game will continue
                if(board[a][b].getDiscolor() == Constants.EMPTY){
                    if(isValidMove(a, b, currentColor, Constants.CHECK_YES)){
                        //the game is not over, so now we allow the other player to move
                        return false;
                    }
                }
            }
        }
        
        //if we reach this point, then the game is over and we should announce it
        JOptionPane.showMessageDialog(null, "No more possible moves for either player. Game over.");
        
        //game is over if the method reaches this statement.
        return gameOver;
    }
    
    
    public void changePlayer(){
          //changes the player after a move has ended
            if(game.getCurrentPlayer() == game.getPlayers().get(Constants.PLAYER_ONE)){
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_TWO));
            }else{
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_ONE));
            }
        }
    
    /**
     * @return the darkCount
     */
    public int getDarkCount() {
        return darkCount;
    }

    /**
     * @param darkCount the darkCount to set
     */
    public void setDarkCount(int darkCount) {
        this.darkCount = darkCount;
    }

    /**
     * @return the lightCount
     */
    public int getLightCount() {
        return lightCount;
    }

    /**
     * @param lightCount the lightCount to set
     */
    public void setLightCount(int lightCount) {
        this.lightCount = lightCount;
    }

    /**
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
