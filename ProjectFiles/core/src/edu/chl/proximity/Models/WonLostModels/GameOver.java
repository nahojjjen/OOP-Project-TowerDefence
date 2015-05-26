package edu.chl.proximity.Models.WonLostModels;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-15
 *
 * 17/05 modified by Hanna Romer. Added map, player, viewport, game, buttons resume and mainMenu.
 * 24/05 modified by Hanna Romer. Added nullchecker to getButtonActionOnPosition.
 */
public class GameOver {
    private Map map;

    private Button resume;
    private ProximityVector rPos=new ProximityVector(430,400);

    private Button mainMenu;
    private ProximityFont mainMenuText;
    private ProximityFont resumeText;

    private Image defeatImage = new Image(Constants.FILE_PATH + "Backgrounds/defeated.png");

    private ProximityVector mmPos=new ProximityVector(630,400);


    public GameOver(Map map){
        this.map=map;

        mainMenu=new Button(mmPos, new Image(Constants.FILE_PATH + "Buttons/PropertiesPanelButton.png"));
        mainMenuText = new ProximityFont(new ProximityVector(700,430),"Main Menu");
        resume=new Button(rPos, new Image(Constants.FILE_PATH + "Buttons/PropertiesPanelButton.png"));
        resumeText = new ProximityFont(new ProximityVector(500,430),"Start Over");
    }

    public String getButtonActionOnPosition(ProximityVector position) {
        if(position!=null) {
            if (resume.containsPoint(position)) {
                return "Resume";
            } else if (mainMenu.containsPoint(position)) {
                return "MainMenu";
            }
        }
        return null;
    }

    public Map getMap(){
        return map;
    }


    public void render(ProximityBatch batch){
        mainMenu.render(batch);
        resume.render(batch);
        mainMenuText.draw(batch);
        resumeText.draw(batch);
        batch.render(defeatImage, new ProximityVector(500,120),0);
    }
}
