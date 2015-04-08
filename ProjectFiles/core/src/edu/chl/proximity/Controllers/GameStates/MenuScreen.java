package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Johan on 2015-04-07.
 */
public class MenuScreen implements Screen {

    private Game game;
    private Stage stage;


    public MenuScreen(Game g){
        this.game = g;
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

       //needs a skin or similar // -   TextButton playBtn = new TextButton("Play", new ImageTextButton.ImageTextButtonStyle());
        //playBtn.setPosition(300,300);
       // playBtn.setSize(100, 100);

       // stage.addActor(playBtn);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        System.out.println("rendering menu");

        stage.act(delta);
        stage.draw();
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
