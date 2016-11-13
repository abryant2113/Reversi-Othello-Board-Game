/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;

/**
 *
 * @author Austin
 */
public class Player {
    //variables that describe each player
    private String name;
    private Color discColor;
    private int score;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the discColor
     */
    public Color getDiscColor() {
        return discColor;
    }

    /**
     * @param discColor the discColor to set
     */
    public void setDiscColor(Color discColor) {
        this.discColor = discColor;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
}
