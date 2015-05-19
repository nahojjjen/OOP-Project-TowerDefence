package edu.chl.proximity.Models.WonLostModels;

import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-15
 *
 * 17/05 added map, player, viewport, game, buttons resume and mainMenu.
 */
public class GameOver {
    private Map map;
    private Player player;
    private Viewport viewport;
    private Proximity game;

    private Button resume;
    private ProximityVector rPos=new ProximityVector(200,400);

    private Button mainMenu;
    private ProximityVector mmPos=new ProximityVector(400,400);


    public GameOver(Map map, Player player, Viewport viewport, Proximity game){
        this.map=map;
        this.player=player;
        this.viewport=viewport;
        this.game=game;

        mainMenu=new Button(mmPos, new Image(Constants.FILE_PATH + "Buttons/ResumeButton.png"));
        resume=new Button(rPos, new Image(Constants.FILE_PATH + "Buttons/ResumeButton.png"));
    }

    public void pressedButtonOnPosition(ProximityVector position) {
        if (PointCalculations.isPointInObject(position, resume)) {
            pressedResume();
        } else if (PointCalculations.isPointInObject(position, mainMenu)) {
            pressedMainMenu();
        }
    }

    public void pressedResume(){
        game.changeScreen(Proximity.State.GAME, map.getNew(), player, viewport);
    }

    public void pressedMainMenu(){
        game.changeScreen(Proximity.State.MAIN_MENU, map, player, viewport);
    }

    public void render(ProximityBatch batch){
        mainMenu.render(batch);
        resume.render(batch);
    }
}
