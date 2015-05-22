package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.io.File;

/**
 * @author Simon Gislen
 * @date 2015-05-13
 *
 * A class for handling a line of creeps
 *
 * 22/05 modified by Linda Evaldsson. Changed how the images are handled.
 */
public class Line2 extends Creep {

    private int creepLineIndex;
    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Line2/6.png"); //dummy image to get correct resolution
    private static int speed = 3;

    public Line2(int creepLineIndex, ParticleManager particleManager, Path path) {
        super(null, img, speed, particleManager, path);
        if(creepLineIndex > 6) {
            this.creepLineIndex = 6;
        } else {
            this.creepLineIndex = creepLineIndex;
        }
        setImage(getCreepImage());
    }

    /**
     * Constructor to create a Line1 creep with properties from another creep.
     * @param oldCreep a Creep object from which position is used.
     */
    public Line2(Line2 oldCreep) {
        super(img, speed, oldCreep);
        this.creepLineIndex = oldCreep.getCreepLineIndex() - 1;
        setImage(getCreepImage());
    }


    //Logic for devolving line1 creeps
    @Override
    public void devolve() {
        if (!isRemoved()){
            if (creepLineIndex != 1) {
                //Devolves into a new Line 1.
                add(new Line2(this));

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
        return new Resources(1, 2, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 5;
    }
}
