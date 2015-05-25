package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson
 * @date 2015-04-08
 *
 * ---
 * Uknown date modified by Johan Swanberg
 * A class for the concrete creep Triangle
 *
 * 23/04 Modified by Simon. Adding resources XP when killing creeps
 * 27-4 Modified by Johan Swanberg - Crashfix for when a creep gets hit by several projectiles the same frame
 * 12/05 modified by Linda Evaldsson. Removed map from constructor. Added ParticleManager and Path.
 */
public class Triangle extends Creep {

    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Line3/triangle.png");
    private static int speed = 1;

    public Triangle(ParticleManager particleManager, Path path) {
        super(null, img, speed, particleManager, path);
    }


    @Override
    public void devolve() {
        if(!isRemoved()){
            add(new Circle(this));
            destroy();
        }
    }

    @Override
    public int getCreepLineIndex() {
        return 0;
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(20, 10, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 10;
    }
}
