package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ProfilePanel;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 *
 * A class for testing the class ProfilePanel
 *
 */
public class ProfilePanelTest {

    @Test
    public void testUpdateExperience() throws Exception {
        GameData.getInstance().setPlayer(new Player(null));
        ProfilePanel profilePanel = new ProfilePanel();

        Player player = GameData.getInstance().getPlayer();
        int level = (int)player.getLevel();
        profilePanel.updateExperience();

        assertEquals("" + level, profilePanel.getLevelText().getText());

    }
}