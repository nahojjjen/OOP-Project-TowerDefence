package edu.chl.proximity.Models.Factions;

import edu.chl.proximity.Models.Bases.Base;
import edu.chl.proximity.Models.Spells.PersistentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan
 */
public abstract class Faction {
    //also has spells
    private List<PersistentObject> spells = new ArrayList();

    /**
     * create a new Faction
     */
    public Faction(){   }

    /**
     * get the base used by this faction
     * @return (Base) The base used by this faction
     */
    public abstract Base getNewBase();

    public abstract void configureSpells();

    public void addSpell(PersistentObject input){
        spells.add(input);
    }
    public PersistentObject getSpell(int i){
        return spells.get(i);

    }

}
