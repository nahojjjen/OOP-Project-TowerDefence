package edu.chl.proximity.Models.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A spell which creates a field where all normal creeps completely devolve, at the cost of base hp.
 * @author Johan on 2015-04-28.
 */
public class BloodCarnage extends Spell {
    private boolean hasDamagedPlayer = false;

    public BloodCarnage() {
        super(null, 60*5); //600 frames = 10 seconds @ 60 fps
    }

    @Override
    public void performEffect(int counter) {
        if (!hasDamagedPlayer){
            GameData.getInstance().getMap().getBase().damage(35);
            hasDamagedPlayer = true;
        }
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < 80 * 80) {
                creep.devolve();
                GameData.getInstance().getMap().getParticleManager().getBloodCarnageCreepEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public void playParticleEffect() {
        GameData.getInstance().getMap().getParticleManager().getBloodCarnageEffect().createEffect(getPosition());
    }
}