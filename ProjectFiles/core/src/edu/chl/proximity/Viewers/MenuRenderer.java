package edu.chl.proximity.Viewers;

import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityShapeRenderer;

/**
 * @author Hanna Romer
 * @date 2015-04-25
 *
 * A class that handles the rendering of the main menu screen
 */
public class MenuRenderer {
    private MainMenu mainMenu;
    public MenuRenderer(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    public void render(ProximityBatch batch, ProximityShapeRenderer shapeRenderer){
        mainMenu.render(batch);
    }

}
