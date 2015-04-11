package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.chl.proximity.Controllers.BoardInputProcessor;
import edu.chl.proximity.Controllers.GodController;
import edu.chl.proximity.Models.Creeps.Triangle;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Towers.ShootingTower;
import edu.chl.proximity.Viewers.Renderer;

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
        GameData.getInstance().setMap(currentMap);
        renderer = new Renderer();
        godController = new GodController();

        fixCamera();
        Gdx.input.setInputProcessor(new BoardInputProcessor(viewport));

        map.addCreep(new Triangle());

    }



    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();


        currentMap.addTower(new ShootingTower(new Vector2(0, 0)));//cameraPointCoordinates));

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

        godController.updateAllControllers();
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
