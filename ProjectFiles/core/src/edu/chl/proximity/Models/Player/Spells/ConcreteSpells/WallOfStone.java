package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * Creates a circle where all creeps inside and who collide in it has to stop. Seen as 100% slow.
 * @author Johan on 2015-04-25.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 * 10-05-2015 Modified by Johan swanberg. Makes spell work again, was broken by change in Map interface
 */
public class WallOfStone extends Spell {

    //Spell stats
    private static double range = 60f;
    private static int duration = 120;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/wallofstone.png");

    public WallOfStone(Map map) {
        super(map, image, duration); //600 frames = 10 seconds @ 60 fps
    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creepsWithinRange = getMap().getCreepsWithinDistance(getPosition(),range);

        for (Creep creep : creepsWithinRange) {
            creep.slowDown(100, 1);
            getMap().getParticleManager().getDirtSmokeEffect().createEffect(creep.getCenter());
        }
    }
        /*
        BoardObject o = getMap().getCreepsWithinDistance(getPosition(), range);
        if(o instanceof Creep) {
            Creep c = (Creep)o;
            c.slowDown(100, 1);
            getMap().getParticleManager().getDirtSmokeEffect().createEffect(c.getCenter());
        }
        */


    @Override
    public void playParticleEffect() {
        getMap().getParticleManager().getWallOfStone().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}
