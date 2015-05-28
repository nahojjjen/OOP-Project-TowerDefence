package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Simon Gislén
 * @date 2015-04-21
 *
 * A class which describes an evolutionary line of monsters, or so called "creeps", the creeps
 * can spawn at different "levels", a level means different speeds, images etc. When a level 5 creep dies,
 * a level 4 creep takes its place, it "devolves", same for level 3, 2 etc.
 *
 * 23/04 Modified by Simon. Adding resources and XP when killing creeps
 * 24/4 Modified by Johan Swanberg - Makes creeps correctly follow path by making getCenter work correctly- fixing images
 * 27-4 Modified by Johan Swanberg - Crashfix for when a creep gets hit by several projectiles the same frame
 * 12/05 modified by Linda Evaldsson. Removed Map from constructor, added ParticleManager and Path
 * 22/05 modified by Linda Evaldsson. Changed how the images are handled.
 */
public class Line1 extends Creep {

    private int creepLineIndex;
    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Line3/6.png"); //dummy image to get correct resolution
    private static double speed = 2;

    public Line1(int creepLineIndex, ParticleManager particleManager, Path path) {
        super(null, img, speed, particleManager, path);
        if(creepLineIndex > 7) {
            this.creepLineIndex = 7;
        } else {
            this.creepLineIndex = creepLineIndex;
        }
        this.creepLineIndex = creepLineIndex;
        setImage(getCreepImage());
    }

    /**
     * Constructor to create a Line1 creep with properties from another creep.
     * @param oldCreep a Creep object from which position is used.
     */
    public Line1(Creep oldCreep) {
        super(img, speed, oldCreep);
        this.creepLineIndex = oldCreep.getCreepLineIndex()-1;
        setImage(getCreepImage());
    }

    /**
     * Constructor to create a Line1 creep with properties from another creep.
     * @param oldCreep a Creep object from which position is used.
     */
    public Line1(Creep oldCreep, int creepLineIndex) {
        super(img, speed, oldCreep);
        this.creepLineIndex = creepLineIndex;
        setImage(getCreepImage());
    }


    //Logic for devolving line1 creeps
    @Override
    public void devolve() {
        if (!isRemoved()){
            if (creepLineIndex != 1) {
                //Devolves into a new Line 1.
                add(new Line1(this));
            }
            destroy();
        }
    }

    //Getters
    public int getCreepLineIndex() {
        return creepLineIndex;
    }

    //Helpers
    public Image getCreepImage() {
        return new Image(Constants.FILE_PATH + "Creeps/Line3/" + creepLineIndex + ".png");
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(2, 1, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 5;
    }
}
