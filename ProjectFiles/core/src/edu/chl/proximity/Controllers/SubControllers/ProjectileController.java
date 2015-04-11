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
        /*


        Iterator projectileIterator = projectiles.iterator();

        while (projectileIterator.hasNext()) {
            Object projectileObject = projectileIterator.next();
            Projectile projectile = (Projectile) projectileObject;
            Creep closestVictim = getClosestCreep(projectile.getPosition());
            if (closestVictim != null) {
                projectile.faceTarget(closestVictim.getPosition());
                if (projectile.collidesWith(closestVictim.getPosition(), 20)) {
                    System.out.println("In GodController is trying play a sound, but can not.");

                    map.getParticleManager().getExplosionEffect().createEffect((int)projectile.getPosition().x, (int)projectile.getPosition().y);

                    creeps.remove(closestVictim);
                    map.getParticleManager().getCreepDiesEffect().createEffect(closestVictim.getPosition().x, closestVictim.getPosition().y);
                    projectileIterator.remove();

                }

            }
            projectile.move();

            if (projectile.isOutsideView()) {
                projectileIterator.remove();
            }
        }
*/
    }

}
