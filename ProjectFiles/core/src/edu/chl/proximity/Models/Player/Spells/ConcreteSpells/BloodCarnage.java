package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A spell which creates a field where all normal creeps completely devolve, at the cost of base hp.
 * @author Johan on 2015-04-28.
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 */
public class BloodCarnage extends Spell {

    //Spell stats
    private static double range = 80f;
    private static int healthCost = 35;
    private static int duration = 60 * 5; //600 frames = 10 seconds @ 60 fps

    private boolean hasDamagedPlayer = false;

    public BloodCarnage(Map map) {
        super(map, null, duration);
    }

    @Override
    public void performEffect(int counter) {
        if (!hasDamagedPlayer){
            getMap().getBase().damage(healthCost);
            hasDamagedPlayer = true;
        }
        List<Creep> creeps = getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                creep.devolve();
               getMap().getParticleManager().getBloodCarnageCreepEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public void playParticleEffect() {
        getMap().getParticleManager().getBloodCarnageEffect().createEffect(getPosition());
    }

    @Override
    public double getRange() {
        return range;
    }
}