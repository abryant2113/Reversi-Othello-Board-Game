/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Austin
 */
public class OthelloUi extends JFrame {
    
    Game game;
    GameUi gameUi;
    BoardUi boardUi;
    
    public OthelloUi(Game game){
        this.game = game;
        initComponents();
    }
    
    private void initComponents(){
        
        // creates the frame and sets the dimensions of it
        this.setPreferredSize(new Dimension(600, 600));
        this.setMinimumSize(new Dimension(600,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //game = new Game();
        
        //creates the two instances of the classes gameUi and BoardUi
        gameUi = new GameUi(game);
        boardUi = new BoardUi(game, gameUi);
        
        //adds the objects to the actual frame
        this.add(boardUi, BorderLayout.CENTER);
        this.add(gameUi, BorderLayout.NORTH);
        
        //makes the actual frame visible
        this.setVisible(true);
        
    }
}
