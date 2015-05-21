package edu.chl.proximity.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Controllers.GameStates.MenuScreen;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.MenuModels.StartButton;
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
    private Game game;
    private Player player;

    public GameOverController(Game g,Viewport v, GameOver go, Player player){
        viewport=v;
        gameOver=go;
        game=g;
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
        if(action.equals("Resume")){
            game.setScreen(new GameScreen(game,gameOver.getMap().getNew(),player,viewport));
        }else if(action.equals("MainMenu")){
            game.setScreen(new MenuScreen(game,player,viewport));
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
