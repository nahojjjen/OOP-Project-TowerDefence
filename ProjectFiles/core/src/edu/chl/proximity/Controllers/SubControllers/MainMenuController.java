package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.MenuModels.StartButton;
import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Proximity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-04-25
 */
public class MainMenuController implements ClickHandler{
    private Background model=new Background(null);
    //private List<BoardObject> models = new ArrayList<BoardObject>();
    private MainMenu mainMenu=GameData.getInstance().getMainMenu();

    public MainMenuController() {
        model.setPosition(new Vector2(0,0));
        model.setWidth(Gdx.graphics.getWidth());
        model.setHeight(Gdx.graphics.getHeight());
    }

    @Override
    public void touchDown(Vector2 clickedPoint, int pointer, int button) {
        if(GameData.getInstance().getGame().getCurrentScreen().equals(Proximity.State.MAIN_MENU)) {
            BoardObject touchedButton = mainMenu.getButtonOnPosition(clickedPoint);
            if (touchedButton instanceof StartButton) {
                System.out.println("PRESSED START!!!!!!!!!!!!!!!!");
                mainMenu.pressedStart();
            }
        }

    }
    @Override
    public void mouseMoved(Vector2 newPosition) {

    }

}
