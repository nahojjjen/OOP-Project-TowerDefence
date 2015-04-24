package edu.chl.proximity.Models.Spells.ConcreteSpells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Spells.PersistentObject;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;


    /**
     * @author by Johan on 2015-04-24.
     */
    public class FireField extends PersistentObject {

        public FireField(Vector2 position){
            super(position, 120); //600 frames = 10 seconds @ 60 fps
            GameData.getInstance().getMap().getParticleManager().getFrostField().createEffect(position);

        }

        @Override
        public void performEffect(int counter) {
            List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
            for (Creep creep:creeps){
                if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getCenter()) < 60 * 60) {
                    creep.slowDown(-160, 1); //speeds up creeps

                    if(counter % 20 ==0)
                        creep.devolve();
                        GameData.getInstance().getMap().getParticleManager().getFrostBlastEffect().createEffect(creep.getCenter());
                }

            }
        }
    }

