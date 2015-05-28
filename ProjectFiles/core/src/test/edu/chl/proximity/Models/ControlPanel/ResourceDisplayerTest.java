package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ResourceDisplayer;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Linda
 * @date 2015-05-23.
 */
public class ResourceDisplayerTest {

    @Test
    public void testGetFont() throws Exception {
        ResourceDisplayer resourceDisplayer = new ResourceDisplayer(null, new ProximityVector(0, 0));

        assertTrue(resourceDisplayer.getFont() != null);
    }
}