package edu.chl.proximity.Models.ControlPanel;

/**
 * Created by simongislen on 28/04/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
        levelText = createFont(30, 30, "1");
    }

    public void updateExperience() {
        Player player = GameData.getInstance().getPlayer();
        int level = (int)player.getLevel();
        levelText.setText("" + player.getExperience() + ", " + level);

    }

    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new ProximityVector(getPosition().x + x, getPosition().y + y), s);
        //return new ProximityFont(new ProximityVector(width + x, y), s);
    }


    public void render(SpriteBatch batch) {
        super.render(batch);

        /*Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(0, 0, 1, 0.5f));
        shapeRenderer.circle(40, 40, 50);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 1, 1, 0.5f));
        shapeRenderer.circle(40, 40, 50);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 1, 0.5f));
        shapeRenderer.circle(45, 45, 40);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        */

        levelText.draw(batch);
    }
}
