package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.GameStates.MenuScreen;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.*;
import edu.chl.proximity.Models.ControlPanel.ButtonsPanel.*;
import edu.chl.proximity.Models.ControlPanel.TowerPanel.TowerPanel;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.ControlPanel.PropertiesPanel.*;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 *
 * 29/04 modified by Linda Evaldsson. Merged this class with PropertiesPanelController (created by Hanna Romer 2015-04-23) and ButtonPanelController (created by Hanna Romer 2015-04-23).
 * 29/04 modified by Hanna Romer. Added Play and Pause Buttons to possible clicked buttons on ButtonsPanel. Removed PausePlayButton option.
 * 30/04 modified by Simon Gislen. Added ProfilePanel.
 * 30/04 modified by Hanna Romer. Cannot click on anything else when propertiesPanel is open, cannot place towers when game is paused.
 * 03/05 modified by Simon Gislen. Towers are not free anymore.
 * 07/05 modofied by Linda Evaldsson. Added key bindings for spells. Also added handling for ControlPanels.
 * 08/05 modified by Hanna Römer. Added tower panel.
 * 13/05 Modified by Simon Gislen. Passes map to profile panel
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
    private Game game;

    public ControlPanelController(Map map, Game g, Viewport viewport) {
        this.viewport=viewport;
        this.map = map;
        this.game=g;

        propertiesPanel = new PropertiesPanel(viewport);
        controlPanel = new ControlPanel(map);
        towerPanel=new TowerPanel(map);
        //map.setPropertiesPanel(propertiesPanel);
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

    public boolean modelsClicked(ProximityVector clickedPoint) {
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

        spellPanel.updateCooldowns();

        profilePanel.updateExperience(map);
    }


    @Override
    public void mouseMoved(ProximityVector newPosition) {

    }

    public void keyDown(int keycode) {

        Input.Keys keys = new Input.Keys();

        //Spell shortcuts
        if(keycode == keys.Q) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("q"));//GameData.getInstance().getPlayer().getFaction().getSpell(0));
        }
        if(keycode == keys.W) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("w"));//(GameData.getInstance().getPlayer().getFaction().getSpell(1));
        }
        if(keycode == keys.E) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("e"));//(GameData.getInstance().getPlayer().getFaction().getSpell(2));
        }
        if(keycode == keys.R) {
            map.getHand().setItem(spellPanel.getSpellBoundTo("r"));//(GameData.getInstance().getPlayer().getFaction().getSpell(3));
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

        //Other shortcuts
        if(keycode == keys.ESCAPE) {
            map.getHand().setItem(null);
        }
        if(keycode == keys.SPACE) {
            GameData.getInstance().getPlayer().getSettings().togglePause();
        }
    }


    public void touchDown (ProximityVector clickedPoint, int pointer, int button) {

        if (GameData.getInstance().getPlayer().getSettings().getGameSpeed() != 0 && modelsClicked(clickedPoint)) {


            if(map.getHand().getItem() != null) {
                if(map.getChosenTower()==null) {
                    map.getHand().setItem(null);
                }
            }

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

        if (!propertiesPanel.getIfVisible() ) {
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

        if (propertiesPanel.getIfVisible()) {
                touchedButton = propertiesPanel.getButtonOnPosition(clickedPoint);
                if (touchedButton != null) {
                    map.getHand().setItem(null);
                }
                if (touchedButton instanceof ResumeButton) {
                    propertiesPanel.pressedResumeButton();
                } else if (touchedButton instanceof MainMenuButton) {
                    propertiesPanel.pressedMainMenuButton();
                    game.setScreen(new MenuScreen(game, GameData.getInstance().getPlayer(),viewport));
                } else if (touchedButton instanceof SoundButton) {
                    propertiesPanel.pressedSoundButton();
                } else if (touchedButton instanceof SoundBar) {
                    int level = ((SoundBar) touchedButton).getLevel();
                    propertiesPanel.pressedBar(level);
                }
        }


    }
}
