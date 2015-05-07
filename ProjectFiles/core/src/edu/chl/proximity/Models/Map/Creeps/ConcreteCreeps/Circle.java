package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Simon Gislén
 * @date 2015-04-14
 * 23/04 Modified by Simon. Adding resources and XP when killing creeps
 * 27-4 Modified by Johan Swanberg - Crashfix for when a creep gets hit by several projectiles the same frame
 */
public class Circle extends Creep {

    private static Image img = new Image(Constants.filePath + "Creeps/Line2/7.png");
    private static int speed = 3;

    public Circle(Map map) {
        super(map, img, speed);
    }

    /**
     * Constructor to create a Circle with properties from another creep.
     * @param oldCreep a Creep object from which position is used.
     */
    public Circle(Map map, Creep oldCreep) {
        super(map, img, speed, oldCreep);
    }

    @Override
    public void devolve() {
        if (!isDead()){
            Player p = GameData.getInstance().getPlayer();
            Resources res = p.getResources();
            res.addResources(getCreepResource());
            p.addExperiencePoints(getCreepExperiencePoints());

            destroy();
        }
        markAsDead();
    }

    //Logic to obtain creep resource
    public Resources getCreepResource() {
        return new Resources(10, 20, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 4;
    }

}