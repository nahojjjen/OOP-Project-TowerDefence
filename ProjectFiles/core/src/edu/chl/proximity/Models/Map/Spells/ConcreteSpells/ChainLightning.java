package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.List;

/**
 * @author Johan on 2015-04-24.
 * A spell that creates an area of lightning, the lightning will "bounce" on the closest creep
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 10-05-2015 modified by Johan Swanberg. Lightning effect not works again - was broken by structural change in program related to hand object
 * 15/5 modified by johan, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map.
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class ChainLightning extends Spell {

    //Spell stats
    private static double range = 120f;
    private static int duration = 2;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/chainlightning.png");
    private static final int maxCooldown = 60*40;
    private int currentCooldown = 0;
    private int charges = 10;

    public ChainLightning(ParticleManager particleManager) {
        super(image, duration, new Cooldown(maxCooldown), particleManager); //600 frames = 10 seconds @ 60 fps

    }


    @Override
    public void performEffect(int counter) {
        List<Creep> creepsWithinRange = getCreepsWithinDistance(getPosition(), range);
        List<Creep> creeps = getCreeps();

        for (int i = 0; i<5; i++){
            //first hit, kills creepw within distance
            for (Creep creep : creepsWithinRange) {
                creep.devolve();//devolve all creeps in range
            }

            for (Creep creep : creeps){
                if (charges >= 0){
                    creepsWithinRange = getCreepsWithinDistance(creep.getCenter(), range);
                    getParticleManager().getLightningCreepEffect().createEffect(creep.getCenter());
                    creep.devolve();//devolve all creeps in range
                    charges--;
                }
            }
        }

    }


    @Override
    public void playParticleEffect() {
        getParticleManager().getLightningOriginSpellEffect().createEffect(getPosition()); //create original lightning effect
    }
    @Override
    public double getRange() {
        return range;
    }

}
