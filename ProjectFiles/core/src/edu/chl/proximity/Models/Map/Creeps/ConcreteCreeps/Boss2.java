package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Simon Gislen
 * @date 2015-05-25
 *
 * A class which represent a creep of the type boss2
 *
 */
public class Boss2 extends Creep {

    private int creepLineIndex;
    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Boss2/boss1.png"); //dummy image to get correct resolution
    private static double speed = 1.8;

    public Boss2(ParticleManager particleManager, Path path) {
        super(null, img, speed, particleManager, path);
        creepLineIndex = 65;
        setImage(getCreepImage());
    }


    //Logic for devolving line1 creeps
    @Override
    public void devolve() {
        if (!isRemoved()){
            creepLineIndex--;
            setImage(getCreepImage());
            if (creepLineIndex <= 0) {
                spawnHoard();
             }
         }
    }

    /**
     * spawns Boss1 creeps at the boss location
     */
    private void spawnHoard(){
        for (int i = 0; i < 8; i++) {
            Creep creep = new Boss1(this);
            for(int y = 0; y<i*4; y++){
                creep.move();
                creep.move();
            }
            add(creep);
        }
        destroy();
    }

    //Getters
    public int getCreepLineIndex() {
        return creepLineIndex;
    }

    //Helpers
    public Image getCreepImage() {
        if (creepLineIndex <= 20) {
            return new Image(Constants.FILE_PATH + "Creeps/Boss2/boss1.png");
        }
        else if (creepLineIndex <= 40) {
            return new Image(Constants.FILE_PATH + "Creeps/Boss2/boss2.png");
        }
        return new Image(Constants.FILE_PATH + "Creeps/Boss2/boss3.png");
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(0, 0, 4);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 60;
    }
}
