package test.edu.chl.proximity.Models.Map.Particles;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import edu.chl.proximity.Models.Map.Particles.ProximityEffect;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Johan on 2015-05-19.
 */
public class ProximityEffectTest {

    @Test
    public void testSetAngle() throws Exception {

    }

    @Test
    public void testRotateEffect() throws Exception {

    }

    @Test
    public void testCreateEffect() throws Exception {

        ProximityEffect effect = new ProximityEffect(Constants.FILE_PATH + "/Particles/baseGlitter",123);
        ParticleEffect testEffect = effect.createEffect(new ProximityVector(0, 0));
        for (int i = 0; i<99999; i++){
            effect.createEffect(new ProximityVector(0,0));
        }

        effect.rotateEffect(123, testEffect);//should do nothing, since it cant load without opengl
        assertTrue(testEffect.getEmitters().size == 0);

        effect.setAngle(123,123,testEffect); //should do nothing, since it cant load without opengl
    }



}