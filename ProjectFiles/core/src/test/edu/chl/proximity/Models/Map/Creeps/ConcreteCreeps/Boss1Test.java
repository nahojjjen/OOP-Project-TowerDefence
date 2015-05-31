package test.edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Boss1;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.Settings;
import junit.framework.TestCase;

/**
 * @author Johan
 * @date 2015-05-29.
 */
public class Boss1Test extends TestCase {

    public void testDevolve() throws Exception {
        Boss1 creep = new Boss1(new ParticleManager(new Settings()), new FirstPath());
        assertTrue(creep.getAddList().size() == 0);
        creep.devolve();
        assertTrue(creep.getAddList().size()==0);
        for (int i = 0; i<100; i++){
            creep.unRemove();
            creep.devolve();
        }
        assertTrue(creep.getAddList().size() > 5);
    }

    public void testGetCreepLineIndex() throws Exception {
        Boss1 creep = new Boss1(new ParticleManager(new Settings()), new FirstPath());
        assertTrue(creep.getCreepLineIndex() >  0);
    }

    public void testGetCreepImage() throws Exception {
        Boss1 creep = new Boss1(new ParticleManager(new Settings()), new FirstPath());
        for (int i = 0; i<40; i++){
            creep.unRemove();
            creep.devolve();
        }
        Image img = creep.getImage();
        assertTrue(img != null);
        for (int i = 0; i<30; i++){
            creep.unRemove();
            creep.devolve();
        }
        Image img2 = creep.getImage();
        assertTrue(img2 != null);
    }

    public void testGetCreepResource() throws Exception {

        Boss1 creep = new Boss1(new ParticleManager(new Settings()), new FirstPath());
        assertTrue(creep.getCreepResource().getLines() + creep.getCreepResource().getPolygons() + creep.getCreepResource().getPoints() > 0);
    }

    public void testGetCreepExperiencePoints() throws Exception {
        Boss1 creep = new Boss1(new ParticleManager(new Settings()), new FirstPath());
        assertTrue(creep.getCreepExperiencePoints() > 0);
    }
}