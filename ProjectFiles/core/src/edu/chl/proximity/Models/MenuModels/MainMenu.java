package edu.chl.proximity.Models.MenuModels;

import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna Romer
 * @date 2105-04-25
 *
 * 01/05 modified by Hanna Romer. Added FactionChooser.
 * 03/05 modified by Hanna Romer. Added MapSelect.
 */
public class MainMenu {
    private Faction faction;
    private Player player;
    private Map map;
    private StartButton startButton;
    private FactionChooser factionChooser;
    private MapSelect mapSelect;
    private Proximity game;
    private Image background;


    public MainMenu(Proximity game){
        this.game = game;
        player = GameData.getInstance().getPlayer();

        background = new Image(Constants.FILE_PATH + "Backgrounds/MainMenuBackground.png");
        factionChooser=new FactionChooser();
        mapSelect=new MapSelect(null);
        map=mapSelect.getSelected();
        startButton = new StartButton(map, new ProximityVector((Constants.GAME_WIDTH/2-150),440));

    }



    public BoardObject getButtonOnPosition(ProximityVector position){
        if(startButton.containsPoint(position)) {
            return startButton;
        }else if(factionChooser.containsPoint(position)){
            return factionChooser;
        }
        return mapSelect;
    }

    public void pressedStart(Viewport viewport){
        player.setFacton(factionChooser.getCurrentlyShown());
        map=mapSelect.getSelected();
        player.getFaction().configureSpells(map.getParticleManager());

        GameData.getInstance().setPlayer(player);
        player.getResources().setResources(500,500,0);

        game.changeScreen(Proximity.State.GAME, map, player,viewport);
        //game.changeScreen(Proximity.State.GAME_OVER,map,player,viewport);
    }


    public void pressedFactionChooser(ProximityVector pos){
        factionChooser.pressed(pos);
    }

    public void pressedMap(ProximityVector pos){mapSelect.pressed(pos);}

    public void render(ProximityBatch batch){

        batch.render(background, new ProximityVector(0,0), 0);
        startButton.render(batch);
        factionChooser.render(batch);
        mapSelect.render(batch);
    }
}
