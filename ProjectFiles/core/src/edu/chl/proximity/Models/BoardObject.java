package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-02
 *
 * An object that can be placed on the board.
 *
 * ---
 * 02/04 modified by Simon Gislén. Added properties.
 * 08/04 modified by Linda Evaldsson. Getters for width and height created.
 * 23/04 modified Simon Gislén. Added empty constructor.
>*/
public abstract class BoardObject implements Cloneable {
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
    public BoardObject() {
        //Empty constructor for subclasses
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

    public void setCenter(Vector2 pos) {
        setPosition(new Vector2(pos.x - this.getWidth()/2, pos.y - this.getHeight()/2));
    }

    public boolean containsPoint(Vector2 point) {
        return PointCalculations.isPointInObject(point, this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        BoardObject clone = (BoardObject)super.clone();
        clone.setPosition(new Vector2(position.x, position.y));
        clone.setImage((Image)image.clone());
        return clone;
    }
}
