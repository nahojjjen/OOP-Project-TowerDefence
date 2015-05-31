package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.MainMenuController;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChangerListener;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.ProximityAudioPlayer;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityShapeRenderer;
import edu.chl.proximity.Viewers.MenuRenderer;

/**
 * @author Johan Swanberg
 * @date 2015-04-07
 *
 * A class for handling the MenuScreen, the screen for the menu. It handles the
 * controllers, models and renderers for the main menu.
 *
 * ---
 * 08/04 Modified by Johan Swanberg. Switch to ScreenType from GameState.
 * 25/04 modified by Hanna Romer. Added Game,MainMenu, SpriteBatch, ShapeRenderer,MenuRenderer,GameController, OrthographicCamera and FitViewport
 */
public class MenuScreen implements Screen, ScreenChangerListener {

    //View/Renderer
    private MenuRenderer menuRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;

    //Controller
    private Game game;
    private MainMenuController mainMenuController;

    //Model
    private ProximityBatch batch;
    private ProximityShapeRenderer shapeRenderer;
    private MainMenu mainMenu;
    private Player player;
    private ParticleManager particleManager;

    public MenuScreen(Game g, Player player, Viewport viewport){
        game = g;
        GameData.getInstance().setPlayer(player);

        //Fix of camera and graphics
        if (viewport == null){
            fixCamera();
        }else{
            this.viewport = viewport;
            this.camera = (OrthographicCamera)viewport.getCamera();
        }

    }

    public void initiateNew(Player player) {
        this.player = player;
        initiateModel();
        initiateController();
        initiateView();
    }

    private void initiateModel() {
        if (this.particleManager == null){
            this.particleManager = new ParticleManager(player.getSettings());
        }
        this.mainMenu=new MainMenu(particleManager);
        ProximityAudioPlayer.pauseGameMusic();
    }

    private void initiateController() {
        mainMenuController=new MainMenuController(this.viewport);
        mainMenuController.setMainMenu(mainMenu);
        Gdx.input.setInputProcessor(mainMenuController);
        ScreenChanger.setListener(this);
    }

    private void initiateView() {
        batch = new ProximityBatch();
        shapeRenderer = new ProximityShapeRenderer();
        menuRenderer=new MenuRenderer(mainMenu);
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
    }

    public void screenChanged(ScreenChanger.ScreenType type) {
        switch(type) {
            case Play: ScreenCollector.setGameScreen(game, mainMenu.getMap(), GameData.getInstance().getPlayer(), viewport); break;
            default: break;
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {    }

    @Override
    public void hide() {    }

    //The disposeCollector handles disposing
    @Override
    public void dispose(){     }

}
