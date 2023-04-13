/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

/**
 *
 * @author asus-pc
 */
public abstract class Player implements Comparable,PlayerSetup{
    
    private String playerName;
    private int score;
    
    Player(String name){
        playerName = name;
        score = 0;
    }
    Player(PlayerSetup ps){
    
        playerName = ps.getPlayerName();
        score = ps.getScore();
    }
    //override
    public String getPlayerName(){
        return playerName;
    }
    public int getScore(){
        return score;
    }
    
    public int compareTo(Object obj){
    
        Player pl = (Player) obj;
        return this.score - pl.getScore();
    }
    
}
