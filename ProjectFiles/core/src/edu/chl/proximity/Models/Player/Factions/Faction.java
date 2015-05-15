package edu.chl.proximity.Models.Player.Factions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan
 *
 * 01/05 modified by Hanna R�mer. Added string name and ShowImage
 */
public abstract class Faction {
    //also has spells
    private List<Spell> spells = new ArrayList();
    private String name;
    private Image profileImage;

    /**
     * create a new Faction
     */
    public Faction(String name, Image profileImage){
        this.name=name;
        this.profileImage=profileImage;
    }

    /**
     * get the base used by this faction
     * @return (Base) The base used by this faction
     */
    public abstract Base getNewBase(Path path, ParticleManager particleManager);

    public abstract void configureSpells(Map map);

    public void addSpell(Spell input){
        spells.add(input);
    }
    public Spell getSpell(int i){
        try {
            return (Spell)spells.get(i).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return  spells.get(i);
    }

    public String getName(){
        return this.name;
    }
    public Image getShowImage(){
        return this.profileImage;
    }

}
