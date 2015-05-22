package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;

import java.util.List;
import java.util.Random;

/**
 * @author Hanna Römer
 * @date 2015-05-22
 */
public class LifeGamble extends Spell {
    private static double range=5f;
    private static int duration=1;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/dice.png");
    private static final int maxCooldown = 60*2;
    private static int currentCooldown = 0;

    public LifeGamble(ParticleManager particleManager){
        super(image, duration, particleManager);
    }

    public void performEffect(int counter){
        Double diceNbr=ProximityRandom.getRandomDoubleBetween(0,6);
        switch (diceNbr.intValue()){
            case 0:
                this.setHealthChange(20);
                break;
            case 1:
                this.setHealthChange(10);
                break;
            case 2:
                this.setHealthChange(5);
                break;
            case 3:
                break;
            case 4:
                this.setHealthChange(-10);
                break;
            case 5:
                this.setHealthChange(-20);
                break;
            case 6:
                this.setHealthChange(-100);
                break;
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

    public void playParticleEffect(){}

    public double getRange(){
        return range;
    }
}
