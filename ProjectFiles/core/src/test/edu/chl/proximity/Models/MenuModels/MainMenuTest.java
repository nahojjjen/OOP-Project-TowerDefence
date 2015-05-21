package test.edu.chl.proximity.Models.MenuModels;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.MenuModels.StartButton;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Settings;
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
    public void testGetButtonOnPositionReturnsRightButtonOnClick(){
        Proximity g = new Proximity();
        Player p=new Player(new Planes());
        GameData.getInstance().setPlayer(p);

        MainMenu m = new MainMenu(g, new ParticleManager(new Settings()));

        assertTrue(m.getButtonOnPosition(new ProximityVector(500,450)) instanceof StartButton);
        System.out.println(Constants.GAME_HEIGHT-240);
        /*
        Double startX=new Double(Constants.GAME_WIDTH/2-150); //X-pos of startbutton. Y-pos is 440.
        StartButton button=new StartButton(new ProximityVector(0,0));
        assertTrue(m.getButtonOnPosition(new ProximityVector(startX.intValue(),440)) instanceof StartButton); //Checking upper left corner
        assertTrue(m.getButtonOnPosition(new ProximityVector(startX.intValue()+button.getWidth(),440)) instanceof StartButton); //Upper right corner
        assertTrue(m.getButtonOnPosition(new ProximityVector(startX.intValue(),440+button.getHeight())) instanceof StartButton);//Lower left corner
        assertTrue(m.getButtonOnPosition(new ProximityVector(startX.intValue() + button.getWidth()440+button.getHeight())) instanceof StartButton); //Lower right corner

        int fcY=Constants.GAME_HEIGHT-240; //Y-pos of FactionChooser. X-pos is 0;
        FactionChooser fc=new FactionChooser();
        assertTrue(m.getButtonOnPosition(new ProximityVector(0,fcY)) instanceof FactionChooser); //Checking upper left corner.
        assertTrue();
        */
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
