package edu.chl.proximity.Models.Spells.ConcreteSpells;

import com.badlogic.gdx.math.Vector2;
import com.sun.net.httpserver.Filter;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Spells.PersistentObject;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan on 2015-04-24.
 * A spell that creates an area of lightning, the lightning will "bounce" on nearby creeps
 */
public class ChainLightning extends PersistentObject {
    private List<Creep> alreadyHitCreeps = new ArrayList<Creep>();
    private double range = 100;

    public ChainLightning(Vector2 position) {
        super(position, 1); //600 frames = 10 seconds @ 60 fps
        GameData.getInstance().getMap().getParticleManager().getLightningOriginSpellEffect().createEffect(position); //create original lightning effect

    }

    public ChainLightning(Vector2 position, List<Creep> alreadyHitCreeps) {
        super(position, 1); //600 frames = 10 seconds @ 60 fps
        range = 100;
        this.alreadyHitCreeps = alreadyHitCreeps;

        GameData.getInstance().getMap().getParticleManager().getLightningOriginSpellEffect().createEffect(position); //create original lightning effect

    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getCenter()) < range * range) {
                creep.devolve();
                if (!alreadyHitCreeps.contains(creep)) {
                    //GameData.getInstance().getMap().getAddStack().add(new ChainLightning(creep.getCenter(), alreadyHitCreeps));
                    ChainLightning newSpark = new ChainLightning(creep.getCenter(), alreadyHitCreeps);
                    GameData.getInstance().getMap().getParticleManager().getLightningCreepEffect().createEffect(creep.getCenter()); //create minor lightning effect
                }


            }
        }

    }
}
