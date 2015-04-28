package edu.chl.proximity.Models.Spells.ConcreteSpells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Spells.PersistentObject;
import edu.chl.proximity.Models.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * Creates a circle where all creeps inside and who collide in it has to stop. Seen as 100% slow.
 * @author Johan on 2015-04-25.
 */
public class WallOfStone extends Spell {

    public WallOfStone() {
        super(null, 120); //600 frames = 10 seconds @ 60 fps
    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < 60 * 60) {
                creep.slowDown(100, 1);
                GameData.getInstance().getMap().getParticleManager().getDirtSmokeEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public void playParticleEffect() {
        GameData.getInstance().getMap().getParticleManager().getWallOfStone().createEffect(getPosition());
    }
}
