package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Utilities.PointCalculations;

import java.awt.*;

/**
 * Created by Johan on 4/2/2015.
 */
public abstract class BoardObject {
    /**
     * Position on the gameboard
     */
    private Point position;
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
    public BoardObject(Point position, Image img, double angle){
        this.position = position;
        this.image = img;
        this.angle = angle;
    }


    //Getters and Setters
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
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
        if (getPosition().getX() < Gdx.graphics.getWidth()&& getPosition().getY() < Gdx.graphics.getHeight() && getPosition().getX() > 0 && getPosition().getY() > 0) {
            return false;

        }
        System.out.println("Board object found outside board, removing!");
        return true;
    }

    /**
     * re-adjusts the projectiles angle to face the given point
     *
     * @param p what point the projectile should travel towards
     */
    public void faceTarget(Point p) {
        if (p != null) {
            angle = (PointCalculations.getVectorAngle(getPosition(), p));
            //angle remains unchanged, if there's no new point to look at.
        }
    }
}
