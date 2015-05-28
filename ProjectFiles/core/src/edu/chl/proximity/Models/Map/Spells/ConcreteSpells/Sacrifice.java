package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-05-10
 * 15/5 modified by johan, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map.
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class Sacrifice extends Spell {
    private static double range=30f;
    private static int duration=10;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/sacrifice.png");
    private static final int maxCooldown = 60*10;
    private static int currentCooldown = 0;

    public Sacrifice(ParticleManager particleManager){
        super(image, "Sacrifice", duration, new Cooldown(maxCooldown), particleManager);
    }

    public void performEffect(int counter){
        List<Tower> towers= getTowersWithinDistance(this.getPosition(), range);
        for(Tower t: towers){
            t.remove();
            setHealthChange(20);
        }
    }
    @Override
    public String getDescription() {
        return "Consumes all towers in the area, granting 20 hp per destroyed tower.";
    }


    public void playParticleEffect(){
        if (getParticleManager() != null) {
            getParticleManager().getBloodCarnageCreepEffect().createEffect(getPosition());
            getParticleManager().getSacrificeEffect().createEffect(getPosition());
        }
    }
    public double getRange(){
        return range;
    }

}
