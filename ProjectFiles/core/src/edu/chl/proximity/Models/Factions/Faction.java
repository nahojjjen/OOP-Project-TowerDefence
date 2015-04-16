package edu.chl.proximity.Models.Factions;

import edu.chl.proximity.Models.Bases.Base;
import edu.chl.proximity.Models.Bases.BaseManager;

/**
 * @author Johan
 */
public abstract class Faction {
    private BaseManager baseManager = new BaseManager();
    private int baseID;
    //also has spells

    /**
     * create a new Faction
     * @param baseID What base the faction should have
     */
    public Faction(int baseID){
        this.baseID = baseID;
    }

    /**
     * get the base used by this faction
     * @return (Base) The base used by this faction
     */
    public Base getNewBase(){
        return baseManager.get(baseID);
    }
}
