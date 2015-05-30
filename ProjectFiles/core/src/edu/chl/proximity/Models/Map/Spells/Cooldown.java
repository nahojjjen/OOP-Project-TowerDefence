package edu.chl.proximity.Models.Map.Spells;

/**
 * @author Linda Evaldsson
 * @date 2015-05-24
 *
 * A class for handling cooldowns in the game
 *
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 * 30/05 modified by Linda Evaldsson. Added reset-method.
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

    public double getCooldownInSeconds() {
        return maxCooldown/60.0;
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

    public void reset() {
        currentCooldown = 0;
    }
    public void update() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

}
