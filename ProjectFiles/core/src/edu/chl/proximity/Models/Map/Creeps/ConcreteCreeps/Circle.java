package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Simon Gislén
 * @date 2015-04-14
 * 23/04 Modified by Simon. Adding resources and XP when killing creeps
 * 27-4 Modified by Johan Swanberg - Crashfix for when a creep gets hit by several projectiles the same frame
 * 12/05 modified by Linda Evaldsson. Removed Map from constructor, added ParticleManager and Path
 */
public class Circle extends Creep {

    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Line2/7.png");
    private static int speed = 3;

    public Circle(ParticleManager particleManager, Path path) {
        super(null, img, speed, particleManager, path);
    }

    /**
     * Constructor to create a Circle with properties from another creep.
     * @param oldCreep a Creep object from which position is used.
     */
    public Circle(Creep oldCreep) {
        super(img, speed, oldCreep);
    }

    @Override
    public void devolve() {
        if (!isRemoved()){
            destroy();
        }
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(10, 20, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 4;
    }

}