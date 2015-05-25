package test.edu.chl.proximity.Models.Utils.CreepGenerator;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Waves.Wave;
import edu.chl.proximity.Models.CreepGenerator.StandardGenerator;
import edu.chl.proximity.Models.Utils.Settings;
import junit.framework.TestCase;

/**
 * @author Simon Gislen
 * @date 18/05/15.
 *
 * A Test class for StandardGenerator.
 */
public class StandardGeneratorTest extends TestCase {

    private Map map;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        map = new StandardMap(new ParticleManager(new Settings()));

    }

    public void testGenerateWaveForWaveIndex() throws Exception {
        StandardGenerator generator = new StandardGenerator(map);

        int firstWaveIndex = 1;
        Wave wave = generator.generateWaveForWaveIndex(firstWaveIndex);
        assertNotNull(wave.getCreeps());
        assertTrue(wave.getCooldownTimeInterval() >= 0f);
        assertTrue(wave.getSpawnInterval() >= 0f);

        int highWaveIndex = 999;
        wave = generator.generateWaveForWaveIndex(highWaveIndex);
        assertNotNull(wave.getCreeps());
        assertTrue(wave.getCooldownTimeInterval() >= 0f);
        assertTrue(wave.getSpawnInterval() >= 0f);

        int minusWaveIndex = -1;
        wave = generator.generateWaveForWaveIndex(minusWaveIndex);
        assertNull(wave);

        int maxWaveIndex = Integer.MAX_VALUE;
        wave = generator.generateWaveForWaveIndex(maxWaveIndex);
        assertNull(wave);
    }
}