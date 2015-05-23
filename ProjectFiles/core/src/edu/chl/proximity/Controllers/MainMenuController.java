package edu.chl.proximity.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.MenuModels.StartButton;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.ProximityAudioPlayer;
import edu.chl.proximity.Utilities.ProximityVector;


/**
 * @author Hanna Romer
 * @date 2015-05-01
 */
public class MainMenuController implements InputProcessor{
    private MainMenu mainMenu;
    private Viewport viewport;
    private Game game;

    public MainMenuController(Game game,Viewport viewport){
        this.game=game;
        this.viewport=viewport;
    }

    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu=mainMenu;
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
        Vector2 pos = viewport.unproject(new Vector2(x,y));
        ProximityVector translatedPosition = new ProximityVector(pos.x,pos.y);
        BoardObject touchedButton=mainMenu.getButtonOnPosition(translatedPosition);
        if(touchedButton instanceof StartButton){
            mainMenu.pressedStart();
            game.setScreen(new GameScreen(game,mainMenu.getMap(),GameData.getInstance().getPlayer(),viewport));
            ProximityAudioPlayer.playGameMusic();
        }else if(touchedButton instanceof FactionChooser){
            mainMenu.pressedFactionChooser(translatedPosition);
        }else if(touchedButton instanceof MapSelect){
            mainMenu.pressedMap(translatedPosition);
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
