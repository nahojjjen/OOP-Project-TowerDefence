package edu.chl.proximity.Models.Creeps;

import edu.chl.proximity.Models.Image;
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


    @Override
    public void devolve() {

    }
}
