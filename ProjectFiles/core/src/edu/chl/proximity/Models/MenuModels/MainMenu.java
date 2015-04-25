package edu.chl.proximity.Models.MenuModels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Backgrounds.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.StandardMap;
import edu.chl.proximity.Models.Players.Player;
import edu.chl.proximity.Models.PropertiesPanel.SoundBar;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna Römer
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
