package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-19
 *
 * A class for testing the ControlPanel class
 */
public class ControlPanelTest {


    @Test
    public void testGetMap() throws Exception {
        Map map = new StandardMap();
        ControlPanel cp = new ControlPanel(map, null, null);
        assertEquals(map, cp.getMap());
        assertEquals(map, cp.getMap());

        try {
            ControlPanel cp2 = new ControlPanel(null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
    @Test
    public void testInitiateText() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(), null, null);
        assertNotEquals(null, cp.getLineText());
        assertNotEquals(null, cp.getPercentBar());
        assertNotEquals(null, cp.getPolygonText());
        assertNotEquals(null, cp.getPointText());
    }
    @Test
    public void testInitiateControlPanelTowers() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(), null, null);
        assertTrue(cp.getControlPanelTowerListSize() > 0);
    }
    @Test
    public void testSetHealth() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(), null, null);
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
        ControlPanel cp = new ControlPanel(new StandardMap(), null, null);
        Resources resources = new Resources(100, 10, 50);
        cp.setResources(null);
        cp.setResources(resources);
        cp.getLineText().getText().equals("Lines: 100");
    }
    @Test
    public void testGetTowerOnPosition() throws Exception {

        ControlPanel cp = new ControlPanel(new StandardMap(), null, null);
        ProximityVector vector = new ProximityVector(0, 0);
        for(int i = 0; i < 1000; i++) {
            ProximityRandom random = new ProximityRandom();
            vector = new ProximityVector((float)random.getRandomDoubleBetween(cp.getPosition().x, cp.getPosition().x + cp.getWidth()), (float)random.getRandomDoubleBetween(cp.getPosition().y, cp.getPosition().y + cp.getHeight()));
            assertTrue(cp.getTowerOnPosition(vector) == null || cp.getTowerOnPosition(vector) instanceof ControlPanelTower);
        }

    }

    @Test
    public void testGetTowerBoundTo() throws Exception {
        ControlPanel cp = new ControlPanel(new StandardMap(), null, null);
        assertEquals(cp.getTowerBoundTo(1).getClass(), BulletTower.class);

        assertTrue(cp.getTowerBoundTo(0) == null);
        assertTrue(cp.getTowerBoundTo(100) == null);
        assertTrue(cp.getTowerBoundTo(-5) == null);
    }
}