package edu.chl.proximity.Controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.MenuModels.StartButton;
import edu.chl.proximity.Utilities.ProximityVector;


/**
 * @author Hanna Romer
 * @date 2015-05-01
 *
 * A class (controller) for the Main menu screen, it handles everything on the Main menu Screen.
 * It handles input and checks if a button is pressed.
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
        ProximityVector translatedPosition = new ProximityVector(pos.x,pos.y);
        BoardObject touchedButton=mainMenu.getButtonOnPosition(translatedPosition);
        if(touchedButton instanceof StartButton){
            mainMenu.pressedStart();
            ScreenChanger.changeScreen(ScreenChanger.ScreenType.Play);


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
