package test.edu.chl.proximity.Models.MenuModels;

import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.MenuModels.StartButton;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Romer
 * @date 2015-05-19
 */
public class MainMenuTest {

    @Test
    public void testGetButtonOnPosition(){
        Proximity g = new Proximity();
        Player p=new Player(new Planes());
        GameData.getInstance().setPlayer(p);

        MainMenu m = new MainMenu(g);

        Double startX=new Double(Constants.GAME_WIDTH/2-150);
        assertTrue(m.getButtonOnPosition(new ProximityVector(startX.intValue(),440)) instanceof StartButton);
        /*
        for(int x=startX.intValue(); x<startX.intValue()+326;x ++){
            for(int y=440;y<440+377; y ++){
                assertTrue(m.getButtonOnPosition(new ProximityVector(x,y)) instanceof StartButton);
            }
        }*/

    }

    @Test
    public void testPressedStart(){

    }

    @Test
    public void testPressedFactionChooser(){

    }

    @Test
    public void testPressedMap(){

    }


}
