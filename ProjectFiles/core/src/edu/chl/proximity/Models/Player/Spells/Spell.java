package edu.chl.proximity.Models.Player.Spells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
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

    public Spell(Map map, Image icon, int counter) {
        super(map, null, null, counter);
        controlPanelImage = icon;
    }



    @Override
    public void placeObject(Vector2 position) {
        resetPersistentObject();
        this.setPosition(position);
        getMap().getAddStack().add(this);// this avoids concurrent modificaiton exception

        this.start();
        playParticleEffect(); //important that this is after setPosition
        getMap().getHand().setItem(null);
    }
    public abstract void playParticleEffect();
    public abstract double getRange();

    public Image getControlPanelImage() {
        return controlPanelImage;
    }

}
