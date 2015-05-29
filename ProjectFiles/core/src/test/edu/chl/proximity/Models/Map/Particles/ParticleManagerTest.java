package test.edu.chl.proximity.Models.Map.Particles;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Particles.ProximityEffect;
import edu.chl.proximity.Models.Utils.Settings;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Johan
 *
 * @date 2015-05-19.
 */
public class ParticleManagerTest {

    @Test
    public void testClearEffects(){

        ParticleManager test = new ParticleManager(new Settings());
        test.clearAllParticles();
    }

        @Test
        public void testParticleManager(){
        ParticleManager test = new ParticleManager(new Settings());

        assertTrue(test.getBaseCracksEffect() != null);
        assertTrue(test.getBaseDamageEffect() != null);
        assertTrue(test.getBloodCarnageCreepEffect() != null);
        assertTrue(test.getBloodCarnageEffect() != null);
        assertTrue(test.getBloodPoolCreepEffect() != null);
        assertTrue(test.getBloodPoolEffect() != null);
        assertTrue(test.getBulletEffect() != null);
        assertTrue(test.getCreepDiesEffect() != null);
        assertTrue(test.getDirtSmokeEffect() != null);
        assertTrue(test.getExplosionEffect() != null);
        assertTrue(test.getFireCreepEffect() != null);
        assertTrue(test.getFireFieldEffect() != null);
        assertTrue(test.getFrostBlastEffect() != null);
        assertTrue(test.getFrostField() != null);
        assertTrue(test.getLightningCreepEffect() != null);
        assertTrue(test.getLightningOriginSpellEffect() != null);
        assertTrue(test.getSacrificeEffect() != null);
        assertTrue(test.getWallOfStone() != null);
        assertTrue(test.getBloodPoolEffect() != null);
        assertTrue(test.getBloodPoolCreepEffect() != null);
        assertTrue(test.getBloodCarnageEffect() != null);
        assertTrue(test.getBloodCarnageCreepEffect() != null);
        assertTrue(test.getSacrificeEffect() != null);
        assertTrue(test.getFireBurstEffect() != null);
        assertTrue(test.getDustCreepEffect() != null);
        assertTrue(test.getBloodSipperEffect() != null);
        assertTrue(test.getLuckQGood() != null);
        assertTrue(test.getLuckQBad() != null);
        assertTrue(test.getLuckWGood() != null);
        assertTrue(test.getLuckWBad() != null);

    }
}