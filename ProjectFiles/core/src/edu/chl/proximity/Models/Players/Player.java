package edu.chl.proximity.Models.Players;

import edu.chl.proximity.Models.ResourceSystem.Resources;

/**
 * @author Hanna Römer
 * @date 2015-04-15
 * A class epresenting the player. 
 */
public class Player {
    private Resources resources;
    private int level;

    /**
     * Create a new player with starting resources of 300 points,
     * 300 lines and 0 polygons
     */
    public Player(){
        resources=new Resources(300, 300, 0);
    }

    /**
     * Set the player level
     * @param level what the player's level is set to
     */
    public void setLevel(int level){
        this.level=level;
    }

    /**
     * Increase the player's level by one
     */
    public void levelUp(){
        level += 1;
    }

    /**
     * Increases the player's level by a given amount.
     * @param l amount the player's level is to
     *          be increased by
     */
    public void levelUpByAmount(int l){
        level+=l;
    }

    /**
     * Get the player's instance of Resources
     * @return the player's Resources
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * Get the player's current level
     * @return the player's level
     */
    public int getLevel(){
        return level;
    }
}
