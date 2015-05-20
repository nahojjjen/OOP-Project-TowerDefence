package test.edu.chl.proximity.Models.Map.Particles;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Particles.ProximityEffect;
import edu.chl.proximity.Models.Utils.Settings;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan
 *
 * @date 2015-05-19.
 */
public class ParticleManagerTest {

    @Test
    public void testParticleManager(){
        ParticleManager test = new ParticleManager(new Settings());

        assertTrue(test.getBaseCracksEffect() instanceof ProximityEffect);
        assertTrue(test.getBaseDamageEffect()  instanceof ProximityEffect);
        assertTrue(test.getBloodCarnageCreepEffect()  instanceof ProximityEffect);
        assertTrue(test.getBloodCarnageEffect() instanceof ProximityEffect);
        assertTrue(test.getBloodPoolCreepEffect() instanceof ProximityEffect);
        assertTrue(test.getBloodPoolEffect() instanceof ProximityEffect);
        assertTrue(test.getBulletEffect() instanceof ProximityEffect);
        assertTrue(test.getCreepDiesEffect() instanceof ProximityEffect);
        assertTrue(test.getDirtSmokeEffect() instanceof ProximityEffect);
        assertTrue(test.getExplosionEffect() instanceof ProximityEffect);
        assertTrue(test.getFireCreepEffect() instanceof ProximityEffect);
        assertTrue(test.getFireFieldEffect() instanceof ProximityEffect);
        assertTrue(test.getFrostBlastEffect() instanceof ProximityEffect);
        assertTrue(test.getFrostField() instanceof ProximityEffect);
        assertTrue(test.getLightningCreepEffect() instanceof ProximityEffect);
        assertTrue(test.getLightningOriginSpellEffect() instanceof ProximityEffect);
        assertTrue(test.getSacrificeEffect() instanceof ProximityEffect);
        assertTrue(test.getWallOfStone() instanceof ProximityEffect);

    }
}