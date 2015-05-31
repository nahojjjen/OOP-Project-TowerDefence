package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Cooldown;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityRandom;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 *
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class PickACard extends Spell {
    private static double range = 100f;
    private static int duration = 240;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/tarotcards.png");
    private static final int maxCooldown = 60 * 5;
    private int effect;

    public PickACard(ParticleManager particleManager) {
        super(image, "Pick a Card", duration, new Cooldown(maxCooldown), particleManager);

    }

    public void performEffect(int counter) {
        switch (effect) {
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
            default: break;
        }
    }
    @Override
    public String getDescription() {
        return "Creates a field for 4 seconds which can have one of the following effects: Slow, Damage, change health, destroy towers or perform miracle.";
    }


    private void performSlowEffect() {
        List<Creep> creeps = this.getCreepsWithinDistance(this.getPosition(), range);
        for (Creep c : creeps) {
            c.slowDown(50, 60 * 5);
        }
    }

    private void performKillEffect(int counter) {
        List<Creep> creeps = this.getCreepsWithinDistance(this.getPosition(), range);
        if (counter % 10 == 0) {
            for (Creep c : creeps) {
                c.devolve();
            }
        }
    }

    private void performHealthEffect(int counter) {
        if (counter == 230) {
            this.setHealthChange(10);
            this.setResourcesChange(50, 50, 0);
        }
    }

    private void performSabotageEffect(int counter) {
        List<Tower> towers = this.getTowersWithinDistance(this.getPosition(), range);
        for (Tower t : towers) {
            t.remove();
        }
        if (counter == 230) {
            this.setResourcesChange(-50, -50, 0);
            this.setHealthChange(-20);
        }

    }

    private void performMiracle(int counter) {
        if (counter == 230) {
            this.setHealthChange(100);
            setResourcesChange(0, 0, 50);
        }
    }


    public void playParticleEffect() {
        if (getParticleManager() != null) {
            switch (effect) {
                case 0:
                    getParticleManager().getFrostField().createEffect(getPosition());
                    break;
                case 1:
                    getParticleManager().getLightningOriginSpellEffect().createEffect(getPosition());
                    break;
                case 2:
                    getParticleManager().getLuckQGood().createEffect(getPosition());
                    break;
                case 3:
                    getParticleManager().getLuckWBad().createEffect(getPosition());
                    break;
                default:
                    break;
            }
        }
    }




    public double getRange() {
        return range;
    }

    @Override
    public void start(){
        super.start();
        Double dEffect = ProximityRandom.getRandomDoubleBetween(0, 4);
        effect = dEffect.intValue();
    }

}
