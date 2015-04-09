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
                waypoint.add(new Vector2(100, 100));
                waypoint.add(new Vector2(200, 200));
                waypoint.add(new Vector2(100, 400));

            }
        }
    }
}
