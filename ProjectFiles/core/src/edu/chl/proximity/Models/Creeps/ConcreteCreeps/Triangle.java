package edu.chl.proximity.Models.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Linda on 2015-04-08.
 */
public class Triangle extends Creep {

    private static Image img = new Image(Constants.filePath + "Creeps/Line1/triangle.png");
    private static int speed = 3;

    public Triangle() {
        super(img, speed);
    }


    /**
     * on death,
     */
    @Override
    public void devolve() {
        Map map = GameData.getInstance().getMap();
        displayDeathEffect();
        map.getCreeps().remove(this);
    }
}
