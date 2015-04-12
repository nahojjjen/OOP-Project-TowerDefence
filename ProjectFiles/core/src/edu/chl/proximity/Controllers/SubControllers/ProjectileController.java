package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Projectiles.Projectile;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class ProjectileController {

    private List<Projectile> projectiles;

    public ProjectileController(){
        Map map = GameData.getInstance().getMap();
        projectiles = map.getProjectiles();
    }

    public void update() {
        Iterator projectileIterator = projectiles.iterator();

        while (projectileIterator.hasNext()) {
            Object projectileObject = projectileIterator.next();
            Projectile projectile = (Projectile) projectileObject;

            projectile.reAngle();
            projectile.move(); //check if outside board and remove if true?
            Creep target = projectile.getTarget();
            if (target != null){
                if(projectile.collidesWith(target.getPosition(), 20)){
                    projectile.doCollisionEffect(projectileIterator);
                }
            }
            if (projectile.isOutsideView()){
                projectileIterator.remove();
            }


        }

    }

}
