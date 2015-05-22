package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;

import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-05-22
 */
public class PickACard extends Spell{
    private static double range=120f;
    private static int duration=240;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/TarotCards.png");
    private static final int maxCooldown = 60*5;
    private static int currentCooldown = 0;
    private int effect;

    public PickACard(ParticleManager particleManager){
        super(image, duration, particleManager);
    }

    public void performEffect(int counter){
        if(counter==240) {
            Double dEffect = ProximityRandom.getRandomDoubleBetween(0,4);
            effect=dEffect.intValue();
        }else{
            switch(effect){
                case 0:
                    performSlowEffect();
                    break;
                case 1:
                    performKillEffect(counter);
                    break;
                case 2:
                    performHealthEffect(counter);
                    break;
                case 3:
                    performSabotageEffect(counter);
                    break;
                case 4:
                    performMiracle(counter);
                    break;
            }
        }
    }
    private void performSlowEffect(){
        List<Creep> creeps = this.getCreepsWithinDistance(this.getPosition(),range);
        for(Creep c:creeps){
            c.slowDown(70, 60 * 5);
        }
    }
    private void performKillEffect(int counter){
        List<Creep> creeps = this.getCreepsWithinDistance(this.getPosition(),range);
        if(counter%10==0) {
            for (Creep c : creeps) {
                c.devolve();
            }
        }
    }
    private void performHealthEffect(int counter){
        if(counter==230) {
            this.setHealthChange(10);
            this.setResourcesChange(50,50,0);
        }
    }
    private void performSabotageEffect(int counter){
        List<Tower> towers=this.getTowersWithinDistance(this.getPosition(),range);
        for(Tower t:towers){
            t.remove();
        }
        if(counter==230){
            this.setResourcesChange(-50,-50,0);
            this.setHealthChange(-20);
        }

    }
    private void performMiracle(int counter){
        if(counter==230){
            this.setHealthChange(100);
            setResourcesChange(0,0,50);
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
        switch(effect){
            case 0:
                getParticleManager().getWallOfStone().createEffect(getPosition());
                break;
            case 1:
                getParticleManager().getFrostField().createEffect(getPosition());
                break;
        }
    }
    public double getRange(){
        return range;
    }



}
