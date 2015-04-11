package edu.chl.proximity.Models.Particles;


/**
 * Created by Johan on 2015-04-11.
 */
public class ParticleManager {
    private ProximityEffect explosionEffect;
    private ProximityEffect creepDiesEffect;

    /**
     * create all the particle pools (currently only explosion)
     */
    public ParticleManager() {
        explosionEffect = new ProximityEffect("explosion", 100);
        creepDiesEffect = new ProximityEffect("creepdies", 1000);
    }

    public ProximityEffect getExplosionEffect() {return explosionEffect;}
    public ProximityEffect getCreepDiesEffect() {return creepDiesEffect;}

}



