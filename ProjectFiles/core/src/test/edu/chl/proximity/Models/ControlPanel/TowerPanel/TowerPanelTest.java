package test.edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.ControlPanel.TowerPanel.TowerPanel;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetFirst;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Map.Towers.TargetingTower;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/*
 * @author Linda Evaldsson
  * @date 2015-05-23
  *
 */
public class TowerPanelTest {

    @Test
    public void testPressedPosition() throws Exception {
        Player player = new Player(null);
        player.addResources(new Resources(1000, 1000, 1000));
        GameData.getInstance().setPlayer(player);
        ProximityVector defaultVector = new ProximityVector(0, 0);
        Map map = new StandardMap(new ParticleManager(new Settings()));
        TowerPanel towerPanel = new TowerPanel(map);
        BulletTower tower = new BulletTower(defaultVector, new TargetFirst(), map.getParticleManager());
        map.add(tower);
        map.setChosenTower(tower);
        towerPanel.setInfo();

        //Testing targeting
        List<ProximityVector> list = towerPanel.getCheckBoxCenters();

        TargetingMethod lastTargetingMethod = tower.getTargetingMethod();
        for(ProximityVector vector : list) {
            towerPanel.pressedPosition(vector);
            assertTrue(lastTargetingMethod != tower.getTargetingMethod());
            lastTargetingMethod = tower.getTargetingMethod();
        }

        //Testing upgrade
        towerPanel.pressedPosition(towerPanel.getUpgradeCenter());
        assertTrue(map.getChosenTower().getClass() == tower.getUpgrade().getClass());
        map.clearRemoveStack();

        //Testing sell
        int objects = map.getNumberOfObjectsOnMap();
        towerPanel.pressedPosition(towerPanel.getSellCenter());
        map.clearRemoveStack();
        assertTrue(map.getNumberOfObjectsOnMap() == objects - 1);
    }


    @Test
    public void testSetInfo() throws Exception {
        Player player = new Player(null);
        player.addResources(new Resources(1000, 1000, 1000));
        GameData.getInstance().setPlayer(player);
        ProximityVector defaultVector = new ProximityVector(0, 0);
        Map map = new StandardMap(new ParticleManager(new Settings()));
        TowerPanel towerPanel = new TowerPanel(map);
        BulletTower tower = new BulletTower(defaultVector, new TargetFirst(), map.getParticleManager());
        map.add(tower);
        map.setChosenTower(tower);
        assertTrue(towerPanel.getTowerName() == null);
        towerPanel.setInfo();
        assertEquals(towerPanel.getTowerName(), tower.getName());
    }
}