package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A circle where all creeps inside are slowed.
 * @author by Johan on 2015-04-24.
 */
public class FrostField extends Spell {

    public FrostField(){
        super(null, 600); //600 frames = 10 seconds @ 60 fps

    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep:creeps){
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < 60 * 60) {
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
}
