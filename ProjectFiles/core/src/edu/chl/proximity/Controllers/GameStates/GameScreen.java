package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.chl.proximity.Controllers.MainController;
import edu.chl.proximity.Models.ButtonsPanel.ButtonPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Players.Player;
import edu.chl.proximity.Models.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Models.Spells.ConcreteSpells.FrostField;
import edu.chl.proximity.Models.Towers.SlowTower;
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
 * 23/04 modified by Hanna Römer. Added ButtonPanel and PropertiesPanel.
 */
public class GameScreen implements Screen{
    private Game game;
    private Map currentMap;
    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Renderer renderer;
    private ControlPanel controlPanel;
    private ButtonPanel buttonPanel;
    private PropertiesPanel propertiesPanel;
    private MainController mainController;
    private OrthographicCamera camera;
    private FitViewport viewport;

    public GameScreen(Game g, Map map, Player player){

        game = g;
        currentMap = map;
        GameData.getInstance().setMap(currentMap);

        controlPanel = new ControlPanel(); //Must be set after map is set in GameData
        buttonPanel=new ButtonPanel();
        propertiesPanel=new PropertiesPanel();

        GameData.getInstance().setPlayer(player);
        renderer = new Renderer();
        fixCamera();
        mainController = new MainController(viewport);

        renderer.setControlPanel(controlPanel);
        renderer.setButtonPanel(buttonPanel);
        renderer.setPropertiesPanel(propertiesPanel);

        mainController.setControlPanel(controlPanel);
        mainController.setButtonPanel(buttonPanel);
        mainController.setPropertiesPanel(propertiesPanel);

        GameData.getInstance().setButtonPanel(buttonPanel);
        GameData.getInstance().setPropertiesPanel(propertiesPanel);

        shapeRenderer.setAutoShapeType(true);

        map.setBase(player.getFaction().getNewBase());
        Gdx.input.setInputProcessor(mainController);

        runDebugCode();


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
        GameData.getInstance().setGameSpeed(1);
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
        for (int i=0; i<GameData.getInstance().getGameSpeed(); i++){
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
