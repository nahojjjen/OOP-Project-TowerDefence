package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-21
 *
 * A class for displaying resources with an image
 */
public class ResourceDisplayer {

    private ProximityVector position;
    private Image image;
    private ProximityFont font;

    public ResourceDisplayer(Image image, ProximityVector position) {
        this.position = position;
        this.image = image;
        font = new ProximityFont(new ProximityVector(position.x + 20, position.y+2), "",12, 1,1,1);
    }

    public ProximityFont getFont() {
        return font;
    }

    public void render(ProximityBatch batch) {
        batch.render(image, position, 0);
        font.draw(batch);
    }


}
