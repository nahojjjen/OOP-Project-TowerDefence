package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;


/**
 * A spell which creates a circle of flames, where creeps slowly devolve, but inside this field the
 * creeps run slightly faster.
 * @author by Johan on 2015-04-24.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 10-05-2015 Modified by Johan swanberg. Spell now uses new more efficient getCreepsWithinRange method
 * 15/5 modified by johan, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map.
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class FireField extends Spell {

    //Spell stats
    private static double range = 100f;
    private static int duration = 120;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/firefield.png");
    private static final int maxCooldown = 60*30;
    private int currentCooldown = 0;

    public FireField(ParticleManager particleManager){
        super(image, duration, new Cooldown(maxCooldown), particleManager); //600 frames = 10 seconds @ 60 fps

    }

    @Override
    public void performEffect(int counter) {

        List<Creep> creeps = getCreepsWithinDistance(getPosition(), range);
        for (Creep creep:creeps){
                getParticleManager().getFireCreepEffect().createEffect(creep.getCenter());
                if(counter % 10 ==0)
                    creep.devolve();
        }
    }

    @Override
    public void playParticleEffect() {
        getParticleManager().getFireFieldEffect().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}

