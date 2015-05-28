package test.edu.chl.proximity.Models.MenuModels.MapSelect;

import edu.chl.proximity.Models.Map.Maps.FillerMap;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.SmallSpiralMap;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Romer
 * @date 2015-05-24.
 */
public class MapSelectTest {

    @Test
    public void testGetSelectedReturnsMap(){
        ParticleManager pm=new ParticleManager(new Settings());
        MapSelect ms=new MapSelect(pm);
        assertTrue(ms.getSelected() != null);
    }

    @Test
    public void testPressedSetsMapIfMapPressed(){
        ParticleManager pm=new ParticleManager(new Settings());
        MapSelect ms=new MapSelect(pm);

        ms.pressed(new ProximityVector(300,210));//Should set selected map as FillerMap (Since it's on that position)
        assertTrue(ms.getSelected() instanceof FillerMap);

        ms.pressed(null);
        assertTrue(ms.getSelected() instanceof FillerMap);//Should not change anything since nothing was pressed

        ms.pressed(new ProximityVector(Integer.MAX_VALUE, Integer.MIN_VALUE));//Should not change selected map since no map was pressed
        assertTrue(ms.getSelected() instanceof FillerMap);

        ms.pressed(new ProximityVector(Integer.MIN_VALUE, Integer.MIN_VALUE));//Should not change selected map since no map was pressed
        assertTrue(ms.getSelected() instanceof FillerMap);
    }

    @Test
    public void testMapClickedSetsCorrectMap(){
        ParticleManager pm=new ParticleManager(new Settings());
        MapSelect ms=new MapSelect(pm);

        ms.mapClicked(3); // will fail if the map is not unlocked
        if (GameData.getInstance().getPlayer().hasPlayerWonPreviousMap(new SmallSpiralMap(new ParticleManager(new Settings())))){
            assertTrue(ms.getSelected() instanceof SmallSpiralMap);
        }else{
            assertFalse(ms.getSelected() instanceof SmallSpiralMap);
        }


        ms.mapClicked(0);
        assertTrue(ms.getSelected() instanceof StandardMap);

        ms.mapClicked(-1);
        assertTrue(ms.getSelected() instanceof StandardMap);

        ms.mapClicked(Integer.MAX_VALUE);
        assertTrue(ms.getSelected() instanceof StandardMap);

        ms.mapClicked(Integer.MIN_VALUE);
        assertTrue(ms.getSelected() instanceof StandardMap);

    }

    /*
    Order of maps + pos they're at
    maps.add(new MapSelectIcon(new StandardMap(particleManager), new ProximityVector(200, 200)));
    maps.add(new MapSelectIcon(new FillerMap(particleManager), new ProximityVector(300,210)));
    maps.add(new MapSelectIcon(new DifficultJuggMap(particleManager),new ProximityVector(660,150)));
    maps.add(new MapSelectIcon(new SmallSpiralMap(particleManager),new ProximityVector(420,170)));
    maps.add(new MapSelectIcon(new SnakeMap(particleManager),new ProximityVector(560,140)));
    maps.add(new MapSelectIcon(new ZigZagMap(particleManager),new ProximityVector(750,200)));
    */
}
