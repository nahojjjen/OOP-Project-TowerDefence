package edu.chl.proximity.Models.Towers;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Projectiles.Projectile;

import java.awt.Point;


/**
 * Created by Johan on 4/2/2015.
 */
public abstract class Tower extends BoardObject {

    private int bulletSpeed = 10;
    private int reloadTime = 0;
    private Projectile projectileType;

    /**
     *
     */
    public Tower(Point pos, Image image, Projectile projectile){
        //point texture angle
        super(pos, image, 0);
        projectileType = projectile;
    }


    /**
     * create a projectile at the towers location, if the tower can shoot
     * if the tower shoots, start the reload time
     */
    public void shoot(){
        if(reloadTime < 1){
            Map.getInstance().addProjectile(projectileType);
            reloadTime = 100;
        }

    }
    /**
     * decrease the reload time, tower can shoot when reload is at 0
     */
    public void reload(){
        if(reloadTime > 0){
            reloadTime --;
        }
    }

}
