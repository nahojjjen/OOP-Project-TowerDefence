package edu.chl.proximity.Models.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Bases.Base;
import edu.chl.proximity.Models.Bases.ShardBase;
import edu.chl.proximity.Models.Factions.Faction;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 */
public class Planes extends Faction {

    public Planes(){
        super(); //should also specify whay spells should be added
    }

    @Override
    public Base getNewBase() {
        return new ShardBase();
    }
}
