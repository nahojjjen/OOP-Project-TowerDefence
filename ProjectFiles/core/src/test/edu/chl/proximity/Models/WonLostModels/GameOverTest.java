package test.edu.chl.proximity.Models.WonLostModels;

import edu.chl.proximity.Models.Map.Maps.FillerMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Models.WonLostModels.GameOver;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Romer
 * @date 2015-05-24
 */
public class GameOverTest {

    @Test
    public void testGetButtonActionOnPosition() {
        ParticleManager pm = new ParticleManager(new Settings());
        GameOver go= new GameOver(new FillerMap(pm));
        assertTrue(go.getButtonActionOnPosition(new ProximityVector(200,400)).equals("Resume"));
        assertTrue(go.getButtonActionOnPosition(new ProximityVector(400,400)).equals("MainMenu"));

        assertTrue(go.getButtonActionOnPosition(null) == null);
        assertTrue(go.getButtonActionOnPosition(new ProximityVector(Integer.MIN_VALUE,Integer.MIN_VALUE)) == null);
        assertTrue(go.getButtonActionOnPosition(new ProximityVector(Integer.MAX_VALUE,Integer.MAX_VALUE)) == null);
    }

    @Test
    public void testGetMap(){
        ParticleManager pm = new ParticleManager(new Settings());
        GameOver go= new GameOver(new FillerMap(pm));
        assertTrue(go.getMap() instanceof FillerMap);
    }
}
