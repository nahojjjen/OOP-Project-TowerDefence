package edu.chl.proximity.Models.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Players.Player;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

import java.io.File;

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
    if (!isDead()){
        if (creepLineIndex != 1) {
            GameData gameData = GameData.getInstance();
            //Devolves into a new Line 1.
            gameData.getMap().addCreep(new Line1(this));

            Player p = GameData.getInstance().getPlayer();
            Resources res = p.getResources();
            res.addResources(getCreepResource());
            p.addExperiencePoints(getCreepExperiencePoints());
        }
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
        return (creepLineIndex + 1) * 3;
    }
}
