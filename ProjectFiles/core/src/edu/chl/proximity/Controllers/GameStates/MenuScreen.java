package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.MainMenuController;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Viewers.MenuRenderer;

/**
 * @author Johan Swanberg
 * @date 2015-04-07
 *
 * A class for handling the MenuScreen, the screen for the menu
 *
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 25/04 modified by Hanna Romer. Added Game,MainMenu, SpriteBatch, ShapeRenderer,MenuRenderer,MainController, OrthographicCamera and FitViewport
 */
public class MenuScreen implements Screen {

    private MainMenu mainMenu;
    private ProximityBatch batch = new ProximityBatch();
    private ProximityShapeRenderer shapeRenderer = new ProximityShapeRenderer();
    private MenuRenderer menuRenderer;
    private MainMenuController mainMenuController;
    private OrthographicCamera camera;
    private Viewport viewport;

    public MenuScreen(Proximity g, Player player, Viewport viewport){
        GameData.getInstance().setPlayer(player);
        this.mainMenu=new MainMenu(g);

        //Configurates view and controller
        menuRenderer=new MenuRenderer(mainMenu);

        //Fix of camera and graphics
        fixCamera();
        //TODO fix this viewport scaling issue
        /*
        if(viewport==null) {
            fixCamera();
        }else{
            this.viewport=viewport;
            this.camera=(OrthographicCamera) viewport.getCamera();
            viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            //viewport.apply();
        }
        */
        mainMenuController=new MainMenuController(this.viewport);
        mainMenuController.setMainMenu(mainMenu);
        Gdx.input.setInputProcessor(mainMenuController);

    }

    /**
     * Attaches a camera object that views the game-data and interprets it as visual information, and a viewport
     * that scales this information to fit the screen. These objects are part of the LibGDX library.
     */
    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        menuRenderer.render(batch, shapeRenderer);
        batch.end();

        //stage.act(delta);
        //stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
