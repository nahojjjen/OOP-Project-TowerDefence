package edu.chl.proximity.Utilities;

import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;

import java.awt.*;

/**
 * Created by Johan on 2015-04-02.
 * a class that represents an area, either defined by an elipse or a polygon
 * if both are present then the polygon will be used
 *
 * This class is needed because polygon and ellipse  both implement the interface Shape2D, but this interface does
 * not specify contains();. An object that can sometimes be a circle and sometimes be a polygon is needed.
 */
public class Area {
    private Polygon polygon;
    private Ellipse ellipse;


    /**
     * Define an area by eithe a polygon or an ellipse.
     *
     * @param polygon
     * @param ellipse
     */
    public Area(Polygon polygon, Ellipse ellipse){
        this.polygon = polygon;
        this.ellipse = ellipse;
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean contains(Point p){
        if (polygon != null){
            return polygon.contains((int)p.getX(), (int)p.getY());
        }else if(ellipse != null){
            return ellipse.contains((int)p.getX(), (int)p.getY());
        }
    return false;
    }

}
