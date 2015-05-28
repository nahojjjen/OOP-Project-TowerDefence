package edu.chl.proximity.Models.Map.MouseOver;

import com.badlogic.gdx.Gdx;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linda Evaldsson
 * @date 2015-05-22
 *
 * A class for handling a MouseOverBox that can be temporarily shown if the mouse hovers over an item.
 * If the mouse leaves the box the box disappears.
 *
 * 26/05 modified by Linda Evaldsson. Added automatic row changing and fixed so only one Box can be displayed at any one time.
 */
public class MouseOverBox extends BoardObject {


    private boolean enabled = false;
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/InfoBackground.png");
    private List<ProximityFont> infoTextList = new ArrayList<ProximityFont>();

    //Storing info that was sent to the constructor to avoid doing calculations when box is created
    private String storedInfo;

    public MouseOverBox(int width, String info) {
        super(new ProximityVector(0, 0), background, 0, width, 0);
        storedInfo = info;

    }

    private boolean fontsInitiated() {
        return infoTextList.size() > 0;
    }

    /**
     * A method that creates the fonts that is to be displayed in the MouseOverBox.
     * If the text goes beyond the width of the box a new row is initiated.
     */
    private void initiateFonts() {
        int signsAllowedOnARow = getWidth()/6;

        String[] parts = storedInfo.split("\n");
        for(int i = 0; i < parts.length; i++) {
            String part = parts[i];

            while(part.length() > signsAllowedOnARow) {
                String[] partsOfPart = part.split(" ");
                String rowToAdd = "";
                part = "";
                boolean rowDone = false;
                for(int k = 0; k < partsOfPart.length; k++) {
                    if(rowToAdd.length() + partsOfPart[k].length() < signsAllowedOnARow && !rowDone) {
                        rowToAdd += partsOfPart[k] + " ";
                    } else {
                        rowDone = true;
                        part += partsOfPart[k] + " ";
                    }
                }
                ProximityFont font = new ProximityFont(new ProximityVector(getPosition().x + 5, getPosition().y + 5 + (i*15)), rowToAdd,11, 1,1,1);
                infoTextList.add(font);
            }
            ProximityFont font = new ProximityFont(new ProximityVector(getPosition().x + 5, getPosition().y + 5 + (i*15)), part,11, 1,1,1);
            infoTextList.add(font);

        }
        setHeight(20 + infoTextList.size()*13);
    }

    public boolean containsPoint(ProximityVector point) {
        return super.containsPoint(point) && enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        if(!fontsInitiated())
            initiateFonts();
        if(!enabled) {
            setPosition(new ProximityVector(Gdx.input.getX() - getWidth()/2, Gdx.input.getY() - getHeight() + 10));
            for(int i = 0; i < infoTextList.size(); i++) {
                infoTextList.get(i).setPosition(new ProximityVector(getPosition().x + 5, getPosition().y + 5 + (i*15)));
            }

            InformationCollector.displayBox(this);
        }
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
