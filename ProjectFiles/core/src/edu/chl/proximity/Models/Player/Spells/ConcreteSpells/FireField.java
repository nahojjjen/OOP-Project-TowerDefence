package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;


    /**
     * A spell which creates a circle of flames, where creeps slowly devolve, but inside this field the
     * creeps run slightly faster.
     * @author by Johan on 2015-04-24.
     */
    public class FireField extends Spell {

        public FireField(){
            super(null, 120); //600 frames = 10 seconds @ 60 fps

        }

        @Override
        public void performEffect(int counter) {
            List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
            for (Creep creep:creeps){
                if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < 60 * 60) {
                    GameData.getInstance().getMap().getParticleManager().getFireCreepEffect().createEffect(creep.getCenter());
                    //creep.slowDown(-20, 1); //speeds up creeps
                    if(counter % 20 ==0)

                        creep.devolve();
                }

            }
        }

        @Override
        public void playParticleEffect() {
            GameData.getInstance().getMap().getParticleManager().getFireFieldEffect().createEffect(getPosition());
        }
    }

