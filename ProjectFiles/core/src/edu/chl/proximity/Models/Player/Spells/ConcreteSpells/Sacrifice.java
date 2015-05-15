package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Hanna R�mer
 * @date 2015-05-10
 * 15/5 modified by johan, spells now have a cooldown pattern
 */
public class Sacrifice extends Spell {
    private static double range=30f;
    private static int duration=10;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/bloodpool.png");
    private static final int maxCooldown = 60*10;
    private static int currentCooldown = 0;

    public Sacrifice(Map map){
        super(map, image,duration);
    }

    public void performEffect(int counter){
        List<Tower> towers= getMap().getTowersWithinDistance(this.getPosition(),range);
        for(Tower t: towers){
            t.remove();
            getMap().setChoosenTower(null);
            if(getMap().getBase().getLife()>80){
                getMap().getBase().setLife(100);
            }else {
                getMap().getBase().heal(20);
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
    public void playParticleEffect(){
        getMap().getParticleManager().getBloodCarnageCreepEffect().createEffect(getPosition());
        getMap().getParticleManager().getSacrificeEffect().createEffect(getPosition());
    }
    public double getRange(){
        return range;
    }

}
