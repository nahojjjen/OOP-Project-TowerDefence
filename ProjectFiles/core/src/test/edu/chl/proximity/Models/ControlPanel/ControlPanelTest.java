package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-19
 *
 * A class for testing the ControlPanel class
 *
 * 28/05/15 Modified by Simon Gislen. Bug fixes.
 */
public class ControlPanelTest {


    @Test
    public void testGetMap() throws Exception {
        Map map = new StandardMap(new ParticleManager(new Settings()));
        ControlPanel cp = new ControlPanel(map);
        assertEquals(map, cp.getMap());
        assertEquals(map, cp.getMap());

        try {
            new ControlPanel(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
    @Test
    public void testInitiateText() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(new ParticleManager(new Settings())));
        assertNotEquals(null, cp.getPercentBar());
        assertNotEquals(null, cp.getResourceDisplayerCollection());

    }
    @Test
    public void testInitiateControlPanelTowers() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(new ParticleManager(new Settings())));
        assertTrue(cp.getControlPanelTowerListSize() > 0);
    }
    @Test
    public void testSetHealth() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(new ParticleManager(new Settings())));
        cp.setHealth(10);
        assertEquals(cp.getHealthPercent(), 10);

        cp.setHealth(0);
        assertEquals(cp.getHealthPercent(), 0);

        cp.setHealth(100);
        assertEquals(cp.getHealthPercent(), 100);

        cp.setHealth(150);
        assertEquals(cp.getHealthPercent(), 100);

        cp.setHealth(-50);
        assertEquals(cp.getHealthPercent(), 0);

    }
    @Test
    public void testSetResources() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(new ParticleManager(new Settings())));
        Resources resources = new Resources(100, 10, 50);
        cp.setResources(null);
        cp.setResources(resources);
        assertTrue(cp.getResourceDisplayerCollection().getResources().getPoints() == 100);
    }
    @Test
    public void testGetTowerOnPosition() throws Exception {

        ControlPanel cp = new ControlPanel(new StandardMap(new ParticleManager(new Settings())));
        for(int i = 0; i < 1000; i++) {
            ProximityVector vector = new ProximityVector((float)ProximityRandom.getRandomDoubleBetween(cp.getPosition().x, cp.getPosition().x + cp.getWidth()), (float)ProximityRandom.getRandomDoubleBetween(cp.getPosition().y, cp.getPosition().y + cp.getHeight()));
            assertTrue(cp.getTowerOnPosition(vector) == null || cp.getTowerOnPosition(vector) instanceof ControlPanelTower); //Findbugs false warning
        }

    }

    @Test
    public void testGetTowerBoundTo() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(new ParticleManager(new Settings())));
        assertEquals(cp.getTowerBoundTo(1).getClass(), BulletTower.class);

        assertTrue(cp.getTowerBoundTo(0) == null);
        assertTrue(cp.getTowerBoundTo(100) == null);
        assertTrue(cp.getTowerBoundTo(-5) == null);
    }
}