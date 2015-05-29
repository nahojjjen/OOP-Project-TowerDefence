package test.edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.FillerBase;
import edu.chl.proximity.Models.Map.Bases.ShardBase;
import edu.chl.proximity.Models.Map.Bases.WingedBase;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.LadyLuck;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Settings;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @Johan
 * @date 2015-05-18.
 * Tests all base classes
 */
public class AllBaseTest {
    @Test
    public void testHealAndDamage() throws Exception {
        Base filler = new FillerBase(new FirstPath(), new ParticleManager(new Settings()));
        Base planes = new ShardBase(new FirstPath(), new ParticleManager(new Settings()));
        Base lady = new WingedBase(new FirstPath(), new ParticleManager(new Settings()));

        runTests(filler);
        runTests(planes);
        runTests(lady);

    }

    private void runTests(Base base) throws Exception{
        testHealAndDamage(base);
        testSetLife(base);
        testNotCrashOnSetEffect(base);
    }

    private void testHealAndDamage(Base base){
        assertNotNull(base);
        assertTrue(base.getLife() == 100);
        base.heal(5);
        assertTrue(base.getLife() == 105);
        base.damage(5);
        assertTrue(base.getLife() == 100);
        base.damage(-10);
        assertTrue(base.getLife() == 110);
    }


    public void testSetLife(Base base) throws Exception {
        base.setLife(1);
        assertTrue(base.getLife() == 1);
        base.setLife(-1);
        assertTrue(base.getLife() == -1);
    }

    public void testNotCrashOnSetEffect(Base test){
        //pretty much just make sure the program doesnt crash when you use these setter methods.
        test.showDamageEffect();
        test.setCracksEffect(new ParticleManager(new Settings()).getBulletEffect());
        test.setDamageEffect(new ParticleManager(new Settings()).getBulletEffect());
    }

}