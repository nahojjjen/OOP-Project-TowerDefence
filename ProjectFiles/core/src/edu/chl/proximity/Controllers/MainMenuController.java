package edu.chl.proximity.Controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.MenuModels.StartButton;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Hanna Römer
 * @date 2015-05-01
 */
public class MainMenuController implements InputProcessor{
    private MainMenu mainMenu;
    private Viewport viewport;

    public MainMenuController(Viewport viewport){
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
        BoardObject touchedButton=mainMenu.getButtonOnPosition(pos);
        if(touchedButton instanceof StartButton){
            mainMenu.pressedStart(viewport);
        }else if(touchedButton instanceof FactionChooser){
            mainMenu.pressedFactionChooser(pos);
        }else if(touchedButton instanceof MapSelect){
            mainMenu.pressedMap(pos);
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
