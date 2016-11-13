/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Constants;
import core.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Austin
 */
public class GameUi extends JPanel{
    
    //creates the JLabel variables
    private JLabel nameOne;
    private JLabel nameTwo;
    private JLabel scoreOne;
    private JLabel scoreTwo;
    private Game game;
    
    //constructor used to automatically creat JPanel when GameUi object is created
    public GameUi(Game game){
        this.game = game;
        initComponents();
    }
    
    private void initComponents(){
                
        //sets the default size and color of the JPanel
        this.setPreferredSize(new Dimension(700, 50));
        this.setMinimumSize(new Dimension(700, 50));
        this.setBackground(Color.LIGHT_GRAY);
        
        //creates the first disc
        ImageIcon discOne = new ImageIcon(getClass().getResource("/images/blackDisc.png"));
        discOne = imageResize(discOne);
        
        //sets the label for the first player
        nameOne = new JLabel();
        nameOne.setIcon(discOne);
        nameOne.setText(game.getPlayers().get(Constants.PLAYER_ONE).getName());
        nameOne.setMinimumSize(new Dimension(100, 50));
        nameOne.setPreferredSize(new Dimension(100, 50));
        nameOne.setFont(new Font("Serif", Font.BOLD, 12));
        
        //sets the score label for the first player
        setScoreOne(new JLabel());
        getScoreOne().setText(game.getPlayers().get(Constants.PLAYER_ONE).getName());
        getScoreOne().setMinimumSize(new Dimension(100, 50));
        getScoreOne().setPreferredSize(new Dimension(100, 50));
        
        //creates the second disc
        ImageIcon discTwo = new ImageIcon(getClass().getResource("/images/whiteDisc.png"));
        discTwo = imageResize(discTwo);
        
        //sets the name label for the second player
        nameTwo = new JLabel();
        nameTwo.setIcon(discTwo);
        nameTwo.setText(game.getPlayers().get(Constants.PLAYER_TWO).getName());
        nameTwo.setFont(new Font("Serif", Font.BOLD, 12));
        nameTwo.setMinimumSize(new Dimension(150, 50));
        nameTwo.setPreferredSize(new Dimension(150, 50));
              
        //sets the score label for the second player
        setScoreTwo(new JLabel());
        getScoreTwo().setText(game.getPlayers().get(Constants.PLAYER_TWO).getName());
        getScoreTwo().setMinimumSize(new Dimension(100, 50));
        getScoreTwo().setPreferredSize(new Dimension(100, 50));
        
        //adds all of the labels to the JPanel
        this.add(nameOne);
        this.add(getScoreOne());
        this.add(nameTwo);
        this.add(getScoreTwo());
        
        
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    private ImageIcon imageResize(ImageIcon icon){
            
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }

    /**
     * @return the scoreOne
     */
    public JLabel getScoreOne() {
        return scoreOne;
    }

    /**
     * @param scoreOne the scoreOne to set
     */
    public void setScoreOne(JLabel scoreOne) {
        this.scoreOne = scoreOne;
    }

    /**
     * @return the scoreTwo
     */
    public JLabel getScoreTwo() {
        return scoreTwo;
    }

    /**
     * @param scoreTwo the scoreTwo to set
     */
    public void setScoreTwo(JLabel scoreTwo) {
        this.scoreTwo = scoreTwo;
    }
    
}
