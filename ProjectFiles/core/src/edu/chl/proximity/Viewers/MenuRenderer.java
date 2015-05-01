package edu.chl.proximity.Viewers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;

/**
 * @author Hanna R�mer
 * @date 2015-04-25
 */
public class MenuRenderer {
    private MainMenu mainMenu;
    private ParticleManager particleManager ;

    public MenuRenderer(){

        mainMenu= GameData.getInstance().getMainMenu();
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer){
        mainMenu.render(batch);
    }

}
