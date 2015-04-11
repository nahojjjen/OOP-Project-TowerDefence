package edu.chl.proximity.Models.Creeps.ConcreteCreeps.Line2;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Johan on 2015-04-11.
 */
public class Line2Creep2 extends Creep

    {

        private static Image img = new Image(Constants.filePath + "Creeps/Line2/2.png");
        private static int speed = 3;

        public Line2Creep2() {
        super(img, speed);
    }


        @Override
        public void devolve() {


    }
}
