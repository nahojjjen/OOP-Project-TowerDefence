package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;

import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 *
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class CoinFlip extends Spell{
    private static double range = 60f;
    private static int duration = 120;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/Coins.png");
    private static final int maxCooldown = 60 * 6;
    private int effect;

    public CoinFlip(ParticleManager particleManager) {
        super(image, "Coin Flip", duration, new Cooldown(maxCooldown), particleManager);

    }

    public void performEffect(int counter) {
        List<Creep> creeps = getCreepsWithinDistance(this.getPosition(),range);
        for(Creep c: creeps) {
            switch (effect) {
                case 0:
                    c.devolve();
                    this.setResourcesChange(10,10,0);
                    break;
                case 1:
                    if(counter%10==0) {
                        this.setResourcesChange(-1, -1, 0);
                    }
                    break;

                default: break;
            }
        }
    }
    @Override
    public String getDescription() {
        return "Creates an area which will either damage creeps and grant extra resources, or simply drain your resources for each creep that enters the field.";
    }



    public void playParticleEffect() {
        if (getParticleManager() != null)
            getParticleManager().getFireFieldEffect().createEffect(getPosition());
    }

    public double getRange() {
        return range;
    }

    @Override
    public void start(){
        super.start();
        Double dEffect= ProximityRandom.getRandomDoubleBetween(0, 2);
        effect=dEffect.intValue();
    }

}
