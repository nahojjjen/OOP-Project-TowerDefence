package test.edu.chl.proximity.Models.MenuModels.MapSelect;

import edu.chl.proximity.Models.Map.Maps.FillerMap;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelectIcon;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Romer
 * @date 2015-05-24
 */
public class MapSelectIconTest {

    @Test
    public void testGetMap(){
        ParticleManager p=new ParticleManager(new Settings());
        MapSelectIcon msi=new MapSelectIcon(new FillerMap(p),new ProximityVector(0,0));
        assertTrue(msi.getMap() instanceof FillerMap);
    }
    //Cannot test anything else
}
