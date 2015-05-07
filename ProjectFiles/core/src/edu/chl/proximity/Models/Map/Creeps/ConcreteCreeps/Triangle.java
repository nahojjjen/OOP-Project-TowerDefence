package edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson
 * @date 2015-04-08
 *
 * ---
 * Uknown date modified by Johan Swanberg
 * A class for the concrete creep Triangle
 *
 * 23/04 Modified by Simon. Adding resources XP when killing creeps
 * 27-4 Modified by Johan Swanberg - Crashfix for when a creep gets hit by several projectiles the same frame
 */
public class Triangle extends Creep {

    private static Image img = new Image(Constants.FILE_PATH + "Creeps/Line3/triangle.png");
    private static int speed = 1;

    public Triangle(Map map) {
        super(map, img, speed);
    }


    @Override
    public void devolve() {
        if(isDead()){

            displayDeathEffect();
            getMap().getRemoveStack().add(this);

            getMap().addCreep(new Circle(getMap(), this));

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
        return new Resources(20, 10, 0);
    }
    //Logic to obtain creep xp
    public int getCreepExperiencePoints() {
        return 10;
    }
}
