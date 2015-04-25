package edu.chl.proximity.Models.MenuModels;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Backgrounds.Background;

/**
 * @author Hanna Römer
 * @date 2105-04-25
 */
public class MainMenu {
    private StartButton startButton=new StartButton(new Vector2(200,200));
    private Background background;

    public MainMenu(Background background){
        this.background=background;
    }
}
