package edu.chl.proximity.Models.MenuModels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna Römer
 * @date 2105-04-25
 *
 * 01/05 modified by Hanna Römer. Added FactionChooser.
 * 03/05 modified by Hanna Römer. Added MapSelect.
 */
public class MainMenu {
    private Faction faction;
    private Player player;
    private Map map;
    private StartButton startButton;
    private FactionChooser factionChooser;
    private MapSelect mapSelect;
    private Proximity game;


    public MainMenu(Proximity game){
        this.game = game;
        player = GameData.getInstance().getPlayer();


        factionChooser=new FactionChooser();
        mapSelect=new MapSelect(null);
        map=mapSelect.getSelected();
        startButton = new StartButton(map, new ProximityVector((Gdx.graphics.getWidth()/2)-64,600));

    }



    public BoardObject getButtonOnPosition(ProximityVector position){
        if(PointCalculations.isPointInObject(position, startButton)) {
            return startButton;
        }else if(PointCalculations.isPointInObject(position,factionChooser)){
            return factionChooser;
        }
        return mapSelect;
    }

    public void pressedStart(Viewport viewport){
        player.setFacton(factionChooser.getCurrentlyShown());
        map=mapSelect.getSelected();
        player.getFaction().configureSpells(map);

        GameData.getInstance().setPlayer(player);
        player.getResources().setResources(500,500,0);

        game.changeScreen(Proximity.State.GAME, map, player,viewport);
    }


    public void pressedFactionChooser(ProximityVector pos){
        factionChooser.pressed(pos);
    }

    public void pressedMap(ProximityVector pos){mapSelect.pressed(pos);}

    public void render(SpriteBatch batch){
        startButton.render(batch);
        factionChooser.render(batch);
        mapSelect.render(batch);
    }
}
