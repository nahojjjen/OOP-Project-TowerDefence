package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ButtonsPanel.*;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.ControlPanel.ProfilePanel;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 *
 * 29/04 modified by Linda Evaldsson. Merged this class with PropertiesPanelController (created by Hanna R�mer 2015-04-23) and ButtonPanelController (created by Hanna R�mer 2015-04-23).
 * 29/04 modified by Hanna R�mer. Added Play and Pause Buttons to possible clicked buttons on ButtonsPanel. Removed PausePlayButton option.
 * 30/04 modified by Simon Gislen. Added ProfilePanel.
 * 30/04 modified by Hanna R�mer. Cannot click on anything else when propertiesPanel is open, cannot place towers when game is paused.
 * 03/05 modified by Simon Gislen. Towers are not free anymore.
 */
public class ControlPanelController implements ClickHandler {

    private ControlPanel controlPanel = new ControlPanel();
    private ButtonPanel buttonPanel = new ButtonPanel();
    private PropertiesPanel propertiesPanel=PropertiesPanel.getInstance();
    private ProfilePanel profilePanel = new ProfilePanel();

    private List<BoardObject> models = new ArrayList<BoardObject>();

    public ControlPanelController() {
        models.add(controlPanel);
        models.add(buttonPanel);
        models.add(propertiesPanel);
        models.add(profilePanel);
    }

    public void setControlPanel(ControlPanel controlPanel) { this.controlPanel = controlPanel;}
    public void setPropertiesPanel(PropertiesPanel propertiesPanel){ this.propertiesPanel=propertiesPanel;}
    public void setButtonPanel(ButtonPanel buttonPanel) { this.buttonPanel = buttonPanel;}
    public void setProfilePanel(ProfilePanel profilePanel) { this.profilePanel = profilePanel;}

    public void update() {
        controlPanel.setHealth(GameData.getInstance().getMap().getBase().getLife());
        controlPanel.setResources(GameData.getInstance().getPlayer().getResources());
        profilePanel.updateExperience();
    }


    @Override
    public void mouseMoved(Vector2 newPosition) {

    }


    public void touchDown (Vector2 clickedPoint, int pointer, int button) {
        if (GameData.getInstance().getGameSpeed() != 0) {


            ControlPanelTower cpTower = controlPanel.getTowerOnPosition(clickedPoint);
            if (cpTower != null) {
                GameData.getInstance().getHand().setItem(cpTower.getTower());
            }

            //TODO: Cancel a spell or an item if already in hand.
            /*if (GameData.getInstance().getHand().getItem() != null) {
                GameData.getInstance().getHand().setItem(null);
                return;
            }
            */

            if (cpTower != null) {
                GameData.getInstance().getHand().setItem(cpTower.getTower());
            }
        }
        BoardObject touchedButton;

            //ButtonPanel
        if (!propertiesPanel.getIfVisible()) {
                touchedButton = buttonPanel.getButtonOnPosition(clickedPoint);
                if (touchedButton != null) {
                    GameData.getInstance().getHand().setItem(null);
                }
                if (touchedButton instanceof PlayButton) {
                    buttonPanel.pressedPlay();
                } else if (touchedButton instanceof PauseButton) {
                    buttonPanel.pressedPause();
                } else if (touchedButton instanceof SpeedButton) {
                    buttonPanel.pressedSpeedButton();
                } else if (touchedButton instanceof PropertiesButton) {
                    buttonPanel.pressedPropertiesButton();
                }
        }


            //PropertiesPanel

        if (propertiesPanel.getIfVisible()) {
                touchedButton = propertiesPanel.getButtonOnPosition(clickedPoint);
                if (touchedButton != null) {
                    GameData.getInstance().getHand().setItem(null);
                }
                if (touchedButton instanceof ResumeButton) {
                    propertiesPanel.pressedResumeButton();
                } else if (touchedButton instanceof MainMenuButton) {
                    propertiesPanel.pressedMainMenuButton();
                } else if (touchedButton instanceof SoundButton) {
                    propertiesPanel.pressedSoundButton();
                } else if (touchedButton instanceof SoundBar) {
                    int level = ((SoundBar) touchedButton).getLevel();
                    propertiesPanel.pressedBar(level);
                }
        }


    }
}
