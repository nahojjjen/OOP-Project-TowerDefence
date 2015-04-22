package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;

/**
 * @author Johan Swanberg and Linda Evaldsson (group work)
 * @date 2015-04-02
 */
public abstract class BoardObject {
    /**
     * Position on the gameboard
     */
    private Vector2 position;
    /**
     * Image that is to be rendered
     */
    private Image image;
    /**
     * Rotation property
     */
    private double angle;

    private int width = 0;
    private int height = 0;


    /**
     * create a new board object
     * @param position where the object will be created
     * @param img the image of the object
     * @param angle the rotation of the object (in degrees)
     */
    public BoardObject(Vector2 position, Image img, double angle){
        this.position = position;
        this.image = img;
        this.angle = angle;
        if(img != null) {
            width = img.getTexture().getWidth();
            height = img.getTexture().getHeight();
        }
    }
    public BoardObject(Vector2 position, Image img, double angle, int width, int height) {
        this(position, img, angle);
        this.width = width;
        this.height = height;
    }


    //Getters and Setters
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image img) {
        this.image = img;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void rotate(double a) {
        angle += a;
    }
    public void render(SpriteBatch batch) {
        if(image != null) {
            image.render(batch, position, angle);
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * checks whether bullet has traveled outside the frame
     * x-y val
     *
     * @return true if not visible
     */
    public boolean isOutsideView() {
        if (getCenter().x < Gdx.graphics.getWidth()&& getCenter().y < Gdx.graphics.getHeight() && getCenter().x > 0 && getCenter().y > 0) {
            return false;
        }
        return true;
    }

    /**
     * re-adjusts the projectiles angle to face the given point
     *
     * @param v what point the projectile should travel towards
     */
    public void faceTarget(Vector2 v) {
        if (v != null) {
            angle = (PointCalculations.getVectorAngle(getCenter(), v));
            //angle remains unchanged, if there's no new point to look at.
        }
    }

    /**
     * Gets the center of this object
     * @return the vector in center of this BoardObject
     */
    public Vector2 getCenter() {
        return new Vector2(this.getPosition().x+(this.getWidth()/2), this.getPosition().y + (this.getHeight()/2));
    }

    public boolean containsPoint(Vector2 point) {
        return PointCalculations.isPointInObject(point, this);
    }
}
