package test.edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Map.Bases.FillerBase;
import edu.chl.proximity.Models.Map.Bases.ShardBase;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Johan
 * @date 2015-05-18.
 * Tests fillerBase and
 */
public class AllBaseTest {
    @Test
    public void testHealAndDamage() throws Exception {
       FillerBase test = new FillerBase(new FirstPath(), new ParticleManager());
        assertTrue(test.getLife() == 100);
        test.heal(5);
        assertTrue(test.getLife() == 105);
        test.damage();
        assertTrue(test.getLife() == 104);
        test.damage(54);
        assertTrue(test.getLife() == 50);
        test.damage(50);

    }

    @Test
    public void testSetLife() throws Exception {
        FillerBase test = new FillerBase(new FirstPath(), new ParticleManager());
        test.setLife(1);
        assertTrue(test.getLife() == 1);
    }

    @Test
    public void testNotCrachOnSetEffect(){
        //pretty much just make sure the program doesnt crash when you use these setter methods.
        ShardBase test = new ShardBase(new FirstPath(), new ParticleManager());
        test.showDamageEffect();
        test.setCracksEffect(new ParticleManager().getBulletEffect());
        test.setDamageEffect(new ParticleManager().getBulletEffect());
    }

}