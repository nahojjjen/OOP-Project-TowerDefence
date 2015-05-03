package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
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

    public BloodCarnage() {
        super(null, duration);
    }

    @Override
    public void performEffect(int counter) {
        if (!hasDamagedPlayer){
            GameData.getInstance().getMap().getBase().damage(healthCost);
            hasDamagedPlayer = true;
        }
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                creep.devolve();
                GameData.getInstance().getMap().getParticleManager().getBloodCarnageCreepEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public void playParticleEffect() {
        GameData.getInstance().getMap().getParticleManager().getBloodCarnageEffect().createEffect(getPosition());
    }

    @Override
    public double getRange() {
        return range;
    }
}