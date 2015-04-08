package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Controllers.GodController;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Viewers.Renderer;

/**
 * Created by Johan on 2015-04-07.
 */
public class GameScreen implements Screen{
    private Game game ;
    private SpriteBatch batch = new SpriteBatch();
    private Renderer renderer;

    public GameScreen(Game g){
        game =g ;
        renderer = new Renderer();
    }
    @Override
    public void show() {
        System.out.println("Showing game Screen");
    }

    @Override
    public void render(float delta) {
        //This method gets called every frame


        batch.begin();
        renderer.render(batch);
        batch.end();

        GodController.updateBackground();
        GodController.updateCreeps();
        GodController.updateProjectiles();
        GodController.updateTowers();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resizing game screen");
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
