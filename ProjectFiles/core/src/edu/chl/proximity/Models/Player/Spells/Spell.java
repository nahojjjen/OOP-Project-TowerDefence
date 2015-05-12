package edu.chl.proximity.Models.Player.Spells;

import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
import edu.chl.proximity.Models.Utils.Image;

/**
 * An abstract class for all spells, all spells are holdable objects (can be created by cursor, "hand")
 * and are persistent objects, their effect lasts during a couple of frames.
 * @author Johan on 2015-04-28.
 *
 * 03-05-2015 Modified by Simon Gislen. Spell has a area range, could be infinite.
 * 07/05 modified by Linda Evaldsson. Spell has a control panel Image.
 */
public abstract class Spell extends PersistentObject implements Holdable {

    private Image controlPanelImage;

    private Map map;

    public Spell(Map map, Image icon, int counter) {
        super(null, null, counter);
        controlPanelImage = icon;
        this.map = map;
    }

    public Map getMap() {
        return map;
    }



    @Override
    public void preparePlacing(ProximityVector position) {
        resetPersistentObject();
        this.setPosition(position);
        this.start();
        playParticleEffect(); //important that this is after setPosition

    }
    public abstract void playParticleEffect();
    public abstract double getRange();

    public Image getControlPanelImage() {
        return controlPanelImage;
    }

}
