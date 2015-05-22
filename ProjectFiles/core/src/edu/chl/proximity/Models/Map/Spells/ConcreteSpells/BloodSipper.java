package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-05-22
 */
public class BloodSipper extends Spell {
    private static double range=80f;
    private static int duration=120;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/bloodpool.png");
    private static final int maxCooldown = 60;
    private static int currentCooldown = 0;

    public BloodSipper(ParticleManager particleManager){
        super(image, duration, particleManager);
    }

    public void performEffect(int counter){
        List<Creep> creeps=getCreepsWithinDistance(this.getPosition(), range);
        for(Creep c: creeps){
            if(counter%10==0){
                c.devolve();
            }
            c.slowDown(10,Integer.MAX_VALUE);
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
    public void playParticleEffect(){
        getParticleManager().getWallOfStone().createEffect(getPosition());
    }
    public double getRange(){
        return range;
    }

}
