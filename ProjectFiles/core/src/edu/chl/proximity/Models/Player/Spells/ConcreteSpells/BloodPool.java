package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
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
 */
public class BloodPool extends Spell {

    //Spell stats
    private static double range = 120f;
    private static int duration = 1;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/bloodpool.png");
    private static  int maxCooldown = 30;
    private static int currentCooldown = 0;

    public BloodPool(Map map) {
        super(map, image, duration);
    }

    @Override
    public void performEffect(int counter) {

        List<Creep> creeps = getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                currentCooldown += 30;
                maxCooldown = currentCooldown;
                creep.devolve();
                getMap().getBase().heal(1);
                getMap().getParticleManager().getBloodPoolCreepEffect().createEffect(creep.getCenter());
            }
        }
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
        currentCooldown = maxCooldown;
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
        getMap().getParticleManager().getBloodPoolEffect().createEffect(getPosition());
    }

    @Override
    public double getRange() {
        return range;
    }
}
