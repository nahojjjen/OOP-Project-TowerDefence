package edu.chl.proximity.Models.WonLostModels;

import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-15
 *
 * 17/05 added map, player, viewport, game, buttons resume and mainMenu.
 */
public class GameOver {
    private Map map;

    private Button resume;
    private ProximityVector rPos=new ProximityVector(200,400);

    private Button mainMenu;
    private ProximityVector mmPos=new ProximityVector(400,400);


    public GameOver(Map map){
        this.map=map;

        mainMenu=new Button(mmPos, new Image(Constants.FILE_PATH + "Buttons/ResumeButton.png"));
        resume=new Button(rPos, new Image(Constants.FILE_PATH + "Buttons/ResumeButton.png"));
    }

    public String getButtonActionOnPosition(ProximityVector position) {
        if (resume.containsPoint(position)) {
            return "Resume";
        } else if (mainMenu.containsPoint(position)) {
            return "MainMenu";
        }
        return null;
    }

    public Map getMap(){
        return map;
    }


    public void render(ProximityBatch batch){
        mainMenu.render(batch);
        resume.render(batch);
    }
}
