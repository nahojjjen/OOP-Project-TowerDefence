package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Spells.Spell;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 * ControlPanelSpell is a class that can display Spell
 */
public class ControlPanelSpell extends BoardObject {

    Spell spell;

    public ControlPanelSpell(Vector2 position, Spell spell) {
        super(null, position, spell.getImage(), 0);
        this.spell = spell;
        spell.setPosition(position);
    }

    /**
     * Returns a copy of the tower in this ControlPanelTower
     * @return a copy of tower
     */
    public Spell getSpell(){
        try {
            return (Spell)spell.clone();
        } catch (CloneNotSupportedException e){
            System.out.println("ControlPanelSpell: Error, Clone not supported");
        }
        return null;
    }
}