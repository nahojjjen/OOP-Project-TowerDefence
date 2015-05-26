package test.edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.SoundBar;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 *
 * A class for testing the class SoundBar
 */
public class SoundBarTest {

    @Test
    public void testSetFilled() throws Exception {
        SoundBar bar = new SoundBar(new ProximityVector(0, 0), 5);
        bar.setFilled();
        assertTrue(bar.isFilled());
    }

    @Test
    public void testSetEmpty() throws Exception {
        SoundBar bar = new SoundBar(new ProximityVector(0, 0), 3);
        bar.setEmpty();
        assertFalse(bar.isFilled());
    }

    @Test
    public void testGetLevel() throws Exception {
        SoundBar bar = new SoundBar(new ProximityVector(0, 0), 3);
        assertTrue(bar.getLevel() == 3);
    }
}