package edu.chl.proximity.Models.Bases;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Particles.ProximityEffect;
import edu.chl.proximity.Models.Paths.Path;

/**
 * @author Johan on 2015-04-16.
 */
public abstract class Base extends BoardObject{

    private int life = 100; //Base hp
    private ProximityEffect damageEffect; //effect to show when base takes damage
    private ProximityEffect cracksEffect; //The effect to show passivly that intensifies when base is damaged

    public Base( Image image, ProximityEffect damageEffect, ProximityEffect cracksEffect){
        super(null, image, 0);

        this.cracksEffect = cracksEffect;

        this.damageEffect = damageEffect;
        Path path = GameData.getInstance().getMap().getPath();
        Vector2 basePosition = (path.getWaypoint(path.getWaypoints().size()-1));
        basePosition.add(-1*getWidth()/2, -1* getHeight()/2); //position the base centered at last waypoint
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

        if(life < 20 && cracksEffect != null){
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
        damageEffect.createEffect(this.getPosition().x, this.getPosition().y);
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
