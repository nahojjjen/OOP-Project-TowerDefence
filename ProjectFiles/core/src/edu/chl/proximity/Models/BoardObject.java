package edu.chl.proximity.Models;

/**
 * Created by Johan on 4/2/2015.
 */
public abstract class BoardObject {
    /**
     * Position on the gameboard
     */
    private int position;
    /**
     * Image that is to be rendered
     */
    private int image;
    /**
     * Rotation property
     */
    private int angle;

    //Getters and Setters
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
