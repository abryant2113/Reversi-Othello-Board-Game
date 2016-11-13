/*
    Created by: Austin Bryant
    Class: COP 3330-16 FALL 0001
    Instructor: Karin Whiting
    Date: 9/29/2016
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import core.Game;
import userInterface.OthelloUi;

/**
 *
 * @author Austin
 */
public class Othello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //creates a new game object
        Game game = new Game();
        OthelloUi othello = new OthelloUi(game);
    }
    
}
