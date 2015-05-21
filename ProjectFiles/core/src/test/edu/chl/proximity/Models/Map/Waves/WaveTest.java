package test.edu.chl.proximity.Models.Map.Waves;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Waves.Wave;
import junit.framework.TestCase;

import java.util.*;

/**
 * @author simongislen.
 * @date 21/05/15.
 *
 *
 * A Test class for Wave
 */
public class WaveTest extends TestCase {

    private List<Creep> creeps;
    private double spawnInterval;
    private double cooldownTimeInterval;
    private Path path;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        path = new FirstPath();
        creeps = new ArrayList<>();
        creeps.add(new Line1(1, null, path));

        spawnInterval = 4f;
        cooldownTimeInterval = 1f;

    }

    public void testGetCreeps() throws Exception {

        Wave wave = new Wave(creeps, spawnInterval, cooldownTimeInterval);
        assertTrue(wave.getCreeps().size() == 1);
    }

    public void testGetSpawnInterval() throws Exception {
        Wave wave = new Wave(creeps, spawnInterval, cooldownTimeInterval);
        assertTrue(wave.getSpawnInterval() > 0);
    }

    public void testGetCooldownTimeInterval() throws Exception {
        Wave wave = new Wave(creeps, spawnInterval, cooldownTimeInterval);
        assertTrue(wave.getCooldownTimeInterval() > 0);
    }
}