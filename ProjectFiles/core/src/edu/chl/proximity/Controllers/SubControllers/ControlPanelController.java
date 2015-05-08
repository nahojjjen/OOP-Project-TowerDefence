package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.*;
import edu.chl.proximity.Models.ControlPanel.ButtonsPanel.*;
import edu.chl.proximity.Models.ControlPanel.TowerPanel.CheckBox;
import edu.chl.proximity.Models.ControlPanel.TowerPanel.TowerPanel;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.*;
import edu.chl.proximity.Proximity;

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
 * 07/05 modofied by Linda Evaldsson. Added key bindings for spells. Also added handling for ControlPanels.
 * 08/05 modified by Hanna Römer. Added tower panel.
 */
public class ControlPanelController implements ClickHandler {

    private ControlPanel controlPanel;
    private ButtonPanel buttonPanel;
    private SpellPanel spellPanel;
    private PropertiesPanel propertiesPanel;
    private ProfilePanel profilePanel;
    private TowerPanel towerPanel;
    private Map map;
    private Viewport viewport;
    private List<BoardObject> controlPanels = new ArrayList<BoardObject>();

    public ControlPanelController(Map map, Proximity g, Viewport viewport) {
        this.viewport=viewport;
        this.map = map;

        propertiesPanel = new PropertiesPanel(g,viewport);
        controlPanel = new ControlPanel(map);
        towerPanel=new TowerPanel(map);
        map.setPropertiesPanel(propertiesPanel);
        buttonPanel = new ButtonPanel(propertiesPanel);
        profilePanel = new ProfilePanel();
        spellPanel = new SpellPanel(GameData.getInstance().getPlayer().getFaction());

        controlPanels.add(propertiesPanel);
        controlPanels.add(controlPanel);
        controlPanels.add(buttonPanel);
        controlPanels.add(profilePanel);
        controlPanels.add(spellPanel);
        controlPanels.add(towerPanel);
    }

    public boolean modelsClicked(Vector2 clickedPoint) {
        for(BoardObject cp : controlPanels) {
            if(cp.containsPoint(clickedPoint)) {
                return true;
            }
        }
        return false;
    }


    public List<BoardObject> getControlPanels() { return controlPanels;}
    //public void setButtonPanel(ButtonPanel buttonPanel) { this.buttonPanel = buttonPanel;}
    //public void setProfilePanel(ProfilePanel profilePanel) { this.profilePanel = profilePanel;}
    //public void setSpellPanel(SpellPanel spellPanel){
     //   this.spellPanel = spellPanel;
    //}

    public void update() {
        controlPanel.setHealth(map.getBase().getLife());
        controlPanel.setResources(GameData.getInstance().getPlayer().getResources());
        profilePanel.updateExperience();
    }


    @Override
    public void mouseMoved(Vector2 newPosition) {

    }

    public void keyDown(int keycode) {

        Input.Keys keys = new Input.Keys();
        if(keycode == keys.Q) {
            map.getHand().setItem(GameData.getInstance().getPlayer().getFaction().getSpell(0));
        }
        if(keycode == keys.W) {
            map.getHand().setItem(GameData.getInstance().getPlayer().getFaction().getSpell(1));
        }
        if(keycode == keys.E) {
            map.getHand().setItem(GameData.getInstance().getPlayer().getFaction().getSpell(2));
        }
        if(keycode == keys.R) {
            map.getHand().setItem(GameData.getInstance().getPlayer().getFaction().getSpell(3));
        }
    }


    public void touchDown (Vector2 clickedPoint, int pointer, int button) {

        if (GameData.getInstance().getPlayer().getSettings().getGameSpeed() != 0 && modelsClicked(clickedPoint)) {

            if(map.getHand().getItem() != null)
                map.getHand().setItem(null);

            ControlPanelTower cpTower = controlPanel.getTowerOnPosition(clickedPoint);
            if (cpTower != null) {
                map.getHand().setItem(cpTower.getTower());
            }
            ControlPanelSpell cpSpell = spellPanel.getSpellOnPosition(clickedPoint);
            if (cpSpell != null) {
                map.getHand().setItem(cpSpell.getSpell());
            }

        }
        BoardObject touchedButton;

        if (!map.getPropertiesPanel().getIfVisible() ) {
            //ButtonPanel
            touchedButton = buttonPanel.getButtonOnPosition(clickedPoint);
            if (touchedButton != null) {
                map.getHand().setItem(null);
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
            //TowerPanel
            touchedButton=towerPanel.getButtonOnPosition(clickedPoint);


        }


            //PropertiesPanel

        if (map.getPropertiesPanel().getIfVisible()) {
                touchedButton = map.getPropertiesPanel().getButtonOnPosition(clickedPoint);
                if (touchedButton != null) {
                    map.getHand().setItem(null);
                }
                if (touchedButton instanceof ResumeButton) {
                    map.getPropertiesPanel().pressedResumeButton();
                } else if (touchedButton instanceof MainMenuButton) {
                    map.getPropertiesPanel().pressedMainMenuButton();
                } else if (touchedButton instanceof SoundButton) {
                    map.getPropertiesPanel().pressedSoundButton();
                } else if (touchedButton instanceof SoundBar) {
                    int level = ((SoundBar) touchedButton).getLevel();
                    map.getPropertiesPanel().pressedBar(level);
                }
        }


    }
}
