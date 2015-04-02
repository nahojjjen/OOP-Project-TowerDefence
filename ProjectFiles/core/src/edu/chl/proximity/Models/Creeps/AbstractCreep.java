package edu.chl.proximity.Models.Creeps;

import com.badlogic.gdx.audio.Sound;
import edu.chl.proximity.Models.BoardObject;

import java.awt.*;

/**
 * Created by Tindra on 02/04/15.
 */
public abstract class AbstractCreep extends BoardObject {

    private Point nextWayPoint;
    private int distanceToNextWayPoint;
    //private Path path;
    private Sound devolveSound;

    public AbstractCreep() {


    }

    public void devolve() {


    }
}
