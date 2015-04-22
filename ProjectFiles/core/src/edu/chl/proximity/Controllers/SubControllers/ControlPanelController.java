package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.GameData;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 */
public class ControlPanelController implements ClickHandler {

    private ControlPanel controlPanel;

    public ControlPanelController() {
        controlPanel = new ControlPanel();
    }
    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void update() {
        controlPanel.setHealth(GameData.getInstance().getMap().getBase().getLife());
        controlPanel.setResources(GameData.getInstance().getPlayer().getResources());

    }

    public BoardObject getModel() {
        return controlPanel;
    }


    public void touchDown (Vector2 clickedPoint, int pointer, int button) {
        System.out.println("MainController: ControlPanel is clicked");
        ControlPanelTower cpTower = controlPanel.getTowerOnPosition(clickedPoint);
        if(cpTower != null) {
            System.out.println("MainController: Clicked on Tower");
            cpTower.getTower();
        }

    }
}
