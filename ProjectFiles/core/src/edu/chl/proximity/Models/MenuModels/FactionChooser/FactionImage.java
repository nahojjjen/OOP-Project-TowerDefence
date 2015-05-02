package edu.chl.proximity.Models.MenuModels.FactionChooser;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;

/**
 * @author Hanna Römer
 * @date 2015-05-01
 */
public class FactionImage extends BoardObject{
    private static Image image= null;

    public FactionImage(Vector2 position){
        super(position,image,180);
    }

}
