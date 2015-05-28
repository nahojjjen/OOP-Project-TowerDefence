package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna Romer
 * @date 2015-04-23
 *
 * 24/05 modified by Linda Evaldsson. Made this more general and renamed it to PropertisPanelButton. It replaces former MainMenuButton.
 * 28/05 modified by Hanna Romer. Added comments.
 */
public class PropertiesPanelButton extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Buttons/PropertiesButtonBackground.png");
    private ProximityFont font;

    /**
     * Create a new PropertiesPanelButton, with given position and text.
     * @param position Position button will be placed at.
     * @param s Text button will display
     */
    public PropertiesPanelButton(ProximityVector position, String s){
        super(position,image,0);
        float x = position.x + (getWidth() - s.length()*17) / 2f;
        float y = position.y + (getHeight() - 20) / 2f;
        font = new ProximityFont(new ProximityVector(x, y), s,24, 1,1,1);
    }

    public void render(ProximityBatch batch) {
        super.render(batch);
        font.draw(batch);
    }

}
