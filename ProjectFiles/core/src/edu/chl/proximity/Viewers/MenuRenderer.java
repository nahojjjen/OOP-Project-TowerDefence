package edu.chl.proximity.Viewers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityBatch;

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

    public void render(ProximityBatch batch, ShapeRenderer shapeRenderer){
        mainMenu.render(batch);
    }

}
