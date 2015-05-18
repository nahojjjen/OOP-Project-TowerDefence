package test.edu.chl.proximity.Models.Map.Creeps;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import junit.framework.TestCase;

/**
 * @author simongislen.
 * @date 18/05/15.
 *
 *
 * A Test class for Creep
 */
public class CreepTest extends TestCase {

    private Map map;
    private Path path;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        map = new StandardMap();
        path = new FirstPath();
    }

    public void testMarkAsDead() throws Exception {
        Creep creep = new Line1(1, null, path);
        creep.markAsDead();
        assertTrue(creep.isDead());
    }

    public void testIsDead() throws Exception {
        Creep creep = new Line1(1, null, path);
        creep.markAsDead();
        assertTrue(creep.isDead());
    }

    public void testSetupCreep() throws Exception {

        Creep creep = new Line1(1, null, path);
        creep.setupCreep(2f);

        double expected = 2f;
        double actual = creep.getSpeed();
        assertEquals(expected, actual);
    }

    public void testGetNextWayPointID() throws Exception {
        Creep creep = new Line1(1, null, path);
        int nextWayPoint = creep.getNextWayPointID();
        assertTrue(nextWayPoint == 1);
    }

    public void testGetDistanceToNextWayPoint() throws Exception {

    }

    public void testDisplayDeathEffect() throws Exception {

    }

    public void testDestroy() throws Exception {

    }

    public void testRotate() throws Exception {

    }

    public void testDevolve() throws Exception {

    }

    public void testGetCreepResource() throws Exception {

    }

    public void testGetCreepExperiencePoints() throws Exception {

    }

    public void testMove() throws Exception {

    }

    public void testGetAngleToNextPoint() throws Exception {

    }

    public void testReachedLastWayPoint() throws Exception {

    }

    public void testSlowDown() throws Exception {

    }

    public void testCheckIfSpeedUp() throws Exception {

    }

    public void testGetParticleManager() throws Exception {

    }

    public void testGetPath() throws Exception {

    }
}