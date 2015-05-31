package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Models.Utils.ProximityShapeRenderer;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Simon Gislen
 * @date 2015-04-28
 *
 * The class displaying the profile image, level and experience
 *
 * ---
 * 12/05 Modified by Simon Gislen. Added a more visual profile view
 * 22/05 Modified by Simon Gislen. Bug fix
 *
 */

public class ProfilePanel extends BoardObject {
    private ProximityFont levelText;

    //Width and heigh of the ControlPanel when it is initiated
    private static int width = 200;
    private static int height = 200;

    private ProximityShapeRenderer shapeRenderer = new ProximityShapeRenderer();

    public ProfilePanel() {
        super(new ProximityVector(0, Constants.GAME_HEIGHT - 75), null, 0, width, height);
        levelText = createFont(10, 54, "1");
    }

    public void updateExperience() {
        Player player = GameData.getInstance().getPlayer();
        int level = (int)player.getLevel();
        levelText.setText("" + level);
    }

    /**
     * For JUnit-testing. Gets the level font
     * @return the level font
     */
    public ProximityFont getLevelText() {
        return levelText;
    }

    /**
     * Creates a font placed relative to this ProfilePanels top left corner
     * @param x the x position on the ProfilePanel
     * @param y the y position on the ProfilePanel
     * @param s the String that the font should display
     * @return The created font
     */
    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new ProximityVector(getPosition().x + x, getPosition().y + y), s,14, 1,1,1);
    }

    public void render(ProximityBatch batch) {
        super.render(batch);

        levelText.draw(batch);
        batch.end();
        Player player = GameData.getInstance().getPlayer();
        double temp = player.getLevel();
        double anglePercentage = (temp-(int)(temp)) * 100;
        double angle = (anglePercentage)/10 * 9;

        shapeRenderer.begin(ProximityShapeRenderer.Shape.Filled);
        shapeRenderer.setColor(new Color(0.95f, 0.95f, 0.95f, 1f));
        shapeRenderer.renderArc(0, 0, 145, 0, (float) angle);
        shapeRenderer.setColor(new Color(0.2f, 0.3f, 0.50f, 1f));
        shapeRenderer.renderArc(0, 0, 140, 0, 90);


        shapeRenderer.end();
        batch.begin();
        Image image = player.getFaction().getShowImage();
        batch.render(image, new ProximityVector(10, Constants.GAME_HEIGHT - 105), 180);
        batch.end();

        shapeRenderer.end();

        batch.begin();

        levelText.draw(batch);

    }
}
