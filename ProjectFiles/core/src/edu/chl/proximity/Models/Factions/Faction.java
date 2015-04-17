package edu.chl.proximity.Models.Factions;

import edu.chl.proximity.Models.Bases.Base;

/**
 * @author Johan
 */
public abstract class Faction {
    //also has spells

    /**
     * create a new Faction
     */
    public Faction(){   }

    /**
     * get the base used by this faction
     * @return (Base) The base used by this faction
     */
    public abstract Base getNewBase();


}
