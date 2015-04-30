package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ProfilePanel;


/**
 * @author Simon Gislen
 * @date 28-04-2015
 * Controller for the ProfileView in the bottom left corner
 */
public class ProfileViewController implements ClickHandler {

    private ProfilePanel profilePanel;
    @Override
    public void touchDown(Vector2 clickedPoint, int pointer, int button) {

    }

    @Override
    public BoardObject getModel() {
        return profilePanel;
    }

    @Override
    public void mouseMoved(Vector2 newPosition) {

    }

    public void setProfilePanel(ProfilePanel profilePanel) {
        this.profilePanel = profilePanel;
    }

    public void update() {
        profilePanel.updateExperience();
    }
}
