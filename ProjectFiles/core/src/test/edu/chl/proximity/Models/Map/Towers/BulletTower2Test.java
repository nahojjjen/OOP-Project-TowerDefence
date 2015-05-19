package test.edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Circle;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Towers.BulletTower2;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @date 2015-05-19.
 */
public class BulletTower2Test {

    @Test
    public void testTarget() throws Exception {
    BulletTower2 test = new BulletTower2(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
        double angle= test.getAngle();
        List list = new ArrayList<Creep>();
        test.target(list);
        assertTrue(test.getAngle() == angle); //targeting nothing should not have changed the angle
        test.target(null);
        assertTrue(test.getAngle() == angle); //targeting nothing should not have changed the angle
        Creep creep = new Circle(new ParticleManager(), new FirstPath());
        list.add(creep);
        creep.setPosition(new ProximityVector(100, 100));
        test.setPosition(new ProximityVector(110, 100)); // place the tower to the left of the creep
        test.target(list);
        assertTrue(test.getAngle() == 180 && test.getAngle() != angle); //make sure the tower turned

    }

    @Test//(expected=java.lang.ExceptionInInitializerError.class)
    public void testShoot() throws Exception {
        BulletTower2 test = new BulletTower2(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
        test.shoot(null); //make sure it can handle a null argument
        List list = new ArrayList<Creep>();
        test.shoot(list);
        Creep creep = new Circle(new ParticleManager(), new FirstPath());
        creep.setPosition(new ProximityVector(100,100));
        list.add(creep);
        test.preparePlacing(new FirstPath().getWaypoint(0));
        test.setCenter(new ProximityVector(999, 999));
        test.shoot(list);

        test.setCenter(new ProximityVector(1,1));
        for (int i = 0; i<1000; i++){
            test.shoot(list);
        }

    }

    @Test
    public void testGetTarget() throws Exception {

        Tower test = new BulletTower2(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
    }

    @Test
    public void testCreateProjectile() throws Exception {

        Tower test = new BulletTower2(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
    }

    @Test
    public void testUpdate() throws Exception {

        Tower test = new BulletTower2(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
    }

    @Test
    public void testGetNewUpgrade() throws Exception {

        Tower test = new BulletTower2(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
    }

    @Test
    public void testGetRange() throws Exception {

        Tower test = new BulletTower2(new ProximityVector(0,0), new TargetClosest(), new ParticleManager());
    }
}