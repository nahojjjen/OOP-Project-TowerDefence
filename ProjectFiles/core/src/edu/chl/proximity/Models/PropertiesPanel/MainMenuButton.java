package edu.chl.proximity.Models.PropertiesPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-24
 */
public class MainMenuButton extends BoardObject{
    private static Image image = new Image(Constants.filePath + "Buttons/ResumeButton.png");

    public MainMenuButton(Vector2 position){ super(position,image,0);}

}
