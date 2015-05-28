package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetClosest;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 *
 * A class for testing the class ControlPanelTower
 */
public class ControlPanelTowerTest {

    @Test
    public void testCreatingControlPanelTower() throws Exception {
        ProximityVector defaultVector = new ProximityVector(0, 0);
        Tower tower = new BulletTower(defaultVector, new TargetClosest(), new ParticleManager(new Settings()));
        new ControlPanelTower(defaultVector, tower);
        try {
            new ControlPanelTower(defaultVector, null);
            fail("It should not be possible to create a ControlPanelTower with tower set to null");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCreatingTowerAndGetTower() throws Exception {

        ProximityVector defaultVector = new ProximityVector(0, 0);
        Tower tower = new BulletTower(defaultVector, new TargetClosest(), new ParticleManager(new Settings()));
        ControlPanelTower cpTower = new ControlPanelTower(defaultVector, tower);
        assertFalse(tower == cpTower.getTower());

    }

    @Test
    public void testSetAndGetKeyBind() throws Exception {
        ProximityVector defaultVector = new ProximityVector(0, 0);
        Tower tower = new BulletTower(defaultVector, new TargetClosest(), new ParticleManager(new Settings()));
        ControlPanelTower cpTower = new ControlPanelTower(defaultVector, tower);

        cpTower.setKeyBind(4);
        assertEquals(4, cpTower.getKeyBind());

        cpTower.setKeyBind(-5);
        assertEquals(4, cpTower.getKeyBind());

        cpTower.setKeyBind(100);
        assertEquals(4, cpTower.getKeyBind());
    }


    @Test
    public void testSetPosition() throws Exception {
        ProximityVector defaultVector = new ProximityVector(0, 0);
        Tower tower = new BulletTower(defaultVector, new TargetClosest(), new ParticleManager(new Settings()));
        ControlPanelTower cpTower = new ControlPanelTower(defaultVector, tower);

        ProximityVector testVector = new ProximityVector(5, 6);
        cpTower.setPosition(testVector);
        assertEquals(testVector, cpTower.getPosition());

    }

    @Test
    public void testRender() throws Exception {

    }
}