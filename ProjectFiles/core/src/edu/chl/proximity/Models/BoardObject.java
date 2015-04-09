package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;

/**
 * Created by Johan on 4/2/2015.
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

    public void setTexture(Image img) {
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

        image.render(batch, position, angle);
    }

    public int getWidth() {
        return image.getTexture().getWidth();
    }
    public int getHeight() {
        return image.getTexture().getHeight();
    }

    /**
     * checks whether bullet has traveled outside the frame //TODO fix static
     * x-y val
     *
     * @return true if not visible
     */
    public boolean isOutsideView() {
        if (getPosition().x < Gdx.graphics.getWidth()&& getPosition().y < Gdx.graphics.getHeight() && getPosition().x > 0 && getPosition().y > 0) {
            return false;

        }
        System.out.println("Board object found outside board, removing!");
        return true;
    }

    /**
     * re-adjusts the projectiles angle to face the given point
     *
     * @param v what point the projectile should travel towards
     */
    public void faceTarget(Vector2 v) {
        if (v != null) {
            angle = (PointCalculations.getVectorAngle(getPosition(), v));
            //angle remains unchanged, if there's no new point to look at.
        }
    }
}
