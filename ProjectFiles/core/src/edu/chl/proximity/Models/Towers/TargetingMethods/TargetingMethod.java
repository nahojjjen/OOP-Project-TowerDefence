package edu.chl.proximity.Models.Towers.TargetingMethods;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Creeps.Creep;

/**
 * Created by Johan on 2015-04-11.Group work with Linda
 */
public interface TargetingMethod {

    public Creep getTarget(Vector2 position, double range);
}
