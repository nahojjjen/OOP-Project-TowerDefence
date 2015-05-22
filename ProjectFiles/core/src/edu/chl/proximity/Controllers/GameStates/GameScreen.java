package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.MainController;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Viewers.Renderer;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-07
 *
 * A class for handling the GameScreen, the screen that is showed when a game is played.
 *
 * ---
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 08/04 modified by Linda Evaldsson. Added som spawning logic for testing.
 * 16/04 modified by Simon Gislen
 * 21/04 modified by Simon Gislen
 * 23/04 modified by Hanna Romer. Added ButtonPanel and PropertiesPanel.
 * 29/04 modified by Hanna Romer. Removed PropertiesPanel since it's singleton
 * 07/05 modified by Linda Evaldsson. Moved handling of ControlPanels to ControlPanelController
 */
public class GameScreen implements Screen{

    private ProximityBatch batch = new ProximityBatch();
    private ProximityShapeRenderer shapeRenderer = new ProximityShapeRenderer();
    private Renderer renderer;


    private MainController mainController;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Settings settings;

    public GameScreen(Game g, Map map, Player player, Viewport viewport){
        this.settings = player.getSettings();

        GameData.getInstance().setPlayer(player);
        this.renderer = new Renderer(map);

        fixCamera();
        //TODO fix this viewport scaling issue
        /*
        this.viewport=viewport;
        camera=(OrthographicCamera)viewport.getCamera();
        camera.setToOrtho(true);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        */

        mainController = new MainController(map, this.viewport, g);
        renderer.setControlPanels(mainController.getControlPanels());
        renderer.addControlPanel(mainController.getWavePanel());
        map.setBase(player.getFaction().getNewBase(map.getPath(), map.getParticleManager()));
        player.getFaction().configureSpells(map.getParticleManager());
        player.getFaction().resetSpellCooldowns();
        Gdx.input.setInputProcessor(mainController);

        //runDebugCode();


    }

    /**
     * Debug code that adds towers, sets gamespeed and sets resources and such, that should not be available to the player
     */
    private void runDebugCode(){

        //currentMap.addTower(new MissileTower(new Vector2(0, 0)));//cameraPointCoordinates));
        //currentMap.addTower(new SlowTower(new Vector2(300, 300)));
        //FrostField frostField = new FrostField();
        //currentMap.addTower(new BulletTower(new Vector2(400,200)));
        //currentMap.addTower(new BulletTower(new Vector2(400,300)));

        //map.getSettings().setGameSpeed(1000);
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
        renderer.render(batch, shapeRenderer);

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
        batch.dispose();
    }
}
