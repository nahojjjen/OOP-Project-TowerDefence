package edu.chl.proximity.Models.Creeps.ConcreteCreeps;

import com.badlogic.gdx.graphics.Texture;
import edu.chl.proximity.Models.Creeps.AbstractCreep;
import edu.chl.proximity.Models.Image;

/**
 * Created by Johan on 2015-04-02.
 */
public class TriangleCreep  extends AbstractCreep{

    public TriangleCreep(){
        super(new Image("assets/Creeps/Line1/triangle.png"), 1);
    }

    @Override
    public void devolve() {
        //create particle effects and
        //The controller calls this method, and then deletes the creep from the list
    }
}
