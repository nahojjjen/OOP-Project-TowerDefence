package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ButtonsPanel.*;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.PropertiesPanel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 *
 * 29/04 modified by Linda Evaldsson. Merged this class with PropertiesPanelController (created by Hanna Römer 2015-04-23) and ButtonPanelController (created by Hanna Römer 2015-04-23).
 */
public class ControlPanelController implements ClickHandler {

    private ControlPanel controlPanel = new ControlPanel();
    private ButtonPanel buttonPanel = new ButtonPanel();
    private PropertiesPanel propertiesPanel=new PropertiesPanel();

    private List<BoardObject> models = new ArrayList<BoardObject>();

    public ControlPanelController() {
        models.add(controlPanel);
        models.add(buttonPanel);
        models.add(propertiesPanel);
    }

    public void setControlPanel(ControlPanel controlPanel) { this.controlPanel = controlPanel;}
    public void setPropertiesPanel(PropertiesPanel propertiesPanel){ this.propertiesPanel=propertiesPanel;}
    public void setButtonPanel(ButtonPanel buttonPanel) { this.buttonPanel = buttonPanel;}

    public void update() {
        controlPanel.setHealth(GameData.getInstance().getMap().getBase().getLife());
        controlPanel.setResources(GameData.getInstance().getPlayer().getResources());

    }

    public boolean isModelClicked(Vector2 clickedPoint) {

        for(BoardObject model : models) {
            if(model.containsPoint(clickedPoint))
                return true;
        }
        return false;
    }

    public List<BoardObject> getModels() {
        return models;
    }

    @Override
    public void mouseMoved(Vector2 newPosition) {

    }


    public void touchDown (Vector2 clickedPoint, int pointer, int button) {

        ControlPanelTower cpTower = controlPanel.getTowerOnPosition(clickedPoint);
        if(cpTower != null) {
            GameData.getInstance().getHand().setItem(cpTower.getTower());
        }
        BoardObject touchedButton;

        //ButtonPanel
        touchedButton=buttonPanel.getButtonOnPosition(clickedPoint);
        if(touchedButton instanceof PlayButton) {
            buttonPanel.pressedPlay();
        }else if(touchedButton instanceof PauseButton){
            buttonPanel.pressedPause();
        }else if(touchedButton instanceof SpeedButton){
            buttonPanel.pressedSpeedButton();
        }else if(touchedButton instanceof PropertiesButton){
            buttonPanel.pressedPropertiesButton();
        }

        //PropertiesPanel

        if (propertiesPanel.getIfVisible()) {
            touchedButton = propertiesPanel.getButtonOnPosition(clickedPoint);
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
