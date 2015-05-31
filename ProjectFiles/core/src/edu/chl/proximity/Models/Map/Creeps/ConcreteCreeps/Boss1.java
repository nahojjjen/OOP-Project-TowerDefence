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
 * A Boss1 class that is responsible for a boss.
 */
public class Boss1 extends Creep {

    private int creepLineIndex;
    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Boss1/boss1.png"); //dummy image to get correct resolution
    private static double speed = 2;

    public Boss1(ParticleManager particleManager, Path path) {
        super(null, img, speed, particleManager, path);
        creepLineIndex = 40;
        setImage(getCreepImage());
    }

    public Boss1(Creep oldCreep) {
        super(img, speed, oldCreep);
        creepLineIndex = 40;
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
     * spawns 20 creeps at the boss location
     */
    private void spawnHoard(){
        for (int i = 0; i < 10; i++) {
            Creep creep = new Line1(this, 6);
            Creep creep2 = new Line2(this, 6);
            for(int y = 0; y<i; y++){
                creep.move();
                creep2.move();
                creep2.move();
            }
            add(creep);
            add(creep2);
        }
        destroy();
    }

    //Getters
    public int getCreepLineIndex() {
        return creepLineIndex;
    }

    //Helpers
    public Image getCreepImage() {
        if (creepLineIndex <= 15) {
            return new Image(Constants.FILE_PATH + "Creeps/Boss1/boss1.png");
        }
        else if (creepLineIndex <= 30) {
            return new Image(Constants.FILE_PATH + "Creeps/Boss1/boss2.png");
        }
        return new Image(Constants.FILE_PATH + "Creeps/Boss1/boss3.png");
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(0, 0, 2);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 30;
    }
}
