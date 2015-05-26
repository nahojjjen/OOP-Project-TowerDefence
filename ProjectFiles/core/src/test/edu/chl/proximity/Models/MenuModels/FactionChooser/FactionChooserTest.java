package test.edu.chl.proximity.Models.MenuModels.FactionChooser;

import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.LadyLuck;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Romer
 * @date 2015-05-24.
 */
public class FactionChooserTest {

    @Test
    public void testPressedNextShowsNext(){
        FactionChooser fc = new FactionChooser();
        for(int i=0;i<3;i++) {
            Faction f=fc.getCurrentlyShown();
            fc.pressedNext();
            if (f instanceof Planes) {
                assertTrue(fc.getCurrentlyShown() instanceof Filler);
            } else if (f instanceof Filler) {
                assertTrue(fc.getCurrentlyShown() instanceof LadyLuck);
            } else if (f instanceof LadyLuck) {
                assertTrue(fc.getCurrentlyShown() instanceof Planes);
            }
        }

    }

    @Test
    public void testPressedPrevious(){
        FactionChooser fc = new FactionChooser();
        for(int i=0;i<3;i++) {
            Faction f=fc.getCurrentlyShown();
            fc.pressedPrevious();
            if (f instanceof Planes) {
                assertTrue(fc.getCurrentlyShown() instanceof LadyLuck);
            } else if (f instanceof Filler) {
                assertTrue(fc.getCurrentlyShown() instanceof Planes);
            } else if (f instanceof LadyLuck) {
                assertTrue(fc.getCurrentlyShown() instanceof Filler);
            }
        }
    }

    @Test
    public void testPressed(){
        FactionChooser fc = new FactionChooser(); //Starts with Planes as currently shown
        fc.pressed(new ProximityVector(8,Constants.GAME_HEIGHT-200)); //Should result in Previous being pressed
        assertTrue(fc.getCurrentlyShown() instanceof LadyLuck);

        fc.pressed(new ProximityVector(352, Constants.GAME_HEIGHT - 200));//Should result in Next being pressed
        assertTrue(fc.getCurrentlyShown() instanceof Planes);

        fc.pressed(null); //Should do nothing, since nothing was pressed
        assertTrue(fc.getCurrentlyShown() instanceof Planes);

        fc.pressed(new ProximityVector(Integer.MAX_VALUE, Integer.MAX_VALUE)); //Outside of window. Nothing is therefore pressed
        assertTrue(fc.getCurrentlyShown() instanceof Planes);

        fc.pressed(new ProximityVector(Integer.MIN_VALUE, Integer.MIN_VALUE)); ////Outside of window. Nothing is therefore pressed
        assertTrue(fc.getCurrentlyShown() instanceof Planes);
        
    }


    @Test
    public void testGetCurrentlyShownReturnsFaction(){
        FactionChooser fc = new FactionChooser();
        assertTrue(fc.getCurrentlyShown() instanceof Faction);
    }

    /*
    Order of factions in list:
    Planes
    Filler
    LadyLuck
    */
}
