package test.edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.Settings;
import junit.framework.TestCase;

/**
 * @author Johan 
 * @date 2015-05-29.
 */
public class Line1Test extends TestCase {


    public void testConstructor() throws Exception {

        Line1 creep = new Line1(8,new ParticleManager(new Settings()), new FirstPath());
        Line1 creepChild = new Line1(creep);
        assertTrue(creep.getCreepLineIndex() -1 == creepChild.getCreepLineIndex());
    }

    public void testDevolve() throws Exception {
    Line1 creep = new Line1(8,new ParticleManager(new Settings()), new FirstPath());
        creep.devolve();
        assertTrue(creep.isRemoved() == true);
        assertTrue(creep.getAddList().size() == 1);
    }

    public void testGetCreepLineIndex() throws Exception {
        Line1 creep = new Line1(6,new ParticleManager(new Settings()), new FirstPath());
        assertTrue(creep.getCreepLineIndex() >  0);
    }

    public void testGetCreepImage() throws Exception {
        Line1 creep = new Line1(6,new ParticleManager(new Settings()), new FirstPath());
        Image img = creep.getImage();
        assertTrue(img != null);
        //Testing that it is possible to get the image without crash
 
    }

    public void testGetCreepResource() throws Exception {
        Line1 creep = new Line1(6,new ParticleManager(new Settings()), new FirstPath());
        assertTrue(creep.getCreepResource().getLines() + creep.getCreepResource().getPolygons() + creep.getCreepResource().getPoints() > 0);
    }

    public void testGetCreepExperiencePoints() throws Exception {
        Line1 creep = new Line1(6,new ParticleManager(new Settings()), new FirstPath());
        assertTrue(creep.getCreepExperiencePoints() > 0);
    }
}