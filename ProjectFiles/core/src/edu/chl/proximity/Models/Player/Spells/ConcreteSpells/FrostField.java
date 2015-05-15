package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A circle where all creeps inside are slowed.
 * @author by Johan on 2015-04-24.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 10-05-2015 Modified by Johan swanberg. Spell now uses new more efficient getCreepsWithinDistance method
 * 15/5 modified by johan, spells now have a cooldown pattern
 */
public class FrostField extends Spell {

    //Spell stats
    private static double range = 60f;
    private static int duration = 600;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/frostfield.png");
    private static final int maxCooldown = 60*10;
    private static int currentCooldown = 0;

    public FrostField(Map map){
        super(map, image, duration); //600 frames = 10 seconds @ 60 fps

    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = getMap().getCreepsWithinDistance(getPosition(), range);
        for (Creep creep:creeps){
            creep.slowDown(60,1);
            if(counter % 10 ==0){ //only create the visual effect every 10th frame for visual purpose
                getMap().getParticleManager().getFrostBlastEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public void updateCooldown() {
        if (currentCooldown>0)currentCooldown--;
    }

    @Override
    public int getCooldownPercent() {
        System.out.println((currentCooldown*100) / maxCooldown);
        return 100-((currentCooldown*100) / maxCooldown);
    }

    @Override
    public void startCooldown() {
        currentCooldown = maxCooldown;
    }

    @Override
    public boolean isReadyToCast() {
        return (currentCooldown<= 0);
    }

      @Override
    public void resetCooldown() {
        currentCooldown =0;
    }

    @Override
    public void playParticleEffect() {
        getMap().getParticleManager().getFrostField().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}
