package edu.chl.proximity.Models.Paths.ConcretePaths;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Paths.Path;

import java.awt.*;

/**
 * Created by Linda on 2015-04-09.
 */
public class TestPath extends Path {

    @Override
    public void initiatePathHitbox() {


    }

    @Override
    public void initiatePoints() {
        if(waypoint != null) {
            if (waypoint.isEmpty()) {
                waypoint.add(new Vector2(140, 99));
                waypoint.add(new Vector2(267, 56));
                waypoint.add(new Vector2(414, 43));
                waypoint.add(new Vector2(320, 200));
                waypoint.add(new Vector2(68, 211));
                waypoint.add(new Vector2(72, 108));
                waypoint.add(new Vector2(184, 302));
                waypoint.add(new Vector2(87, 380));
                waypoint.add(new Vector2(92, 460));
                waypoint.add(new Vector2(386, 431));
                waypoint.add(new Vector2(500, 336));
                waypoint.add(new Vector2(564, 420));
                waypoint.add(new Vector2(594, 239));
                waypoint.add(new Vector2(594, 78));
            }
        }
    }
}
