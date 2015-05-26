package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Particles.ProximityEffect;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 * A model of the base on the map
 */
public abstract class Base extends BoardObject{

    private int life = 100; //Base hp
    private ProximityEffect damageEffect; //effect to show when base takes damage
    private ProximityEffect cracksEffect; //The effect to show passivly that intensifies when base is damaged

    public Base(Path path, Image image, ProximityEffect damageEffect, ProximityEffect cracksEffect){
        super(null, image, 180);

        this.cracksEffect = cracksEffect;

        this.damageEffect = damageEffect;
        ProximityVector basePosition = new ProximityVector(path.getWaypoint(path.getWaypoints().size()-1));
        basePosition.add(-1*getWidth()/2f, -1* getHeight()/2f); //position the base centered at last waypoints
        setPosition(basePosition);
    }



    /**
     * get how much life the base has left
     * @return (int) how much life the base has
     */
    public int getLife(){
        return life;
    }

    /**
     * set the life of the base
     * @param input
     */
    public void setLife(int input){
        life = input;
    }

    /**
     * Deal some damage to the base, automatically shows the "hurt" effect if health gets below 20.
     * @param amount how much damage the base should take
     */
    public void damage(int amount){
        life -= amount;
        if (damageEffect != null){
            damageEffect.createEffect(this.getCenter().x, this.getCenter().y);
        }

        if(life < 20 && life >= 0&&cracksEffect != null  ){
            cracksEffect.createEffect(this.getCenter().x,this.getCenter().y);
        }
    }

    /**
     * deals one damage to the base
     */
    public void damage(){
        damage(1);
    }

    /**
     * Increases the bases health with the specified amount.
     * @param healAmount how much the base should be healed
     */
    public void heal(int healAmount){
        this.life += healAmount;
    }

    /**
     * show the damage effect connected to this base at the base location
     */
    public void showDamageEffect(){
        damageEffect.createEffect(this.getCenter().x, this.getCenter().y);
    }

    /**
     * set what effect the base shows when it takes damage
     * @param effect
     */
    public void setDamageEffect(ProximityEffect effect){
        damageEffect = effect;
    }

    /**
     * set what effect the base shows passivly on low health
     * @param effect
     */
    public void setCracksEffect(ProximityEffect effect){
        cracksEffect = effect;
    }
}
