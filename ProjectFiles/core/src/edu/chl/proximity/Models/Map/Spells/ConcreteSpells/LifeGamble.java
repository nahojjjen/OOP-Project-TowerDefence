package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Cooldown;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityRandom;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 *
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class LifeGamble extends Spell {
    private static double range=5f;
    private static int duration=1;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/dice.png");
    private static final int maxCooldown = 60*2;

    public LifeGamble(ParticleManager particleManager){
        super(image, "Life Gamle", duration, new Cooldown(maxCooldown), particleManager);
    }

    public void performEffect(int counter){
        Double diceNbr=ProximityRandom.getRandomDoubleBetween(0,6);
        switch (diceNbr.intValue()){
            case 0:
                this.setHealthChange(20);
                playParticleEffectGood();
                break;
            case 1:
                this.setHealthChange(10);
                playParticleEffectGood();
                break;
            case 2:
                this.setHealthChange(5);
                playParticleEffectGood();
                break;
            case 3:
                break;
            case 4:
                this.setHealthChange(-10);
                playParticleEffectBad();
                break;
            case 5:
                this.setHealthChange(-20);
                playParticleEffectBad();
                break;
            case 6:
                this.setHealthChange(-100);
                playParticleEffectBad();
                break;
            default: break;
        }
    }

    @Override
    public String getDescription() {
        return "A life gamble, your health will increase or decrease by a random amount. Your ability to manipulate luck makes favorable chances appear more often.";
    }



    public void playParticleEffect(){}
    private void playParticleEffectGood(){
        getParticleManager().getLuckQGood().createEffect(getPosition());
    }
    private void playParticleEffectBad(){

        getParticleManager().getLuckQBad().createEffect(getPosition());
    }

    public double getRange(){
        return range;
    }
}
