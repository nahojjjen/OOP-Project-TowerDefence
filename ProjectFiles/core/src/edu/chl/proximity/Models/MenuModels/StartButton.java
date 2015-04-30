package edu.chl.proximity.Models.MenuModels;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna R�mer
 * @date 2015-04-25
 */
public class StartButton extends BoardObject{
    private static Image image=new Image(Constants.filePath + "Buttons/ResumeButton.png");

    public StartButton(Vector2 pos){
        super(pos,image,0);
    }
}
