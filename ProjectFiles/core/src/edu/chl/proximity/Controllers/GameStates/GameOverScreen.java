package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.GameOverController;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.WonLostModels.GameOver;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityShapeRenderer;
import edu.chl.proximity.Viewers.GameOverRenderer;


/**
 * @author Hanna Romer
 * @date 2015-05-15
 */
public class GameOverScreen implements Screen {
    private ProximityBatch batch = new ProximityBatch();
    private ProximityShapeRenderer shapeRenderer = new ProximityShapeRenderer();
    private GameOverRenderer gameOverRenderer;

    private GameOverController gameOverController;
    private GameOver gameOver;

    private OrthographicCamera camera;
    private Viewport viewport;

    public GameOverScreen(Game g, Map map, Player player){
        GameData.getInstance().setPlayer(player);
        fixCamera();
        gameOver=new GameOver(map,player,viewport);
        gameOverRenderer=new GameOverRenderer(gameOver);
        gameOverController=new GameOverController(g,viewport,gameOver,player);
        Gdx.input.setInputProcessor(gameOverController);
    }

    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {}

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    public void render(float delta) {
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        gameOverRenderer.render(batch);
        batch.end();

        //stage.act(delta);
        //stage.draw();
    }
}
