package edu.chl.proximity.Models.MenuModels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna R�mer
 * @date 2105-04-25
 */
public class MainMenu {
    private StartButton startButton=new StartButton(new Vector2(200,200));


    public MainMenu(){

    }

    public BoardObject getButtonOnPosition(Vector2 position){

        if(PointCalculations.isPointInObject(position, startButton)) {
            return startButton;
        }
        return null;
    }
    public void pressedStart(){
        GameData.getInstance().getGame().changeScreen(Proximity.State.GAME,new StandardMap(),this,new Player(new Planes()));
    }

    public void render(SpriteBatch batch){
        startButton.render(batch);
    }
}
