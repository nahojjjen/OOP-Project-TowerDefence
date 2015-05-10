package edu.chl.proximity.Models.Map.Particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import com.badlogic.gdx.utils.Array;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-11
 *
 * A class that describes a "type" of graphical particle-effect, for instance all frostBolt effects. This class is instanciated
 * in ParticleManager.  One "proximityEffect" keeps track of all effects of its given type.
 * To create an effect, call  GameData.getInstance().getMap().getParticleManager().getEFFECTNAME().createEffect(Position);
 *
 * 04/24 Modified by Johan, adds working rotation & angle modification and createeffect returns the created effect
 * 05/10 Modified by Johan, fixes memory leak and limits particle creation to pool size
 *
 */
public class ProximityEffect {
    private ParticleEffectPool effectPool; //effects that can be created
    private ParticleEffect effectTemplate;
    private FileHandle effectFile;
    List<ParticleEffectPool.PooledEffect> effects = new ArrayList(); //effects currently on the map

    /**
     * create a new type of particles that can be rendered
     * @param fileName What the filename is for the particle
     * @param maxPoolAmount Max amount of this type of effect on the map at once.
     */
    public ProximityEffect(String fileName, int maxPoolAmount){
        //maps out the file handles the particle requires
        FileHandle particleEffectsImagesFolder = new FileHandle(Constants.FILE_PATH + "Particles/ParticleImages/");
        effectFile = new FileHandle(Constants.FILE_PATH + "Particles/" + fileName);

        //Configures 1 example effect
        effectTemplate = new ParticleEffect();
        effectTemplate.load(effectFile, particleEffectsImagesFolder);
        flipAllEmitterY(effectTemplate.getEmitters());

        //loads the example effect into the pool, so the pool knows what kind of effect to populate itself with (see pool-design pattern)
         effectPool = new ParticleEffectPool(effectTemplate, 1, maxPoolAmount );
    }


    /**
     * changes the direction and "spray" of a particle-effect
     * @param angle what general angle the effect should move towards
     * @param spread how focused the beam of particles should be (input 360 would throw particles in random direction)
     * @param effect what effect to set the angle at
     */
    public void setAngle(float angle, float spread, ParticleEffect effect){
        Array<ParticleEmitter> emitters = effect.getEmitters();
        for (int i=0; i<emitters.size; i++) {
            emitters.get(i).getAngle().setHighMax(angle + spread/2);
            emitters.get(i).getAngle().setHighMin(angle - spread/2);
            emitters.get(i).getAngle().setLowMax(angle + spread/2);
            emitters.get(i).getAngle().setLowMin(angle - spread/2);

        }
    }

    /**
     * rotates an effect by a given amount
     * @param angle how much to rotate the effect in degrees
     * @param effect what specific effect to rotate
     */
    public void rotateEffect(float angle, ParticleEffect effect){
        Array<ParticleEmitter> emitters = effect.getEmitters();
        for (int i=0; i<emitters.size; i++) {
            float startMax = emitters.get(i).getAngle().getHighMax();
            float startMin = emitters.get(i).getAngle().getHighMin();

            emitters.get(i).getAngle().setHighMax(startMax+angle);
            emitters.get(i).getAngle().setHighMin(startMin+angle);
        }
    }

    /**
     * A method that manually flips all the particle emitters so that it's not upside down
     * This method needs to exist because the effect.flipY supplied by the library is bugged & doesnt work.
     * @param emitters all the emitters that should be flipped
     */
    private void flipAllEmitterY(Array<ParticleEmitter> emitters){
        for (int i=0; i<emitters.size; i++){
            emitters.get(i).flipY();
        }
    }

    /**
     * Create a new ParticleEffect at the given location
     *
     * @param x where to create the effect in x-coordinate
     * @param y where to create the effect in y-coordinate
     */
    public ParticleEffect createEffect(float x, float y) {
        if (effectPool != null){
            if (effectPool.getFree() > 0 || effectPool.max >= effectPool.peak){
                ParticleEffectPool.PooledEffect effect = effectPool.obtain();
                effect.setPosition(x, y);
                effects.add(effect);
                effect.start();
                return effect;
            }
            else{
                System.out.println(effectPool.getFree() + "<- amount of free particles");
                System.out.println("Could not create effect, pool empty! Skipping. Effect that could not be created: " + effectFile);
            }


        }
        return null;
    }
    /**
     * Create a new ParticleEffect at the given location
     *
     * @param vector The vector point where this effect should be created
     */
    public ParticleEffect createEffect(ProximityVector vector) {
        return createEffect(vector.x, vector.y);
    }

    /**
     * cycle through all active effects and render them using the supplied batch
     * @param batch what rendering batch to use
     */
    public  void renderAllActiveEffects(SpriteBatch batch) {

        for (int i = 0; i<effects.size(); i++) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);

            effect.draw(batch, Gdx.graphics.getDeltaTime()*GameData.getInstance().getPlayer().getSettings().getGameSpeed());

            if(effect.getEmitters().get(0).getPercentComplete() >= 0 && !effect.getEmitters().get(0).isContinuous()){//effect.isComplete()){
                effect.free(); //put the effect back in the pool if it is done )
                effects.remove(effect); //remove the finished effect from the list of active effects
            }

        }
    }

}
