package edu.chl.proximity.Models.Creeps.ConcreteCreeps;

import com.badlogic.gdx.Game;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Simon Gislén
 * @date 2015-04-14
 * 23/04 Modified by Simon. Adding resources and XP when killing creeps
 */
public class Circle extends Creep {

    private static Image img = new Image(Constants.filePath + "Creeps/Line2/7.png");
    private static int speed = 3;

    public Circle() {
        super(img, speed);
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

        Resources res = GameData.getInstance().getPlayer().getResources();
        res.addResources(getCreepResource());

        destroy();
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(10, 20, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 8;
    }

}