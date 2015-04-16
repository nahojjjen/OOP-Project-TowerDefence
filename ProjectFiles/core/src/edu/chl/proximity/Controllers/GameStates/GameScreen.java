package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.chl.proximity.Controllers.BoardInputProcessor;
import edu.chl.proximity.Controllers.MainController;
import edu.chl.proximity.Controllers.SubControllers.WaveController;
import edu.chl.proximity.Models.CreepGenerator.StandardGenerator;
import edu.chl.proximity.Models.Factions.Faction;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Towers.BulletTower;
import edu.chl.proximity.Models.Towers.MissileTower;
import edu.chl.proximity.Viewers.Renderer;

/**
 * Created by Johan on 2015-04-07. Group work with Linda
 * revised by Simon Gislen 16/04
 */
public class GameScreen implements Screen{
    private Game game;
    private Map currentMap;
    private SpriteBatch batch = new SpriteBatch();
    private Renderer renderer;
    private MainController mainController;
    private OrthographicCamera camera;
    private FitViewport viewport;



    private WaveController waveController;

    public GameScreen(Game g, Map map, Faction faction){

        game =g ;
        currentMap = map;
        GameData.getInstance().setMap(currentMap);
        GameData.getInstance().setFaction(faction);
        renderer = new Renderer();
        mainController = new MainController();

        map.setBase(faction.getNewBase());
        fixCamera();
        Gdx.input.setInputProcessor(new BoardInputProcessor(viewport));

        waveController = new WaveController();

        runDebugCode();

    }


private void runDebugCode(){

    //currentMap.addTower(new MissileTower(new Vector2(0, 0)));//cameraPointCoordinates));
    //currentMap.addTower(new BulletTower(new Vector2(400,200)));
    currentMap.addTower(new BulletTower(new Vector2(400,300)));
    GameData.getInstance().setGameSpeed(10);
}
    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();



    }


    @Override
    public void show() {
        System.out.println("Showing game Screen");

    }

    @Override
    public void render(float delta) {
        //This method gets called every frame
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        renderer.render(batch);

        batch.end();
        for (int i=0; i<GameData.getInstance().getGameSpeed(); i++){
            mainController.updateAllControllers();
            waveController.update();
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
