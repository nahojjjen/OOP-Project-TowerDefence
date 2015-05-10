package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Spells.Spell;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 * ControlPanelSpell is a class that can display Spell
 */
public class ControlPanelSpell extends BoardObject {

    Spell spell;

    public ControlPanelSpell(ProximityVector position, Spell spell) {
        super(null, position, spell.getControlPanelImage(), 0);
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