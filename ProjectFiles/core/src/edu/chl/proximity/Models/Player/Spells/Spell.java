package edu.chl.proximity.Models.Player.Spells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
import edu.chl.proximity.Models.Utils.Image;

/**
 * An abstract class for all spells, all spells are holdable objects (can be created by cursor, "hand")
 * and are persistent objects, their effect lasts during a couple of frames.
 * @author Johan on 2015-04-28.
 */
public abstract class Spell extends PersistentObject implements Holdable {
    public Spell(Image icon, int counter) {
        super(null,icon, counter);
    }

    @Override
    public void placeObject(Vector2 position) {
        this.setPosition(position);
        GameData.getInstance().getMap().getAddStack().add(this);// this avoids concurrent modificaiton exception
        this.start();
        playParticleEffect(); //important that this is after setPosition
    }
    public abstract void playParticleEffect();
}
