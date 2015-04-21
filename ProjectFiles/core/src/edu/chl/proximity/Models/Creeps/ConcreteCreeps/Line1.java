package edu.chl.proximity.Models.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Utilities.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author simongislen on 21/04/15.
 */
public class Line1 extends Creep {

    private static File[] imageFiles = new File(Constants.filePath + "Creeps/Line1/").listFiles();
    private int creepLineIndex;
    //private static Image img = new Image(Constants.filePath + "Creeps/Line2/7.png");
    private static int speed = 5;

    public Line1(int creepLineIndex) {
        super(getCreepImage(creepLineIndex), speed);
        this.creepLineIndex = creepLineIndex;
    }

    /**
     * Constructor to create a Line1 creep with properties from another creep.
     * @param oldCreep a Creep object from which position is used.
     */
    public Line1(Line1 oldCreep) {
        super(getCreepImage(oldCreep.getCreepLineIndex() - 1), speed, oldCreep);
        this.creepLineIndex = oldCreep.getCreepLineIndex() - 1;
    }


    //Logic for devolving line1 creeps
    @Override
    public void devolve() {

        if (creepLineIndex != 1) {
            Map map = GameData.getInstance().getMap();
            //Devolves into a new Line 1.
            map.addCreep(new Line1(this));
        }
        destroy();
    }

    //Getters
    public int getCreepLineIndex() {
        return creepLineIndex;
    }

    //Helpers
    public static Image getCreepImage(int creepLineIndex) {
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
}
