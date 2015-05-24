package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

/**
 * @author Linda Evaldsson
 * @date 2015-05-24
 *
 * A class for handling cooldowns in the game
 *
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class Cooldown {

    private int maxCooldown;
    private int currentCooldown = 0;

    public Cooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public boolean isReady() {
        return currentCooldown == 0;
    }

    public int getCooldownPercent() {
        return 100 - ((currentCooldown*100) / maxCooldown);
    }

    public void startCooldown() {
        currentCooldown = maxCooldown;
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public void update() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

}
