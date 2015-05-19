package edu.chl.proximity.Utilities;

import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * 08/05 modified by Linda Evaldsson. Changed percentBar to use textures (images).
 */
public class PercentBar {

    private ProximityVector position;
    int width;
    int height;
    private int border = 1;
    private int percent = 100;
    private Image foreground;
    private Image background;
    private ProximityFont text;

    public PercentBar(ProximityVector position, int width, int height, Image foreground, Image background) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.background = background;
        this.foreground = foreground;
        text = new ProximityFont(new ProximityVector(position.x + (width/2) - 8, position.y + 8), "");
        text.scale(0.1f);

    }

    public void setText(String s) {
        text.setText(s);
    }

    public void setPercent(int newPercent) {
        if(newPercent >= 0) {
            percent = newPercent;
        } else {
            percent = 1;
        }
    }

    public int getPercent() {
        return percent;
    }

    public void render(ProximityBatch batch) {
        batch.renderRepeatedly(background, position, width, height);

        int foregroundWidth = (int) ((width / 100.0) * percent);
        batch.renderRepeatedly(foreground, new ProximityVector(position.x+border, position.y+border),foregroundWidth - border*2, height - border*2);

        text.draw(batch);
    }

}
