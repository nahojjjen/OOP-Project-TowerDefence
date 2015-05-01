package edu.chl.proximity.Models.Player.Factions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Player.Spells.PersistentObject;
import edu.chl.proximity.Models.Utils.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan
 *
 * 01/05 modified by Hanna Römer. Added string name and ShowImage
 */
public abstract class Faction {
    //also has spells
    private List<PersistentObject> spells = new ArrayList();
    private String name;
    private Image showImage;

    /**
     * create a new Faction
     */
    public Faction(String name, Image showImage){
        this.name=name;
        this.showImage=showImage;
    }

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

    public String getName(){
        return this.name;
    }
    public Image getShowImage(){
        return this.showImage;
    }

}
