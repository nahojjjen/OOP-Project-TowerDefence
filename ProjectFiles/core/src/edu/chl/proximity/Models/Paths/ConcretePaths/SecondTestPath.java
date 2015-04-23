package edu.chl.proximity.Models.Paths.ConcretePaths;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Paths.Path;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-13
 */
public class SecondTestPath extends Path{


    @Override
    public void initiatePathHitbox() {


    }

    @Override
    public void initiatePoints() {
        if (waypoint != null) {
            if (waypoint.isEmpty()) {
                waypoint.add(new Vector2(3, 101));
                waypoint.add(new Vector2(549, 78));
                waypoint.add(new Vector2(550, 144));
                waypoint.add(new Vector2(474, 144));
                waypoint.add(new Vector2(473, 207));
                waypoint.add(new Vector2(561, 206));
                waypoint.add(new Vector2(557, 271));
                waypoint.add(new Vector2(354, 279));
                waypoint.add(new Vector2(358, 189));
                waypoint.add(new Vector2(97, 202));
                waypoint.add(new Vector2(117, 405));
                waypoint.add(new Vector2(490, 406));
            }

        }
    }
}
