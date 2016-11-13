/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Austin
 */
public class Game {
    
    private ArrayList<Player> players;
    private Board board;
    private Player currentPlayer;
    
    //creates the constructor for the game class
    public Game(){
        initObjects();
    }

    
    public void calculateScore(){
        //updates the players current scores
        board.calculateScore();
        players.get(Constants.PLAYER_ONE).setScore(board.getDarkCount());
        players.get(Constants.PLAYER_TWO).setScore(board.getLightCount());
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

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }
    
    public void initObjects(){
        //instantiates the board variable
        board = new Board(this);
        
        //calls the createrplayers and printplayers methods
        createPlayers();
        
        //pass players to the board
        board.setPlayers(players);
        //set the current player
       
        
        currentPlayer = players.get(Constants.PLAYER_ONE);
        
        
        printPlayers();
        
    }
    
    public void createPlayers(){
        //creates a new arraylist object
        players = new ArrayList();
        
        int i;
        //loops through the number of players and generates info for each of them
        for(i=0; i<Constants.MAX_PLAYERS; i++){
            //instructs the program to prompt the player with a dialog box
            String name = JOptionPane.showInputDialog(null, "Enter player's name");
            Player player = new Player();
            player.setName(name);
            //if the player is the first, assign them the dark color
            if(i == Constants.PLAYER_ONE){
                player.setDiscColor(Constants.DARK);
            }
            //otherwise, assign them the white color
            else{
                player.setDiscColor(Constants.LIGHT);
            }
            //adds the player to the current arraylist
            players.add(player);
        }
    }
    
    public void printPlayers(){
        System.out.println("The game has the following players:");
        //for all of the players in the arraylist player, print out their name and corresponding disc color
        for(Player p : players){
            System.out.println("Player " + p.getName() + " is playing disc color " + p.getDiscColor());
        }
    }

    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
