package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 * Represents the  visuals for a properties button
 */
public class PropertiesButton extends BoardObject{
    private static Image image=new Image(Constants.filePath + "Buttons/PauseButton.png");
    private static int height=50;
    private static int width=50;

    public PropertiesButton(Vector2 position){ super(position,image,0,width,height);}

}
