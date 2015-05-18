package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * A spell which creates a field where all normal creeps completely devolve, at the cost of base hp.
 * @author Johan on 2015-04-28.
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 15/5 modified by johan, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map.
 */
public class BloodCarnage extends Spell {

    //Spell stats
    private static double range = 80f;
    private static int healthCost = 35;
    private static int duration = 60 * 5; //600 frames = 10 seconds @ 60 fps
    private static Image image = new Image(Constants.FILE_PATH + "Spells/bloodcarnage.png");
    private static final int maxCooldown = 30;
    private static int currentCooldown = 0;

    private boolean hasDamagedPlayer = false;

    public BloodCarnage(ParticleManager particleManager) {
        super(image, duration, particleManager);
    }

    @Override
    public void performEffect(int counter) {

        if (!hasDamagedPlayer){
            setHealthChange(-healthCost);
            hasDamagedPlayer = true;
        }
        List<Creep> creeps = getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                creep.devolve();
               getParticleManager().getBloodCarnageCreepEffect().createEffect(creep.getCenter());
            }
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
        getParticleManager().getBloodCarnageEffect().createEffect(getPosition());
    }

    @Override
    public double getRange() {
        return range;
    }
}