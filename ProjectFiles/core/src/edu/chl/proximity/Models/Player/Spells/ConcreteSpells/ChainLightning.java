package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Johan on 2015-04-24.
 * A spell that creates an area of lightning, the lightning will "bounce" on the closest creep
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 10-05-2015 modified by Johan Swanberg. Lightning effect not works again - was broken by structural change in program related to hand object
 * 15/5 modified by johan, spells now have a cooldown pattern
 */
public class ChainLightning extends Spell {

    //Spell stats
    private static double range = 100f;
    private static int duration = 2;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/chainlightning.png");
    private static final int maxCooldown = 60*10;
    private static int currentCooldown = 0;

    public ChainLightning(Map map) {
        super(map, image, duration); //600 frames = 10 seconds @ 60 fps

    }


    @Override
    public void performEffect(int counter) {
        List<Creep> creepsWithinRange = getMap().getCreepsWithinDistance(getPosition(), range);
        for (Creep creep : creepsWithinRange) {
            creep.devolve();//devolve all creeps in range
            this.preparePlacing(creep.getCenter());
        }
    }

    @Override
    public void updateCooldown() {
        if (currentCooldown>0)currentCooldown--;
    }

    @Override
    public int getCooldownPercent() {
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
        getMap().getParticleManager().getLightningOriginSpellEffect().createEffect(getPosition()); //create original lightning effect
    }
    @Override
    public double getRange() {
        return range;
    }

}
