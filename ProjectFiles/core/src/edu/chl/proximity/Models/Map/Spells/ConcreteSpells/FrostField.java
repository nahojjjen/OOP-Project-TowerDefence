package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Cooldown;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * A circle where all creeps inside are slowed.
 * @author by Johan on 2015-04-24.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 10-05-2015 Modified by Johan swanberg. Spell now uses new more efficient getCreepsWithinDistance method
 * 15/5 modified by johan, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map.
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class FrostField extends Spell {

    //Spell stats
    private static double range = 60f;
    private static int duration = 600;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/frostfield.png");
    private static final int maxCooldown = 60*15;

    public FrostField(ParticleManager particleManager){
        super(image, "Frost Field", duration, new Cooldown(maxCooldown), particleManager); //600 frames = 10 seconds @ 60 fps

    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = getCreepsWithinDistance(getPosition(), range);
        for (Creep creep:creeps){
            creep.slowDown(60,1);
            if(counter % 10 ==0){ //only create the visual effect every 10th frame for visual purpose
                if (getParticleManager() != null) {
                    getParticleManager().getFrostBlastEffect().createEffect(creep.getCenter());
                }
            }
        }
    }
    @Override
    public String getDescription() {
        return "Slows down all creeps in the area for 10 seconds.";
    }


    @Override
    public void playParticleEffect() {
        if (getParticleManager() != null)
            getParticleManager().getFrostField().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}
