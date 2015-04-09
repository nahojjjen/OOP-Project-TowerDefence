package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import edu.chl.proximity.Controllers.BoardInputProcessor;
import edu.chl.proximity.Controllers.GodController;
import edu.chl.proximity.Models.Creeps.Triangle;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Maps.StandardMap;
import edu.chl.proximity.Models.Towers.ShootingTower;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Viewers.Renderer;

import java.awt.*;
import java.util.Random;
/**
 * Created by Johan on 2015-04-07.
 */
public class GameScreen implements Screen{
    private Game game ;
    private Map currentMap;
    private SpriteBatch batch = new SpriteBatch();
    private Renderer renderer;
    private GodController godController;

    private OrthographicCamera camera;
    private FitViewport viewport;





    public GameScreen(Game g, Map map){
        game =g ;
        currentMap = map;

        renderer = new Renderer(currentMap);
        godController = new GodController(currentMap);
        GameData.getInstance().setMap(currentMap);
        fixCamera();
        Gdx.input.setInputProcessor(new BoardInputProcessor(viewport));

    }



    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();
        

       currentMap.addTower(new ShootingTower(new Point(0, 0)));//cameraPointCoordinates));
        for(int i = 0; i < 10; i++) {
            currentMap.spawnCreep(new Triangle());

        }
    }


    @Override
    public void show() {
        System.out.println("Showing game Screen");

    }

    @Override
    public void render(float delta) {
        //This method gets called every frame
        camera.update();
        batch.setProjectionMatrix(camera.combined );

        batch.begin();
        renderer.render(batch);

        batch.end();

        godController.updateBackground();
        godController.updateCreeps();
        godController.updateProjectiles();
        godController.updateTowers();
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
