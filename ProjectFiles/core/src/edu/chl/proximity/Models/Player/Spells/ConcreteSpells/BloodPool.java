package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A spell which creates a circle where all creeps inside gets devolved 1 step, and gives the base 1 more health for each devolved creep
 * @author Johan on 2015-04-28.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 */
public class BloodPool extends Spell {

    //Spell stats
    private static double range = 120f;
    private static int duration = 1;

    public BloodPool() {
        super(null, duration);
    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                creep.devolve();
                GameData.getInstance().getMap().getBase().heal(1);
                GameData.getInstance().getMap().getParticleManager().getBloodPoolCreepEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public void playParticleEffect() {
        GameData.getInstance().getMap().getParticleManager().getBloodPoolEffect().createEffect(getPosition());
    }

    @Override
    public double getRange() {
        return range;
    }
}
