package test.edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.SoundButton;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-27
 *
 * A class to test the class SoundButton
 */
public class SoundButtonTest {

    @Test
    public void testSetSoundOn() throws Exception {
        SoundButton soundButton = new SoundButton(new ProximityVector(0, 0));
        soundButton.setSoundOn();
        assertTrue(soundButton.isSoundOn());

    }

    @Test
    public void testSetSoundOff() throws Exception {
        SoundButton soundButton = new SoundButton(new ProximityVector(0, 0));
        soundButton.setSoundOff();
        assertFalse(soundButton.isSoundOn());

    }
}