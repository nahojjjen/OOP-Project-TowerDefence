package edu.chl.proximity.Models.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

import java.util.Iterator;

/**
 * @author Linda Evaldsson
 * @date 2015-04-08
 *
 * ---
 * Uknown date modified by Johan Swanberg
 * A class for the concrete creep Triangle
 *
 * 23/04 Modified by Simon. Adding resources XP when killing creeps
 */
public class Triangle extends Creep {

    private static Image img = new Image(Constants.filePath + "Creeps/Line3/triangle.png");
    private static int speed = 1;

    public Triangle() {
        super(img, speed);
    }


    @Override
    public void devolve() {
        Map map = GameData.getInstance().getMap();
        displayDeathEffect();
        map.getRemoveStack().add(this);
        map.addCreep(new Circle(this));

        Resources res = GameData.getInstance().getPlayer().getResources();
        res.addResources(getCreepResource());

        destroy();
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
