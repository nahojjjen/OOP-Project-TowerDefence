package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.GameData;

/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 */
public class ControlPanelController {

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
    public void touchDown (int x, int y, int pointer, int button) {

    }
}
