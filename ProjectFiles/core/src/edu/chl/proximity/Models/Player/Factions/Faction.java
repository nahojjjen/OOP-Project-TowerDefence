package edu.chl.proximity.Models.Player.Factions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan
 *
 * 01/05 modified by Hanna Romer. Added string name and ShowImage
 * 15/5 modified by johan, spells now have a cooldown pattern, cooldowns are reset on game start
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

    public void resetSpellCooldowns(){
        for (Spell spell:spells){
            spell.resetCooldown();
        }
    }
    public abstract void configureSpells(ParticleManager particleManager);

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
