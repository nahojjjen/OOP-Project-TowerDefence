package test.edu.chl.proximity.Models.ControlPanel.ButtonsPanel;

import edu.chl.proximity.Models.ControlPanel.ButtonsPanel.ButtonPanel;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Settings;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Floompa on 2015-05-27.
 */
public class ButtonPanelTest {

    @Test
    public void testGetButtonOnPosition() throws Exception {
        GameData.getInstance().setPlayer(new Player(null));
        Settings settings = GameData.getInstance().getPlayer().getSettings();
        PropertiesPanel propertiesPanel = new PropertiesPanel(settings);
        ButtonPanel buttonPanel = new ButtonPanel(propertiesPanel);

        assertEquals(buttonPanel.getPauseButton(), buttonPanel.getButtonOnPosition(buttonPanel.getPauseButton().getCenter()));
        assertEquals(buttonPanel.getPlayButton(), buttonPanel.getButtonOnPosition(buttonPanel.getPlayButton().getCenter()));
        assertEquals(buttonPanel.getPropertiesButton(), buttonPanel.getButtonOnPosition(buttonPanel.getPropertiesButton().getCenter()));
        assertEquals(buttonPanel.getSpeedButton(), buttonPanel.getButtonOnPosition(buttonPanel.getSpeedButton().getCenter()));

    }

    @Test
    public void testPressedPlay() throws Exception {
        GameData.getInstance().setPlayer(new Player(null));
        Settings settings = GameData.getInstance().getPlayer().getSettings();
        PropertiesPanel propertiesPanel = new PropertiesPanel(settings);
        ButtonPanel buttonPanel = new ButtonPanel(propertiesPanel);

        buttonPanel.pressedPlay();
        assertTrue(settings.getGameSpeed() == 1);
    }

    @Test
    public void testPressedPause() throws Exception {
        GameData.getInstance().setPlayer(new Player(null));
        Settings settings = GameData.getInstance().getPlayer().getSettings();
        PropertiesPanel propertiesPanel = new PropertiesPanel(settings);
        ButtonPanel buttonPanel = new ButtonPanel(propertiesPanel);

        buttonPanel.pressedPause();
        assertTrue(settings.getGameSpeed() == 0);

        buttonPanel.pressedPause();
        assertTrue(settings.getGameSpeed() == 0);

        settings.setGameSpeed(7);
        buttonPanel.pressedPause();
        buttonPanel.pressedPause();
        assertTrue(settings.getGameSpeed() == 0);

    }

    @Test
    public void testPressedSpeedButton() throws Exception {
        GameData.getInstance().setPlayer(new Player(null));
        Settings settings = GameData.getInstance().getPlayer().getSettings();
        PropertiesPanel propertiesPanel = new PropertiesPanel(settings);
        ButtonPanel buttonPanel = new ButtonPanel(propertiesPanel);

        buttonPanel.pressedSpeedButton();
        assertTrue(settings.getGameSpeed() == 2);
    }

    @Test
    public void testPressedPropertiesButton() throws Exception {
        GameData.getInstance().setPlayer(new Player(null));
        Settings settings = GameData.getInstance().getPlayer().getSettings();
        PropertiesPanel propertiesPanel = new PropertiesPanel(settings);
        ButtonPanel buttonPanel = new ButtonPanel(propertiesPanel);

        assertFalse(propertiesPanel.isVisible());
        buttonPanel.pressedPropertiesButton();
        assertTrue(propertiesPanel.isVisible());
    }
}