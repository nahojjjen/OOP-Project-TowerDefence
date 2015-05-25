package test.edu.chl.proximity.Models.Map.Creeps;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Circle;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line2;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Triangle;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import junit.framework.TestCase;

/**
 * @author Simon Gislen.
 * @date 18/05/15.
 *
 *
 *  18/5 modified by Johan, added more test cases
 *
 * A Test class for Creep
 */
public class CreepTest extends TestCase {

    private Map map;
    private Path path;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        map = new StandardMap(new ParticleManager(new Settings()));
        path = new FirstPath();
    }

    public void testSetupCreep() throws Exception {

        Creep creep = new Line1(1, null, path);
        creep.setupCreep(2f);

        double expected = 2f;
        double actual = creep.getSpeed();
        assertEquals(expected, actual);
    }

    public void testGetNextWayPointID() throws Exception {
        Creep creep = new Line1(1, null, path);
        int nextWayPoint = creep.getNextWayPointID();
        assertTrue(nextWayPoint == 1);
    }


    public void testDestroy() throws Exception {
        Creep creep = new Circle(new ParticleManager(new Settings()), new FirstPath());
        creep.destroy();
        assertTrue(creep.isRemoved());

    }

    public void testRotate() throws Exception {
        Creep creep = new Triangle(new ParticleManager(new Settings()), new FirstPath());
        creep.rotate(5);

    }



    public void testGetCreepResource() throws Exception {
        GameData.getInstance();
        GameData.getInstance().setPlayer(new Player(new Filler()));
        Creep creep = new Circle(new ParticleManager(new Settings()), new FirstPath());
        Resources gotten = creep.getCreepResource();
        assertTrue(gotten.getLines() + gotten.getPolygons() + gotten.getPoints() > 0);
    }

    public void testGetCreepExperiencePointsAndDevolve() throws Exception {

        Player player = new Player(new Filler());

        GameData.getInstance().setPlayer(player);
        int currentExp = GameData.getInstance().getPlayer().getExperience(); //will load exp from disk
        Creep creep = new Circle(new ParticleManager(new Settings()), new FirstPath());
        creep.devolve();
        player.addExperiencePoints(creep.getCreepExperiencePoints());
        assertTrue(GameData.getInstance().getPlayer().getExperience() > currentExp);


        Creep creep2 = new Line1(5,new ParticleManager(new Settings()), new FirstPath());
        int resourceSum1 = player.getResources().getLines() + player.getResources().getPoints();
        creep2.devolve();
        player.addResources(creep2.getCreepResource());
        int resourceSum2 = player.getResources().getLines() + player.getResources().getPoints();
        assertTrue(resourceSum1 < resourceSum2);

        Creep creep4 = new Triangle(new ParticleManager(new Settings()), new FirstPath());
        int resourceSum5 = player.getResources().getLines() + player.getResources().getPoints();
        creep4.devolve();
        player.addResources(creep4.getCreepResource());
        int resourceSum6 = player.getResources().getLines() + player.getResources().getPoints();
        assertTrue(resourceSum5 < resourceSum6);

        Creep creep3 = new Line2(5,new ParticleManager(new Settings()), new FirstPath());
        int resourceSum3 = player.getResources().getLines() + player.getResources().getPoints();
        creep3.devolve();
        player.addResources(creep3.getCreepResource());
        int resourceSum4 = player.getResources().getLines() + player.getResources().getPoints();
        assertTrue(resourceSum3 < resourceSum4);




    }

    public void testMove() throws Exception {
        Player player = new Player(new Filler());
        GameData.getInstance();
        GameData.getInstance().setPlayer(player);
        Creep creep = new Circle(new ParticleManager(new Settings()), new FirstPath());
        double xcoor = creep.getPosition().x;
        double ycoor = creep.getPosition().y;
        creep.move();
        assertTrue(creep.getPosition().x != xcoor && creep.getPosition().y != ycoor);
        for(int i = 0; i< 10000; i++){ //make sure the creep goes to the absolute end of the board
            creep.move();
        }
        assertTrue(creep.isRemoved());
    }

    public void testGetAngleToNextPoint() throws Exception {

        Creep creep = new Triangle(new ParticleManager(new Settings()), new FirstPath());
        creep.setPosition(new ProximityVector(0,0));
        double firstAngle = creep.getAngleToNextPoint();
       creep.setPosition(new ProximityVector(100, 100));
        assertTrue(firstAngle != creep.getAngleToNextPoint());
        creep.setPosition(new ProximityVector(123123123, 100));
        assertTrue(firstAngle != creep.getAngleToNextPoint());

    }


    public void testSlowDownAndCheckifSpeedUp() throws Exception {
        Creep creep = new Triangle(new ParticleManager(new Settings()), new FirstPath());
        double creepSpeed1 = creep.getSpeed();
        creep.slowDown(50, 100);
        assertTrue(creep.getSpeed()!= creepSpeed1); //ensure that the speed has changed
        for(int i=0; i<99; i++){
            creep.checkIfSpeedUp(); //run the speedcheck up to the limit
        }

        assertTrue(creep.getSpeed()!= creepSpeed1); //ensure the creepis still slowed
        for(int i=0; i<99; i++){
            creep.checkIfSpeedUp(); //make the speed timer run out
        }
        assertTrue(creep.getSpeed() == creepSpeed1); //ensure the speed has been restored

    }

    public void testGetPath() throws Exception {
        Player player = new Player(new Filler());
        GameData.getInstance();
        GameData.getInstance().setPlayer(player);
        Path path = new FirstPath();
        Creep creep = new Circle(new ParticleManager(new Settings()),path );
        assertTrue(creep.getPath() == path);
    }
}