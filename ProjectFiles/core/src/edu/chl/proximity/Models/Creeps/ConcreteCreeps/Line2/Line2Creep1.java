package edu.chl.proximity.Models.Creeps.ConcreteCreeps.Line2;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Johan on 2015-04-11.
 */
public class Line2Creep1 extends Creep

    {

        private static Image img = new Image(Constants.filePath + "Creeps/Line2/1.png");
        private static int speed = 1;

        public Line2Creep1() {
        super(img, speed);
    }


        @Override
        public void devolve() {

    }
}
