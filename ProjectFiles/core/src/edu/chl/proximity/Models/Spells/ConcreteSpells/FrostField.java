package edu.chl.proximity.Models.Spells.ConcreteSpells;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Particles.ParticleManager;
import edu.chl.proximity.Models.Particles.ProximityEffect;
import edu.chl.proximity.Models.Spells.PersistentObject;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * @author by Johan on 2015-04-24.
 */
public class FrostField extends PersistentObject{

    public FrostField(Vector2 position){
        super(position, 600); //600 frames = 10 seconds @ 60 fps
        GameData.getInstance().getMap().getParticleManager().getFrostField().createEffect(position);

    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep:creeps){
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getCenter()) < 60 * 60) {
                creep.slowDown(60,1);
                if(counter % 10 ==0)
                GameData.getInstance().getMap().getParticleManager().getFrostBlastEffect().createEffect(creep.getCenter());
            }

        }
    }
}
