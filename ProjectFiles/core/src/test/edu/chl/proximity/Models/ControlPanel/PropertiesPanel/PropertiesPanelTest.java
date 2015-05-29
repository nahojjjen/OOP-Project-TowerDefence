package test.edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.SoundBar;
import edu.chl.proximity.Models.Utils.Settings;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-26
 *
 */
public class PropertiesPanelTest {

    @Test
    public void testSetVisibilityAndIsVisible() throws Exception {
        PropertiesPanel propertiesPanel = new PropertiesPanel(new Settings());
        assertTrue(propertiesPanel.isVisible() == false);
        propertiesPanel.setVisibility(true);
        assertTrue(propertiesPanel.isVisible() == true);
        propertiesPanel.setVisibility(false);
        assertTrue(propertiesPanel.isVisible() == false);
    }

    @Test
    public void testSetSoundAt() throws Exception {
        Settings settings = new Settings();
        PropertiesPanel propertiesPanel = new PropertiesPanel(settings);
        propertiesPanel.setMusicSoundAt(5);
        assertTrue(settings.getMusicVolume() == 5);
        propertiesPanel.setMusicSoundAt(-5);
        assertTrue(settings.getMusicVolume() == 0);
        propertiesPanel.setMusicSoundAt(-5);
        assertTrue(settings.getMusicVolume() == 5);
        propertiesPanel.setMusicSoundAt(100);
        assertTrue(settings.getMusicVolume() == 5);


    }

    @Test
    public void testPressButton() throws Exception {
        Settings settings = new Settings();
        PropertiesPanel propertiesPanel = new PropertiesPanel(settings);
        propertiesPanel.setVisibility(true);

        //Pressing Main Menu
        propertiesPanel.pressButton(propertiesPanel.getMainMenuButton());
        assertTrue(propertiesPanel.isVisible() == false);

        //Pressing Toggle sound
        int soundBefore = settings.getMusicVolume();
        int soundBarsBefore = propertiesPanel.getMusicBarLevel();
        propertiesPanel.pressButton(propertiesPanel.getMusicSoundButton());
        assertTrue(settings.getMusicVolume() == 0);
        assertTrue(propertiesPanel.getMusicBarLevel() == 0);

        propertiesPanel.pressButton(propertiesPanel.getMusicSoundButton());
        assertTrue(settings.getMusicVolume() == soundBefore);
        assertTrue(propertiesPanel.getMusicBarLevel() == soundBarsBefore);

        //Pressing Resume
        assertTrue(settings.getGameSpeed() == 1);
        propertiesPanel.pressButton(propertiesPanel.getResumeButton());
        assertTrue(settings.getGameSpeed() == 0);
        assertFalse(propertiesPanel.isVisible());

        //Pressing every bar
        for(int i = 0; i < propertiesPanel.getMusicBars().size(); i++) {
            propertiesPanel.pressButton(propertiesPanel.getMusicBars().get(i));
            assertTrue(settings.getMusicVolume() == i+1);
            assertTrue(propertiesPanel.getMusicBarLevel() == i+1);
        }

    }

    @Test
    public void testGetButtonOnPosition() throws Exception {

        PropertiesPanel propertiesPanel = new PropertiesPanel(new Settings());
        assertEquals(propertiesPanel.getMainMenuButton(), propertiesPanel.getButtonOnPosition(propertiesPanel.getMainMenuButton().getCenter()));
        assertEquals(propertiesPanel.getResumeButton(), propertiesPanel.getButtonOnPosition(propertiesPanel.getResumeButton().getCenter()));
        assertEquals(propertiesPanel.getMusicSoundButton(), propertiesPanel.getButtonOnPosition(propertiesPanel.getMusicSoundButton().getCenter()));

        for(SoundBar bar : propertiesPanel.getMusicBars()) {
            assertEquals(bar, propertiesPanel.getButtonOnPosition(bar.getCenter()));
        }

    }


    @Test
    public void testContainsPoint() throws Exception {

        PropertiesPanel propertiesPanel = new PropertiesPanel(new Settings());
        assertFalse(propertiesPanel.containsPoint(propertiesPanel.getCenter()));
        propertiesPanel.setVisibility(true);
        assertTrue(propertiesPanel.containsPoint(propertiesPanel.getCenter()));
    }
}