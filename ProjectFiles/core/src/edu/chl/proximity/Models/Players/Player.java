package edu.chl.proximity.Models.Players;

import edu.chl.proximity.Models.Factions.Faction;
import edu.chl.proximity.Models.ResourceSystem.Resources;

/**
<<<<<<< HEAD
 * @author Hanna Römer
 * @date 2015-04-15
 * A class representing the player.
=======
 * @author Hanna R�mer
 * @date 2015-04-15
 * A class epresenting the player.
 *
 * 23/04 Modified by Simon. Adding depth to leveling up
 * 28/04 Modified by Simon. Adding level
>>>>>>> Dev: Adds XP when killing creeps
 */
public class Player {
    private Resources resources;
    private Faction faction;
    private int experiencePoints;
    private double level;

    /**
     * Create a new player with starting resources of 300 points,
     * 300 lines and 0 polygons
     * @param faction the players faction
     */
    public Player(Faction faction){
        resources = new Resources(0, 0, 0);
        this.faction=faction;
    }

    /**
     * Change the players faction
     * @param f the player's new faction
     */
    public void setFacton(Faction f){
        this.faction=f;
    }

    /**
     * Get the player's current faction
     * @return the player's faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Adds xp to the player. this method in turn handles leveling up
     */
    public void addExperiencePoints(int xp) {
        experiencePoints += xp;
        level = (Math.sqrt(100 * (2 * experiencePoints + 25)) + 50)/100;
    }

    /**
     * Get the player's instance of Resources
     * @return the player's Resources
     */
    public Resources getResources() {
        return resources;
    }

    //Returns XP
    public int getExperience() {
        return experiencePoints;
    }
    public double getLevel() {
        return level;
    }
}
