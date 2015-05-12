package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-05-10
 */
public class Sacrifice extends Spell {
    private static double range=30f;
    private static int duration=10;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/bloodpool.png");

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

    public void playParticleEffect(){
        getMap().getParticleManager().getSacrificeEffect().createEffect(getPosition());
    }
    public double getRange(){
        return range;
    }

}
