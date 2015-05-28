package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.GameOverController;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChangerListener;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.Utils.ProximityAudioPlayer;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.WonLostModels.GameOver;
import edu.chl.proximity.Models.Utils.ProximityShapeRenderer;
import edu.chl.proximity.Viewers.GameOverRenderer;


/**
 * @author Hanna Romer
 * @date 2015-05-15
 */
public class GameOverScreen implements Screen, ScreenChangerListener {

    //View
    private GameOverRenderer gameOverRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;

    //Controller
    private GameOverController gameOverController;

    //Model
    private ProximityBatch batch;
    private ProximityShapeRenderer shapeRenderer;
    private GameOver gameOver;
    private Map map;
    private Game game;
    private Player player;

    public GameOverScreen(Game g, Map map, Player player, Viewport viewport){

        //Fix of camera and graphics
        if (viewport == null){
            fixCamera();
        }else{
            this.viewport = viewport;
            this.camera = (OrthographicCamera)viewport.getCamera();
        }

    }

    public void initiateNew(Game g, Map map, Player player) {
        this.game = g;
        this.map = map;
        this.player = player;
        initiateModel();
        initiateController();
        initiateView();
        ProximityAudioPlayer.pauseGameMusic();
    }
    private void initiateModel() {
        gameOver=new GameOver(map);
    }
    private void initiateController() {
        gameOverController=new GameOverController(game, viewport, gameOver, player);
        Gdx.input.setInputProcessor(gameOverController);
        ScreenChanger.setListener(this);
    }
    private void initiateView() {
        batch = new ProximityBatch();
        shapeRenderer = new ProximityShapeRenderer();
        gameOverRenderer=new GameOverRenderer(gameOver);
    }

    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {

    }

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

    }

    @Override
    public void screenChanged(ScreenChanger.ScreenType newScreen) {
        switch(newScreen) {
            case MainMenu: ScreenCollector.setMenuScreen(game, GameData.getInstance().getPlayer(), viewport); break;
            case Play: ScreenCollector.setGameScreen(game, map.getNew(), GameData.getInstance().getPlayer(), viewport); break;
            default: break;
        }

    }
}
