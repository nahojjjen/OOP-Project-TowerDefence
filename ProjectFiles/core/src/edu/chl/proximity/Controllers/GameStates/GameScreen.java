package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.MainController;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChangerListener;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.ProximityAudioPlayer;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityShapeRenderer;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Viewers.GameRenderer;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-07
 *
 * A class for handling the GameScreen, the screen that handles the logic when the game is playing.
 *
 * ---
 * 08/04 Modified by Johan Swanberg. Switch to ScreenType from GameState.
 * 08/04 modified by Linda Evaldsson. Added som spawning logic for testing.
 * 16/04 modified by Simon Gislen
 * 21/04 modified by Simon Gislen
 * 23/04 modified by Hanna Romer. Added ButtonPanel and PropertiesPanel.
 * 29/04 modified by Hanna Romer. Removed PropertiesPanel since it's singleton
 * 07/05 modified by Linda Evaldsson. Moved handling of ControlPanels to ControlPanelController
 */
public class GameScreen implements Screen, ScreenChangerListener{

    //View/Renderer
    private GameRenderer gameRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;

    //Controller
    private MainController mainController;

    //Model
    private ProximityBatch batch;
    private ProximityShapeRenderer shapeRenderer;
    private Game game;
    private Map map;
    private Settings settings;
    private Player player;

    public GameScreen(Game g, Map map, Player player, Viewport viewport){
        this.player = player;
        this.settings = player.getSettings();
        this.game = g;
        this.map = map;

        //Fix of camera and graphics
        if (viewport == null){
            fixCamera();
        }else{
            this.viewport = viewport;
            this.camera = (OrthographicCamera)viewport.getCamera();
        }
    }
    public void initiateNew(Map map, Player player) {
        this.map = map;
        map.getParticleManager().clearAllParticles();
        this.player = player;

        initiateModel();
        initiateController();
        initiateView();
        ProximityAudioPlayer.playGameMusic();
    }

    private void initiateController() {
        mainController = new MainController(map, this.viewport);
        mainController.initiateNewMap(map);
        Gdx.input.setInputProcessor(mainController);
        ScreenChanger.setListener(this);
    }

    private void initiateModel() {
        map.setBase(player.getFaction().getNewBase(map.getPath(), map.getParticleManager()));
        player.getFaction().configureSpells(map.getParticleManager());
    }

    private void initiateView() {
        batch = new ProximityBatch();
        shapeRenderer = new ProximityShapeRenderer();
        this.gameRenderer = new GameRenderer(map);
        gameRenderer.setControlPanels(mainController.getControlPanels());
        gameRenderer.addControlPanel(mainController.getWavePanel());
    }


    /**
     * Attatches a camera object that views the game-data and interprets it as visual information, and a viewport
     * that scales this information to fit the screen. These objects are part of the LibGDX library.
     */
    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();
    }



    @Override
    public void show() {    }

    @Override
    public void render(float delta) {
        //This method gets called every frame
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        gameRenderer.render(batch, shapeRenderer);

        batch.end();
        for (int i=0; i<settings.getGameSpeed(); i++){
            mainController.updateAllControllers();
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {    }

    @Override
    public void hide() {    }

    @Override
    public void dispose() {    }

    @Override
    public void screenChanged(ScreenChanger.ScreenType newScreen) {
        switch(newScreen) {
            case MainMenu: ScreenCollector.setMenuScreen(game, GameData.getInstance().getPlayer(), viewport); break;
            case GameOver: ScreenCollector.setGameOverScreen(game, map, GameData.getInstance().getPlayer(), viewport); break;
            default: break;
        }
    }
}
