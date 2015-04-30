package edu.chl.proximity.Models.ControlPanel;

/**
 * Created by simongislen on 28/04/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Players.Player;
import edu.chl.proximity.Models.ProximityFont;

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
        super(new Vector2(0, 20), null, 0, width, height);
        levelText = createFont(30, 30, "1");
    }

    public void updateExperience() {
        Player player = GameData.getInstance().getPlayer();
        int level = (int)player.getLevel();
        levelText.setText("" + player.getExperience() + ", " + player.getLevel());

    }

    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new Vector2(getPosition().x + x, y), s);
        //return new ProximityFont(new Vector2(width + x, y), s);
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
