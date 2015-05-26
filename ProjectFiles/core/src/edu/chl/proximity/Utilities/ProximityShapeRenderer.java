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

    /**
     * create a new shaperenderer that can draw mathematical shapes
     */
    public ProximityShapeRenderer() {
        if(!TestChecker.isJUnitTest()) {
            shapeRenderer = new ShapeRenderer();
            shapeRenderer.setAutoShapeType(true);
        }
        DisposableCollector.add(this);

    }

    /**
     * remove the shaperenderer from RAM (not handeled by GBC)
     */
    public void dispose() {
        shapeRenderer.dispose();
    }


    /**
     * what projection matrix should distort / adapt how the renderer draws locations?
     * @param projection what matrix should be loaded
     */
    public void setProjectionMatrix(Matrix4 projection) {
        shapeRenderer.setProjectionMatrix(projection);
    }

    public void begin() {
        shapeRenderer.begin();
    }

    /**
     * initiate the shaperenderer
     * Make sure the batch has ended before initiating this one
     * @param shape what shape type should be initiated
     */
    public void begin(Shape shape) {
        switch(shape) {
            case Filled: shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        }


    }

    /**
     * renders a rectangle
     * @param position where the upper left corner of the rectangle should be
     * @param width how wide the rectangle should be
     * @param height how high the rectangle should be
     * @param color what color the rectangle should be
     */
    public void renderRectangle(ProximityVector position, float width, float height, Color color){
        Gdx.gl.glEnable(GL20.GL_BLEND); //enables transparency
        shapeRenderer.setColor(color);
        shapeRenderer.rect(position.x,position.y,width,height);
    }

    public void end() {
        shapeRenderer.end();
    }

    public void setColor(Color color) {
        shapeRenderer.setColor(color);
    }

    /**
     * renders a range indicator, which is a semi transparent circle with a radious.
     * @param position where the circle center should be
     * @param range the radius of the circle
     * @param color the color of the circle
     */
    public void renderRangeIndicator(ProximityVector position, double range, Color color) {
        if(range<1500) {
            Gdx.gl.glEnable(GL20.GL_BLEND); //enables transparency
            shapeRenderer.setColor(color);
            shapeRenderer.circle(position.x, position.y, (float) range);
        }
    }

    /**
     * draw a line
     * @param x1 first point x coordinate
     * @param y1 first point y coordinate
     * @param x2 second point x coordinate
     * @param y2 second point y coordinate
     */
    public void renderLine(float x1, float y1, float x2, float y2) {
        shapeRenderer.line(x1, y1, x2, y2);
    }

    /**
     * draw a circle-like shape called an arc, which is can un-complete circle (think a clock)
     * @param x x coordinate of circle center
     * @param y y coordinate of circle center
     * @param radius radius of circle
     * @param start what angle the circle should start at
     * @param angle how many angles of the circle should be drawn out.
     */
    public void renderArc(float x, float y, float radius, float start, float angle) {
        shapeRenderer.arc(x, y, radius, start, angle);
    }
}
