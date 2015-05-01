package edu.chl.proximity.Models.MenuModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna Römer
 * @date 2105-04-25
 */
public class MainMenu {
    private Faction faction;
    private Player player;
    private Map map;
    private StartButton startButton=new StartButton(new Vector2((Gdx.graphics.getWidth()/2)-64,600));
    private FactionChooser factionChooser=new FactionChooser();


    public MainMenu(){
        faction=new Planes();
        player=new Player(faction);
        map=new StandardMap();

    }



    public BoardObject getButtonOnPosition(Vector2 position){
        if(PointCalculations.isPointInObject(position, startButton)) {
            return startButton;
        }else if(PointCalculations.isPointInObject(position,factionChooser)){
            return factionChooser;
        }
        return null;
    }

    public void pressedStart(){
        player.setFacton(factionChooser.getCurrentlyShown());

        GameData.getInstance().setMainMenu(this);
        GameData.getInstance().setPlayer(player);
        GameData.getInstance().setMap(map);
        GameData.getInstance().getGame().changeScreen(Proximity.State.GAME,map,this,player);
    }

    public void pressedFactionChooser(Vector2 pos){
        factionChooser.pressed(pos);
    }

    public void render(SpriteBatch batch){
        startButton.render(batch);
        factionChooser.render(batch);
    }
}
