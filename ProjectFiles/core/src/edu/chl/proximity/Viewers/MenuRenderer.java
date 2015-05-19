package edu.chl.proximity.Viewers;

import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;

/**
 * @author Hanna Romer
 * @date 2015-04-25
 */
public class MenuRenderer {
    private MainMenu mainMenu;
    private ParticleManager particleManager ;

    public MenuRenderer(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    public void render(ProximityBatch batch, ProximityShapeRenderer shapeRenderer){
        mainMenu.render(batch);
    }

}
