package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Input;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.*;
import edu.chl.proximity.Models.ControlPanel.ButtonsPanel.*;
import edu.chl.proximity.Models.ControlPanel.TowerPanel.TowerPanel;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.*;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Models.Utils.MouseOverBox;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 *
 * A controller that controls the ControlPanels in the game
 *
 * 29/04 modified by Linda Evaldsson. Merged this class with PropertiesPanelController (created by Hanna Romer 2015-04-23) and ButtonPanelController (created by Hanna Romer 2015-04-23).
 * 29/04 modified by Hanna Romer. Added Play and Pause Buttons to possible clicked buttons on ButtonsPanel. Removed PausePlayButton option.
 * 30/04 modified by Simon Gislen. Added ProfilePanel.
 * 30/04 modified by Hanna Romer. Cannot click on anything else when propertiesPanel is open, cannot place towers when game is paused.
 * 03/05 modified by Simon Gislen. Towers are not free anymore.
 * 07/05 modofied by Linda Evaldsson. Added key bindings for spells. Also added handling for ControlPanels.
 * 08/05 modified by Hanna Römer. Added tower panel.
 * 13/05 Modified by Simon Gislen. Passes map to profile panel
 * 22/05 modified by Linda Evaldsson. Restructured how the controller handles clicks to make it easier to understand.
 */
public class ControlPanelController implements ClickHandler, UpdateHandler {

    private ControlPanel controlPanel;
    private ButtonPanel buttonPanel;
    private SpellPanel spellPanel;
    private PropertiesPanel propertiesPanel;
    private ProfilePanel profilePanel;
    private TowerPanel towerPanel;

    private Map map;
    private List<BoardObject> controlPanels = new ArrayList<BoardObject>();

    public ControlPanelController(Map map) {
        this.map = map;

        propertiesPanel = new PropertiesPanel(GameData.getInstance().getPlayer().getSettings());
        controlPanel = new ControlPanel(map);
        towerPanel=new TowerPanel(map);
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

    /**
     * Returns whether the controlPanels that this controller controll has been clicked
     * @param clickedPoint the point that has been clicked
     * @return whether the clickedPoint is within one or more of the controlPanels
     */
    public boolean modelsClicked(ProximityVector clickedPoint) {
        for(BoardObject cp : controlPanels) {
            if(cp.containsPoint(clickedPoint)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the controlpanels that this controller updates
     * @return the controlpanels that this controllers updates
     */
    public List<BoardObject> getControlPanels() { return controlPanels;}

    public void update() {
        controlPanel.setHealth(map.getBase().getLife());
        controlPanel.setResources(GameData.getInstance().getPlayer().getResources());

        spellPanel.updateCooldowns();

        profilePanel.updateExperience();
    }


    @Override
    public void mouseMoved(ProximityVector newPosition) {
        if(spellPanel.containsPoint(newPosition)) {
            spellPanel.mouseOverPosition(newPosition);
        }
     }

    public void keyDown(int keycode) {

        Input.Keys keys = new Input.Keys();

        //Spell shortcuts
        if(keycode == keys.Q) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("q"));
        }
        if(keycode == keys.W) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("w"));
        }
        if(keycode == keys.E) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("e"));
        }
        if(keycode == keys.R) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("r"));
        }

        //Tower shortcuts
        if(keycode == keys.NUM_1) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(1));
        }
        if(keycode == keys.NUM_2) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(2));
        }
        if(keycode == keys.NUM_3) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(3));
        }
        if(keycode == keys.NUM_4) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(4));
        }
        if(keycode == keys.NUM_5) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(5));
        }
        if(keycode == keys.NUM_6) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(6));
        }
        if(keycode == keys.NUM_7) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(7));
        }
        if(keycode == keys.NUM_8) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(8));
        }
        if(keycode == keys.NUM_9) {
            map.getHand().setItem(controlPanel.getTowerBoundTo(9));
        }

        //Other shortcuts
        if(keycode == keys.ESCAPE) {
            map.getHand().setItem(null);
        }
        if(keycode == keys.SPACE) {
            GameData.getInstance().getPlayer().getSettings().togglePause();
        }
        if(keycode == keys.F) {
            towerPanel.pressedPosition(towerPanel.getUpgradeCenter());
        }
    }

    @Override
    public void touchDown (ProximityVector clickedPoint, int pointer, int button) {

        //Not handling clicks if game is paused
        if (GameData.getInstance().getPlayer().getSettings().getGameSpeed() != 0) {

            if(controlPanel.containsPoint(clickedPoint)) {
                ControlPanelTower cpTower = controlPanel.getTowerOnPosition(clickedPoint);

                if (cpTower != null) {
                    map.getHand().setItem(cpTower.getTower());
                }
                else if (!towerPanel.containsPoint(clickedPoint)){
                    map.getHand().setItem(null);
                }
            }

            if(spellPanel.containsPoint(clickedPoint)) {
                ControlPanelSpell cpSpell = spellPanel.getSpellOnPosition(clickedPoint);

                if (cpSpell != null) {
                    map.getHand().setItem(cpSpell.getSpell());
                } else {
                    map.getHand().setItem(null);
                }
            }

            if(towerPanel.containsPoint(clickedPoint)) {
                towerPanel.pressedPosition(clickedPoint);
            }

        } //End checking if game is paused


        if (buttonPanel.containsPoint(clickedPoint) && !propertiesPanel.isVisible()) {
            BoardObject touchedButton;
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


        }
        if (propertiesPanel.containsPoint(clickedPoint)) {
            BoardObject touchedButton;
            touchedButton = propertiesPanel.getButtonOnPosition(clickedPoint);
            propertiesPanel.pressButton(touchedButton);

            if (touchedButton == propertiesPanel.getMainMenuButton())
                ScreenChanger.changeScreen(ScreenChanger.ScreenType.MainMenu);

        }

    }
}
