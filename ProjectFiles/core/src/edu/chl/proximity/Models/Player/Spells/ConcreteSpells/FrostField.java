package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A circle where all creeps inside are slowed.
 * @author by Johan on 2015-04-24.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 */
public class FrostField extends Spell {

    //Spell stats
    private static double range = 60f;
    private static int duration = 600;

    public FrostField(){
        super(null, duration); //600 frames = 10 seconds @ 60 fps

    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep:creeps){
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                creep.slowDown(60,1);
                if(counter % 10 ==0)
                GameData.getInstance().getMap().getParticleManager().getFrostBlastEffect().createEffect(creep.getCenter());
            }

        }
    }


    @Override
    public void playParticleEffect() {
        GameData.getInstance().getMap().getParticleManager().getFrostField().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}
