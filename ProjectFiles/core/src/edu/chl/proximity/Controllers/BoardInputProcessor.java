package edu.chl.proximity.Controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Linda on 2015-04-09.
 */
public class BoardInputProcessor implements InputProcessor {

    Viewport viewport;

    public BoardInputProcessor(Viewport v) {
        viewport = v;
    }

    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        //Calculates the real coordinates from the scaled coordinates
        Vector2 tmp = viewport.unproject(new Vector2(x, y));
        System.out.println("Mouse x: " + (int)tmp.x + " Mouse y: " + (int)tmp.y);

        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
       //ystem.out.println("Test");
        return true;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}