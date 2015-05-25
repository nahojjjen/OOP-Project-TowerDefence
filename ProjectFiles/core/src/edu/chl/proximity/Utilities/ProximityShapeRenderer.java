package edu.chl.proximity.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import edu.chl.proximity.Models.Utils.DisposableCollector;
import edu.chl.proximity.Models.Utils.ProximityDisposable;

/**
 * @author Linda Evaldsson
 * @date 2015-05-19
 *
 * A service for drawing shapes.
 */
public class ProximityShapeRenderer implements ProximityDisposable {

    public enum Shape {
        Filled
    }

    ShapeRenderer shapeRenderer;

    public ProximityShapeRenderer() {
        if(!TestChecker.isJUnitTest()) {
            shapeRenderer = new ShapeRenderer();
            shapeRenderer.setAutoShapeType(true);
        }
        DisposableCollector.add(this);

    }

    public void dispose() {
        shapeRenderer.dispose();
    }


    public void setProjectionMatrix(Matrix4 projection) {
        shapeRenderer.setProjectionMatrix(projection);
    }

    public void begin() {
        shapeRenderer.begin();
    }
    public void begin(Shape shape) {
        switch(shape) {
            case Filled: shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        }


    }
    public void end() {
        shapeRenderer.end();
    }

    public void setColor(Color color) {
        shapeRenderer.setColor(color);
    }

    public void renderRangeIndicator(ProximityVector position, double range, Color color) {
        if(range<1500) {
            Gdx.gl.glEnable(GL20.GL_BLEND); //enables transparency
            shapeRenderer.setColor(color);
            shapeRenderer.circle(position.x, position.y, (float) range);
        }
    }

    public void renderLine(float x1, float y1, float x2, float y2) {
        shapeRenderer.line(x1, y1, x2, y2);
    }

    public void renderArc(float x, float y, float radius, float start, float angle) {
        shapeRenderer.arc(x, y, radius, start, angle);
    }
}
