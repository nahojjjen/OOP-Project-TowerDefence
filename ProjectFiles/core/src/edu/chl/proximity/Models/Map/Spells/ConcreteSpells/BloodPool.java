package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Cooldown;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

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
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class BloodPool extends Spell {

    //Spell stats
    private static double range = 120f;
    private static int duration = 1;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/bloodpool.png");
    private static int maxCooldown = 30;

    public BloodPool(ParticleManager particleManager) {
        super(image, "Blood Pool", duration, new Cooldown(maxCooldown), particleManager);
    }

    @Override
    public void performEffect(int counter) {

        int hitCreeps = 0;
        for (Creep creep : getCreeps()) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                hitCreeps++;
                creep.devolve();
                setHealthChange(1);
                if (getParticleManager() != null) {
                    getParticleManager().getBloodPoolCreepEffect().createEffect(creep.getCenter());
                }
            }

            fixCustomCooldownLogic(hitCreeps);
        }

    }
    @Override
    public String getDescription() {
        return "Devoures the life essence of all creeps in the area, granting one life for each creep hit. The cooldown increases by 0.5 seconds for each creep hit.";
    }


    private void fixCustomCooldownLogic(int hitCreeps){
        getCooldown().setMaxCooldown(maxCooldown*(hitCreeps+1));
    }


    @Override
    public void playParticleEffect() {
        if (getParticleManager() != null) {
            getParticleManager().getBloodPoolEffect().createEffect(getPosition());
        }
    }

    @Override
    public double getRange() {
        return range;
    }
}
