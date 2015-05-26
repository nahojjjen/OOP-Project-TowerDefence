package test.edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line2;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.ShootingTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Johan
 * @date 2015-05-19.
 */
public class TargetClosestTest {
    @Test
    public void testGetTarget() throws Exception {
        List<Creep> list = new ArrayList<Creep>();

        ShootingTower tower = new BulletTower(new ProximityVector(3, -30), new TargetClosest(), new ParticleManager(new Settings()));
        double firstAngle = tower.getAngle();
        tower.target(list);
        assertTrue(tower.getAngle() == firstAngle);


        for (int i = 0; i < 1000; i++) {
            Creep creep = new Line2(5, new ParticleManager(new Settings()), new FirstPath());
            for (int y = 0; y < i; y++) {
                creep.move(); //make sure all creeps are standing on differenty positions
            }

            creep.setPosition(new ProximityVector(i, i));
            list.add(creep);
        }


        tower.target(list);
        assertTrue(tower.getAngle() == 79.29864501953125 );
        //if the tower targets the right creep in this situation, its angle should have changed to 79.29864501953125 degrees
    }
}