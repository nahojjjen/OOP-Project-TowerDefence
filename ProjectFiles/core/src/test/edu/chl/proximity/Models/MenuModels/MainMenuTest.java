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
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Hanna Romer
 * @date 2015-05-19
 */
public class MainMenuTest {

    @Test
    public void testGetButtonOnPositionReturnsRightButtonOnClick(){
        Player p=new Player(new Planes());
        GameData.getInstance().setPlayer(p);

        MainMenu m = new MainMenu(new ParticleManager(new Settings()));

        assertTrue(m.getButtonOnPosition(new ProximityVector(500, 450)) instanceof StartButton);
        assertTrue(m.getButtonOnPosition(new ProximityVector(10,500)) instanceof FactionChooser);

        for(int x=0; x<=Constants.GAME_WIDTH; x++){
            for(int y=0; y<=Constants.GAME_WIDTH; y++){
                ProximityVector pos=new ProximityVector(x,y);
                if(!(m.getButtonOnPosition(pos) instanceof StartButton || m.getButtonOnPosition(pos) instanceof FactionChooser)){
                    assertTrue(m.getButtonOnPosition(pos) instanceof MapSelect);
                }
            }
        }

        assertTrue(m.getButtonOnPosition(new ProximityVector(Integer.MAX_VALUE,Integer.MAX_VALUE)) instanceof MapSelect);
        assertTrue(m.getButtonOnPosition(new ProximityVector(Integer.MIN_VALUE,Integer.MIN_VALUE)) instanceof MapSelect);
        assertTrue(m.getButtonOnPosition(null) instanceof MapSelect);
    }

    @Test
    public void testPressedStartStartsGame(){
        Player p=new Player(new Planes());
        GameData.getInstance().setPlayer(p);
        MainMenu m = new MainMenu(new ParticleManager(new Settings()));
        m.pressedStart();
        assertTrue(p.getFaction()!=null);
        assertTrue(p.getResources()!=null);

    }


}
