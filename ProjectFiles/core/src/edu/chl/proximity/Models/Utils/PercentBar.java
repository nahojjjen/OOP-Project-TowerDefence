package edu.chl.proximity.Models.Utils;

import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * A class for drawing a partly filled bar, for example a health bar
 *
 * ---
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
        text = new ProximityFont(new ProximityVector(position.x + (width/2f) - 22, position.y + 10), "",height/2, 1,1,1);

    }

    public void setText(String s) {
        text.setText(s);
    }

    public void setPercent(int newPercent) {
        if(newPercent >= 0) {
            percent = newPercent;
        } else {
            percent = 0;
        }
    }

    public int getPercent() {
        return percent;
    }

    public void render(ProximityBatch batch) {
        batch.renderRepeatedly(background, position, width, height);

        int foregroundWidth = (int) ((width / 100.0) * percent);
        if(foregroundWidth - border*2 > 0)
            foregroundWidth -= border*2;
        batch.renderRepeatedly(foreground, new ProximityVector(position.x+border, position.y+border),foregroundWidth, height - border*2);

        text.draw(batch);
    }

}
