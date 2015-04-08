package edu.chl.proximity.Models.Spells;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Towers.Tower;

import java.util.List;

/**
 * Created by Johan on 2015-04-02.
 * A class for effects that affect all towers/projectiles/creeps on the map.
 */
public abstract class GlobalSpell extends Spell {

    /**
     * This class is almost identical to what AOE spell does if you input an area that covers the entire screen, but
     * it's more efficient since it does not perform the check if the creep is inside the area.
     * @param i what level the spell is
     */
    public  GlobalSpell(int i){
        super(i);
    }


    /**
     * call this method if you want the concrete aoeSpell to devolve all creeps within a polygon
     * @param i how many steps the creeps should devolve. (1 to devolve each creep 1 step)
     */
    public void devolveAllCreeps(int i){
        List<Creep> creepList = Map.getInstance().getCreeps();
        for(int times = 0; times<i; times++){
            for(Creep creep: creepList){

                    creep.devolve();

            }
        }
    }

    //TODO method that makes all projectiles currently in air explode? would b cool.

    /**
     * call this method if you want the concrete aoeSpell to slow all creeps in the polygon
     * @param amount how many % to slow the creep
     */
    public void slowAllCreeps(double amount){
        List<Creep> creepList = Map.getInstance().getCreeps();

        for(Creep creep: creepList){
                //TODO creep.slow(amount);
        }

    }


    /**
     * Change the attackspeed of the towers in the area (by chaning the reload variable)
     * @param change how much % the attackspeed should be (1 would keep the attackspeed, 2 would make it double the speed.)
     */
    public void speedUpAllTowers(double change){
        List<Tower> towers = Map.getInstance().getTowers();

        for(Tower tower : towers){
            //todo make reloadtime a variable and enable chaning it
        }
    }

}
