package edu.chl.proximity.Models.ControlPanel;

/**
 * Created by simongislen on 28/04/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.ProximityFont;

/**
 * @author Simon Gislen
 * @date 2015-04-28
 *
 * The class displaying the profile image, level and experience
 *
 * 12/05 Modified by Simon Gislen. Added a more visual profile view
 *
 */

public class ProfilePanel extends BoardObject {
    private ProximityFont levelText;
    private int levelXP;

    //Width and heigh of the ControlPanel when it is initiated
    private static int width = 200;
    private static int height = 200;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public ProfilePanel() {
        super(new ProximityVector(0, Gdx.graphics.getHeight() - 75), null, 0, width, height);
        levelText = createFont(10, 52, "1");
    }

    public void updateExperience(Map map) {
        Player player = GameData.getInstance().getPlayer();
        int level = (int)player.getLevel();
        levelText.setText("" + level);


    }

    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new ProximityVector(getPosition().x + x, getPosition().y + y), s);
        //return new ProximityFont(new ProximityVector(width + x, y), s);
    }

    public void render(SpriteBatch batch) {
        super.render(batch);

        levelText.draw(batch);
        batch.end();
        Player player = GameData.getInstance().getPlayer();
        double temp = player.getLevel();
        double anglePercentage = temp-(int)(temp);
        double angle = 360 * anglePercentage;
        Gdx.gl.glEnable(GL20.GL_BLEND);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0.05f, 0.05f, 0.05f, 0.95f));
        shapeRenderer.rect(0, 0, 100, 100);

        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0.2f, 0.3f, 0.50f, 0.9f));
        shapeRenderer.arc(50, 50, 45, 0, (float) angle);

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(0.95f, 0.95f, 0.95f, 0.95f));
        shapeRenderer.arc(50, 50, 46, 0, (float) angle);
        shapeRenderer.end();
        batch.begin();
        Image image = player.getFaction().getShowImage();
        image.render(batch, new ProximityVector(10, Gdx.graphics.getHeight() - 100), 180);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0.05f, 0.05f, 0.05f, 0.95f));
        shapeRenderer.rect(0, 0, 30, 30);

        shapeRenderer.end();

        batch.begin();

        levelText.draw(batch);

    }
}
