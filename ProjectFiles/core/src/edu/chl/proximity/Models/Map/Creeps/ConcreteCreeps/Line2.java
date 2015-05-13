package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.io.File;

/**
 * Created by simongislen on 13/05/15.
 */
public class Line2 extends Creep {
    private static File[] imageFiles = new File(Constants.FILE_PATH + "Creeps/Line2/").listFiles();
    private int creepLineIndex;
    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Line2/6.png"); //dummy image to get correct resolution
    private static int speed = 3;

    public Line2(int creepLineIndex, ParticleManager particleManager, Path path) {
        super(null, img, speed, particleManager, path);
        this.creepLineIndex = creepLineIndex;
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
        if (!isDead()){
            if (creepLineIndex != 1) {
                //Devolves into a new Line 1.
                add(new Line2(this));

            }
            Player p = GameData.getInstance().getPlayer();
            Resources res = p.getResources();
            res.addResources(getCreepResource());
            p.addExperiencePoints(getCreepExperiencePoints());

            destroy();
        }
        markAsDead(); //make sure that the creep cannot be killed by several projectiles in the same frame
    }

    //Getters
    public int getCreepLineIndex() {
        return creepLineIndex;
    }

    //Helpers
    public Image getCreepImage() {
        Image image = null;
        for (File imageFile : imageFiles) {
            String name = imageFile.getName();
            if (name.equals("" + creepLineIndex + ".png")) {
                image = new Image(imageFile);
                break;
            }
        }
        if (image == null) {
            image = new Image(Constants.FILE_PATH + "Creeps/Line1/7.png");
        }
        return image;
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources((creepLineIndex + 1) * 2, creepLineIndex, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 5;
    }
}
