package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;

/**
 * Creates a circle where all creeps inside and who collide in it has to stop. Seen as 100% slow.
 * @author Johan on 2015-04-25.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 */
public class WallOfStone extends Spell {

    //Spell stats
    private static double range = 60f;
    private static int duration = 120;

    public WallOfStone() {
        super(null, duration); //600 frames = 10 seconds @ 60 fps
    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                creep.slowDown(100, 1);
                GameData.getInstance().getMap().getParticleManager().getDirtSmokeEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public void playParticleEffect() {
        GameData.getInstance().getMap().getParticleManager().getWallOfStone().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}
