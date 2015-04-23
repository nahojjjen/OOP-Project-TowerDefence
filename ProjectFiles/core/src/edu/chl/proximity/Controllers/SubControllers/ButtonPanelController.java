package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ButtonsPanel.ButtonPanel;
import edu.chl.proximity.Models.ButtonsPanel.PlayPauseButton;
import edu.chl.proximity.Models.ButtonsPanel.PropertiesButton;
import edu.chl.proximity.Models.ButtonsPanel.SpeedButton;
import edu.chl.proximity.Models.GameData;

/**
 * @author Hanna Römer
 * @date 2015-04-23.
 */
public class ButtonPanelController implements ClickHandler{
    private ButtonPanel buttonPanel;
    private PropertiesPanelController ppController;

    public ButtonPanelController(){
        buttonPanel = new ButtonPanel();
    }

    public void setButtonPanel(ButtonPanel buttonPanel) { this.buttonPanel = buttonPanel;}

    public void setPpropertiesPanelController(PropertiesPanelController propertiesPanelController){
        this.ppController=propertiesPanelController;
    }

    public void update(){};

    @Override
    public BoardObject getModel() {
        return buttonPanel;
    }

    @Override
    public void mouseMoved(Vector2 newPosition) {

    }

    @Override
    public void touchDown(Vector2 clickedPoint, int pointer, int button) {
        BoardObject touchedButton=buttonPanel.getButtonOnPosition(clickedPoint);
        if(touchedButton instanceof PlayPauseButton){
            buttonPanel.pressedPausePlay();
        }else if(touchedButton instanceof SpeedButton){
            buttonPanel.pressedSpeedButton();
        }else if(touchedButton instanceof PropertiesButton){
            buttonPanel.pauseGame();
            ppController.show();
        }

    }
}
