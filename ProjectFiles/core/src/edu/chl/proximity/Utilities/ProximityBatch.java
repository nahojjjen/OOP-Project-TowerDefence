package edu.chl.proximity.Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;

/**
 * @author Linda Evaldsson
 * @date 2015-05-19
 *
 * A service for drawing images and fonts.
 */
public class ProximityBatch {


    private SpriteBatch batch = new SpriteBatch();

    public ProximityBatch() {

    }

    public void setProjectionMatrix(Matrix4 projection) {
        batch.setProjectionMatrix(projection);
    }

    public void begin() {
        batch.begin();
    }
    public void end() {
        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }

    public void render(Image image, ProximityVector p, double angle) {
        int rotatePointX = image.getTexture().getWidth() / 2;
        int rotatePointY = image.getTexture().getHeight() / 2;
        int textureWidth = image.getTexture().getWidth();
        int textureHeight = image.getTexture().getHeight();
        int realWidth = image.getTexture().getWidth();
        int realHeight = image.getTexture().getHeight();

        batch.draw(image.getTexture(), (int) p.x, (int) p.y, rotatePointX, rotatePointY, textureWidth, textureHeight,
                1, 1, (int) angle, 0, 0, realWidth, realHeight, false, false);
    }

    public void renderRepeatedly(Image image, ProximityVector p, int width, int height) {
        image.getTexture().setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch.draw(image.getTexture(), p.x, p.y, image.getTexture().getWidth(), image.getTexture().getHeight(), width, height);
    }

    public void render(BoardObject object) {
        render(object.getImage(), object.getPosition(), object.getAngle());
    }

    public void render(BitmapFont font, String str, ProximityVector position) {
        font.draw(batch, str, position.x, position.y);
    }
    public void render(ParticleEffectPool.PooledEffect effect, float time) {
        effect.draw(batch, time);
    }


}
