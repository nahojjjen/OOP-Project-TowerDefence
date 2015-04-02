package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import edu.chl.proximity.Models.BoardObject;

/**
 * Created by Hanna Römer on 2015-04-02.
 */
public abstract class AbstractProjectile extends BoardObject{
    private ParticleEffect effect;
    private int health;
    private int speed;
    private Sound sound;

    public AbstractProjectile(ParticleEffect particleEffect, int health, int speed, Sound sound){
        this.effect=particleEffect;
        this.health=health;
        this.speed=speed;
        this.sound=sound;
    }

    /**
     * get the current coordinates for this projectile
     *
     * @return Point with the projectiles position
     */
    public Point getPoint() {
        return position;
    }

    /**
     * Get whether the projectile point intersects another point
     *
     * @param creeppos what other position should be compared
     * @param hitbox how bix the hitbox is (10 wide hitbox would be 5, because)
     * the 5 goes in both directions.
     * @return true if the projectile intersects the given area
     */
    public boolean collidesWith(Point creeppos, int hitbox) {
        if (creeppos.getY() - hitbox < position.getY() && position.getY() < creeppos.getY() + hitbox) {
            if (creeppos.getX() - hitbox < position.getX() && position.getX() < creeppos.getX() + hitbox) {
                return true;
            }

        }
        return false;
    }

    /**
     * move the projectile
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPosition(int x, int y) {
        Point point = new Point(x, y);
        position = point;
    }

    /**
     * change the andle of the projectile
     *
     * @param angle what angle the projectile should have
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Moves the projectile a step forward based on the current angle and speed
     */
    public void move() {
        Point newPosition;
        int xLenght = (int) (Math.cos(Math.toRadians(angle)) * speed);
        int yLenght = (int) (Math.sin(Math.toRadians(angle)) * speed);

        newPosition = new Point(position.getX() + xLenght, position.getY() + yLenght);
        position = newPosition;
    }

    /**
     * re-adjusts the projectiles angle to face the given point
     *
     * @param p what point the projectile should travel towards
     * @throws SlickException
     */
    public void faceTarget(Point p) throws SlickException {
        if (p != null) {
            angle = Util.getVectorAngle(position, p);
            getImage().setRotation((float) (angle + 90)); //+90 degrees because the source image is rotated
        }else{
            //angle remains unchanged, if there's no new point to look at.
        }
    }

    /**
     * /TODO make destroy summon particle effects of destruciton
     */
    public void destroy() {
        System.out.println("destroyed projectile?");
    }

    /**
     * get the image of the projectile (singleton)
     *
     * @return the Image of the projectile
     */
    public Image getImage() {
        try {
            if (projectileImage == null) {
                projectileImage = new Image("src/Data/tower1/level1bullet.png");
            }
            return projectileImage;
        } catch (SlickException e) {
            System.out.println("slickexception in base");
        }
        return null;
    }

    /**
     * checks whether bullet has traveled outside the frame //TODO fix static
     * x-y val
     *
     * @return true if not visible
     */
    public boolean isOutsideView() {
        if (position.getX() < 1800 && position.getY() < 800 && position.getX() > 0 && position.getY() > 0) {
            return false;

        }
        return true;
    }

}
