package edu.chl.proximity.Models.MenuModels;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2105-04-25
 *
 * 01/05 modified by Hanna Romer. Added FactionChooser.
 * 03/05 modified by Hanna Romer. Added MapSelect.
 * 22/05 modified by Simon Gislen. Changed starting price.
 */
public class MainMenu {
    private Player player;
    private Map map;
    private StartButton startButton;
    private FactionChooser factionChooser;
    private MapSelect mapSelect;
    private Image background;
    private ProximityVector backgroundLocation;


    public MainMenu(ParticleManager particleManager){
        player = GameData.getInstance().getPlayer();

        background = new Image(Constants.FILE_PATH + "Backgrounds/MainMenuBackground.png");
        factionChooser=new FactionChooser();
        mapSelect=new MapSelect(particleManager);
        map=mapSelect.getSelected();
        startButton = new StartButton(new ProximityVector((Constants.GAME_WIDTH/2-150),440));
        backgroundLocation = new ProximityVector(0,0);

    }

    public BoardObject getButtonOnPosition(ProximityVector position){
        if(startButton.containsPoint(position)) {
            return startButton;
        }else if(factionChooser.containsPoint(position)){
            return factionChooser;
        }
        return mapSelect;
    }

    public void pressedStart(){
        player.setFacton(factionChooser.getCurrentlyShown());
        map=mapSelect.getSelected();
        GameData.getInstance().setPlayer(player);
    }

    public Map getMap(){
        return map;
    }

    public void pressedFactionChooser(ProximityVector pos){
        factionChooser.pressed(pos);
    }

    public void pressedMap(ProximityVector pos){mapSelect.pressed(pos);}

    public void render(ProximityBatch batch){
        batch.render(background, backgroundLocation, 0);
        startButton.render(batch);
        factionChooser.render(batch);
        mapSelect.render(batch);
    }

}
