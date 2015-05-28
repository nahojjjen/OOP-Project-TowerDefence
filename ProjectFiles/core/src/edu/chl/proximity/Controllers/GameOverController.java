package edu.chl.proximity.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Models.WonLostModels.GameOver;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-05-15
 */
public class GameOverController implements InputProcessor{
    private Viewport viewport;
    private GameOver gameOver;
    private Player player;

    public GameOverController(Game g,Viewport v, GameOver go, Player player){
        viewport=v;
        gameOver=go;
        this.player=player;
    }

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
        Vector2 pos = viewport.unproject(new Vector2(x,y));
        ProximityVector translatedPosition = new ProximityVector(pos.x,pos.y);
        String action= gameOver.getButtonActionOnPosition(translatedPosition);
        if(action != null) {
            if (action.equals("Resume")) {
                ScreenChanger.changeScreen(ScreenChanger.ScreenType.Play);
                player.initiateNewMap();
            } else if (action.equals("MainMenu")) {
                ScreenChanger.changeScreen(ScreenChanger.ScreenType.MainMenu);
            }
        }
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
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}
