package edu.chl.proximity.Models.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Simon Gislén
 * @date 2015-04-21
 *
 * 23/04 Modified by Simon. Adding resources and XP when killing creeps
 * 24/4 Modified by Johan Swanberg - Makes creeps correctly follow path by making getCenter work correctly- fixing images
 */
public class Line1 extends Creep {

    private static File[] imageFiles = new File(Constants.filePath + "Creeps/Line3/").listFiles();
    private int creepLineIndex;
    private static Image img = new Image(Constants.filePath + "Creeps/Line2/7.png"); //dummy image to get correct resolution
    private static int speed = 5;

    public Line1(int creepLineIndex) {
        super(img, speed);
        this.creepLineIndex = creepLineIndex;
        setImage(getCreepImage());
    }

    /**
     * Constructor to create a Line1 creep with properties from another creep.
     * @param oldCreep a Creep object from which position is used.
     */
    public Line1(Line1 oldCreep) {
        super(img, speed, oldCreep);
        this.creepLineIndex = oldCreep.getCreepLineIndex() - 1;
        setImage(getCreepImage());
    }


    //Logic for devolving line1 creeps
    @Override
    public void devolve() {

        if (creepLineIndex != 1) {
            GameData gameData = GameData.getInstance();
            //Devolves into a new Line 1.
            gameData.getMap().addCreep(new Line1(this));

            Resources res = gameData.getPlayer().getResources();
            res.addResources(getCreepResource());
        }
        destroy();
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
            image = new Image(Constants.filePath + "Creeps/Line1/7.png");
        }
        return image;
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(creepLineIndex * 2, creepLineIndex, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return creepLineIndex * 2;
    }
}
