package test.edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.SoundButton;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
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
        Image on=new Image(Constants.FILE_PATH + "Buttons/SoundOnButton.png");
        Image off= new Image(Constants.FILE_PATH + "Buttons/SoundOffButton.png");
        SoundButton soundButton = new SoundButton(new ProximityVector(0, 0),on,off);
        soundButton.setSoundOn();
        assertTrue(soundButton.isSoundOn());

    }

    @Test
    public void testSetSoundOff() throws Exception {
        Image on=new Image(Constants.FILE_PATH + "Buttons/SoundOnButton.png");
        Image off= new Image(Constants.FILE_PATH + "Buttons/SoundOffButton.png");
        SoundButton soundButton = new SoundButton(new ProximityVector(0, 0),on,off);
        soundButton.setSoundOff();
        assertFalse(soundButton.isSoundOn());

    }
}