package edu.chl.proximity.Models.Particles;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson (Group work)
 * @date 2015-04-11
 *
 * A class that keeps track of all effects that can be displayed, and has the ability to render them
 */
public class ParticleManager {

    List<ProximityEffect> allEffects = new ArrayList();

    /**
     * create all the particle pools (currently only explosion)
     */
    public ParticleManager() {
        allEffects.add(new ProximityEffect("explosion2", 100));
        allEffects.add(new ProximityEffect("creepdies", 1000));
        allEffects.add(new ProximityEffect("bulleteffect", 100));
        allEffects.add(new ProximityEffect("baseDamage", 15));
        allEffects.add(new ProximityEffect("baseGlitter", 1));
        allEffects.add(new ProximityEffect("frostBlast", 10));
    }

    /**
     * render all active effects that the particleManager keeps track of
     * @param batch what batch should be used to render the effects
     */
    public void renderAllParticles(SpriteBatch batch){
        for(ProximityEffect effect:allEffects){
            effect.renderAllActiveEffects(batch);
        }
    }

    public ProximityEffect getExplosionEffect() {return allEffects.get(0);}
    public ProximityEffect getCreepDiesEffect() {return allEffects.get(1);}
    public ProximityEffect getBulletEffect() {return allEffects.get(2);}
    public ProximityEffect getBaseDamageEffect() {return allEffects.get(3);}
    public ProximityEffect getBaseCracksEffect() {return allEffects.get(4);}
    public ProximityEffect getFrostBlastEffect() {return allEffects.get(5);}

}



