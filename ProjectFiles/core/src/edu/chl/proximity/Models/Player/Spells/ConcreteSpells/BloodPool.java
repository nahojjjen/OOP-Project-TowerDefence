package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A spell which creates a circle where all creeps inside gets devolved 1 step, and gives the base 1 more health for each devolved creep
 * For every creep hit, the cooldown will take half a second.(plus a base of half seconds)
 *
 * Example: The spell gets used and hits 5 creeps: the player base regenerates 5 life, the 5 creeps devolve and the spell
 * is put on a 3 second cooldown. (2.5 seconds for 5 creeps + 0.5 seconds base.)
 * @author Johan on 2015-04-28.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 15/5 modified by johan, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map.
 */
public class BloodPool extends Spell {

    //Spell stats
    private static double range = 120f;
    private static int duration = 1;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/bloodpool.png");
    private static  int maxCooldown = 30;
    private static int currentCooldown = 0;
    private static final int baseCooldown = 30;

    public BloodPool(ParticleManager particleManager) {
        super(image, duration, particleManager);
    }

    @Override
    public void performEffect(int counter) {

        int hitCreeps = 0;
        for (Creep creep : getCreeps()) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                hitCreeps++;
                creep.devolve();
                setHealthChange(1);
                getParticleManager().getBloodPoolCreepEffect().createEffect(creep.getCenter());
            }

            fixCustomCooldownLogic(hitCreeps);
        }
    }

    private void fixCustomCooldownLogic(int hitCreeps){
        currentCooldown += 30*hitCreeps; //plus base cooldown because that method gets called
        maxCooldown = currentCooldown;
    }
    @Override
    public void updateCooldown() {
        if (currentCooldown>0)currentCooldown--;
    }

    @Override
    public int getCooldownPercent() {
        return 100-((currentCooldown*100) / maxCooldown);
    }

    @Override
    public void startCooldown() {
        currentCooldown = baseCooldown;
    }

    @Override
    public boolean isReadyToCast() {
        return (currentCooldown<= 0);
    }

    @Override
    public void resetCooldown() {
        currentCooldown =0;
    }
    @Override
    public void playParticleEffect() {
        getParticleManager().getBloodPoolEffect().createEffect(getPosition());
    }

    @Override
    public double getRange() {
        return range;
    }
}
