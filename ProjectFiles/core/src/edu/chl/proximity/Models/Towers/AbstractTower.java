package edu.chl.proximity.Models.Towers;

import edu.chl.proximity.Models.BoardObject;

/**
 * Created by Johan on 4/2/2015.
 */
public class AbstractTower extends BoardObject {

    private Point position;
    private double angle;
    private int bulletSpeed = 10;
    private int reloadTime = 0;
    private Image firstImage;
    private Image secondImage;

    /**
     * create a shooting tower with a position
     * @param pos the center of the tower; where the tower should be created
     * @throws SlickException
     */
    public MissileTower(Point pos) throws SlickException{
        position = pos;
        angle =0;
        firstImage = new Image("src/Data/tower1/level1.png");
        secondImage = new Image("src/Data/tower1/level1shooting.png");
    }

    /**
     * Rotate the images related to this tower towards the point
     * @param p where the tower should be facing
     * @throws SlickException
     */
    @Override
    public void faceTarget(Point p) throws SlickException{
        if (p != null){
            angle = Util.getVectorAngle(position, p)+90;
            getImage().setRotation((float) angle);
            getImage2().setRotation((float) angle);
        }
    }

    /**
     * create a projectile at the towers location, if the tower can shoot
     * if the tower shoots, start the reload time
     */
    @Override
    public void shoot(){
        if(reloadTime < 1){
            Controller.addProjectile(new Projectile(position, bulletSpeed, angle));
            reloadTime = 100;
        }

    }

    /**
     * get the position of the tower
     * @return  Point with tower coordinates (upper left corner)
     */
    @Override
    public Point getPoint(){
        return position;
    }
    /**
     * get the normal image of this tower
     * @return the image corresponding to this tower
     * @throws SlickException
     */
    @Override
    public  Image getImage() throws SlickException{
        return firstImage;
    }

    /**
     * get the fireing image of this tower
     *
     * @returnthe image corresponding to this tower fiering
     * @throws SlickException
     */
    @Override
    public  Image getImage2() throws SlickException{
        return secondImage;
    }

    /**
     * decrease the reload time, tower can shoot when reload is at 0
     */
    @Override
    public void reload(){
        if(reloadTime > 0){
            reloadTime --;
        }
    }
    /**
     * get the animation (combination of the two other images)
     * related to this tower
     * @return Animation including the two images
     * @throws SlickException
     */
    @Override
    public  Animation getAnimation() throws SlickException{
        Image[] animationframes1 = {firstImage,secondImage};
        Animation tower1 = new Animation(animationframes1, 1000);
        return tower1;
    }
}
