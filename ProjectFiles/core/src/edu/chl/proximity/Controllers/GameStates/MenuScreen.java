package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.chl.proximity.Controllers.MainController;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Players.Player;
import edu.chl.proximity.Viewers.MenuRenderer;

/**
 * @author Johan Swanberg
 * @date 2015-04-07
 *
 * A class for handling the MenuScreen, the screen for the menu
 *
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 25/04 modified by Hanna Römer. Added Game,MainMenu, SpriteBatch, ShapeRenderer,MenuRenderer,MainController, OrthographicCamera and FitViewport
 */
public class MenuScreen implements Screen {

    private Game game;
    private Stage stage;
    private MainMenu mainMenu;
    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private MenuRenderer menuRenderer;
    private MainController mainController;
    private OrthographicCamera camera;
    private FitViewport viewport;




    public MenuScreen(Game g, MainMenu mainMenu, Player player){
        this.game = g;
        this.mainMenu=mainMenu;
        GameData.getInstance().setMainMenu(mainMenu);
        GameData.getInstance().setPlayer(player);
        menuRenderer=new MenuRenderer();
        fixCamera();
        mainController=new MainController(viewport);
        shapeRenderer.setAutoShapeType(true);
        Gdx.input.setInputProcessor(mainController);

        //stage = new Stage();
        //Gdx.input.setInputProcessor(stage);

       //needs a skin or similar // -   TextButton playBtn = new TextButton("Play", new ImageTextButton.ImageTextButtonStyle());
        //playBtn.setPosition(300,300);
       // playBtn.setSize(100, 100);

       // stage.addActor(playBtn);
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

        for (int i=0; i<GameData.getInstance().getGameSpeed(); i++){
            mainController.updateAllControllers();
        }
        //stage.act(delta);
        //stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
