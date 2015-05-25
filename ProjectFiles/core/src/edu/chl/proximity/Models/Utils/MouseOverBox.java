package edu.chl.proximity.Models.Utils;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-05-22
 */
public class MouseOverBox extends BoardObject {


    private boolean enabled = false;
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/InfoBackground.png");
    private List<ProximityFont> infoTextList = new ArrayList<ProximityFont>();

    public MouseOverBox(ProximityVector position, int width, int height, String info) {
        super(position, background, 0, width, height);

        String[] parts = info.split("\n");
        for(int i = 0; i < parts.length; i++) {
            infoTextList.add(new ProximityFont(new ProximityVector(position.x + 5, position.y + 5 + (i*15)), parts[i]));
        }
        InformationCollector.addBox(this);

    }

    public boolean containsPoint(ProximityVector point) {
        return super.containsPoint(point) && enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void render(ProximityBatch batch) {
        if(enabled) {
            batch.renderRepeatedly(getImage(), getPosition(), getWidth(), getHeight());
            for(ProximityFont font : infoTextList) {
                font.draw(batch);
            }
        }
    }


}
