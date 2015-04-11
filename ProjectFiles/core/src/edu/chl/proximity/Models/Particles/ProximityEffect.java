package edu.chl.proximity.Models.Particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 2015-04-11.
 */
public class ProximityEffect {
    private ParticleEffectPool effectPool; //effects that can be cretaed
    List<ParticleEffectPool.PooledEffect> effects = new ArrayList(); //effects currently on the map

    public ProximityEffect(String fileName, int maxPoolAmount){
        //maps out the file handles the particle requires
        FileHandle particleEffectsImagesFolder = new FileHandle(Constants.filePath + "Particles/");
        FileHandle effectFile = new FileHandle(Constants.filePath + "Particles/" + fileName);

        //Configures 1 example effect
        ParticleEffect effect = new ParticleEffect();
        effect.load(effectFile, particleEffectsImagesFolder);

        //loads this effect into the pool, so the pool knows what kind of effect to populate itself with
         effectPool = new ParticleEffectPool(effect, 1, maxPoolAmount );

    }


    public List<ParticleEffectPool.PooledEffect> getActiveEffects(){
        return effects;
    }
    /**
     * @param x
     * @param y
     */
    public void createEffect(float x, float y) {
        ParticleEffectPool.PooledEffect effect = effectPool.obtain();
        effect.setPosition(x, y);
        effects.add(effect);
        effect.start();
    }

    /**
     * cycle through all active effects and render them using the supplied batch
     * @param batch what rendering batch to use
     */
    private  void renderAllActiveEffects(SpriteBatch batch)   {
        Map map = GameData.getInstance().getMap();

        for (int i = effects.size() - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);
            effect.draw(batch, Gdx.graphics.getDeltaTime());
            if(effect.isComplete()){
                effect.free();
                effects.remove(effect);
            }
        }
    }

}
