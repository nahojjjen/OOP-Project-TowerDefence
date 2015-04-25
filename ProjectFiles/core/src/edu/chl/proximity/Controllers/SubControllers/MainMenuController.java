package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.Backgrounds.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Holdables.Holdable;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.MenuModels.StartButton;

/**
 * @author Hanna Römer
 * @date 2015-04-25
 */
public class MainMenuController implements ClickHandler{
    private Background model=new Background(null);
    private MainMenu mainMenu=new MainMenu();

    public MainMenuController() {
        model.setPosition(new Vector2(0,0));
        model.setWidth(Gdx.graphics.getWidth());
        model.setHeight(Gdx.graphics.getHeight());
    }
    public BoardObject getModel() {
        return model;
    }
    @Override
    public void touchDown(Vector2 clickedPoint, int pointer, int button) {
        BoardObject touchedButton=mainMenu.getButtonOnPosition(clickedPoint);
        if(touchedButton instanceof StartButton){
            System.out.println("PRESSED START!!!!!!!!!!!!!!!!");
            mainMenu.pressedStart();
        }

    }
    @Override
    public void mouseMoved(Vector2 newPosition) {

    }

}
