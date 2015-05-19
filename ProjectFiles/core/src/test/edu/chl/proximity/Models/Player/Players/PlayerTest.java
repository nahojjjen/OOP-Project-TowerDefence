package test.edu.chl.proximity.Models.Player.Players;

import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Settings;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Römer
 * @date 2015-05-19
 */
public class PlayerTest {

    @Test
    public void testSetFacton(){
        Player p = new Player(new Planes());

        p.setFacton(new Filler());
        assertTrue(p.getFaction() instanceof Filler);

        p.setFacton(null);
        assertTrue(p.getFaction()==null);
    }

    @Test
    public void testGetFaction() {
        Player p = new Player(new Planes());

        Faction f = p.getFaction();
        assertTrue(f instanceof Planes);
    }

    @Test
    public void testAddExperiencePoints() {
        Player p = new Player(new Planes());

        double e=p.getExperience();
        p.addExperiencePoints(253);
        assertTrue(p.getExperience() == e + 253);

        e=p.getExperience();
        p.addExperiencePoints(-535);
        assertTrue(p.getExperience()==e-535);

    }

    @Test
    public void testGetResources() {
        Player p = new Player(new Planes());
        p.getResources().setResources(10, 10, 10);
        assertTrue(p.getResources() instanceof Resources);
        assertTrue(p.getResources().getPoints()==10 && p.getResources().getLines()==10 && p.getResources().getPolygons()==10);

    }

    @Test
    public void testCanPlayerAfford() {
        Player p= new Player(new Planes());
        p.getResources().setResources(10,10,10);
        assertTrue(p.canPlayerAfford(new Resources(2, 2, 2)));
        assertFalse(p.canPlayerAfford(new Resources(14,0,0)));
    }

    @Test
    public void testGetExperience() {
        //TODO how do I test this?
    }

    @Test
    public void testGetLevel() {
        //TODO how do I test this?
    }

    @Test
    public void testGetSettings() {
        Player p = new Player(new Planes());
        assertTrue(p.getSettings() instanceof Settings);

        Settings s= new Settings();
        p.setSettings(s);
        assertTrue(p.getSettings().equals(s));
    }

    @Test
    public void testSetSettings() {
        Player p = new Player(new Planes());
        Settings s=new Settings();
        p.setSettings(s);
        assertTrue(p.getSettings().equals(s));
    }
}
