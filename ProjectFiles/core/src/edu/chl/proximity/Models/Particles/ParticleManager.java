package edu.chl.proximity.Models.Particles;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group programing with Linda.
 * A class that keeps track of all effects that can be displayed, and has the ability to render them
 */
public class ParticleManager {
    private ProximityEffect explosionEffect;
    private ProximityEffect creepDiesEffect;

    List<ProximityEffect> allEffects = new ArrayList();

    /**
     * create all the particle pools (currently only explosion)
     */
    public ParticleManager() {
        allEffects.add(new ProximityEffect("explosion2", 100));
        allEffects.add(new ProximityEffect("creepdies", 1000));
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

}



