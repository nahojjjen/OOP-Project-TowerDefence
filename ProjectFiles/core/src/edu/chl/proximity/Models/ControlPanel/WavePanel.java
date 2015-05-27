package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-22
 *
 * A playing for displaying what wave is currently attacking
 *
 * 24/05 modified by Linda Evaldsson. Added win condition display.
 */
public class WavePanel extends BoardObject {

    private ProximityFont waves;
    private static ProximityVector position = new ProximityVector(10, 10);


    public WavePanel() {
        super(new ProximityVector(position), null, 0);
        waves = new ProximityFont(position, "Wave 0",30, 1,1,1);

    }

    public void updateWaves(int wave, int winCondition) {
        if (wave < 0)
            wave = 0;
        if(wave < winCondition) {
            waves.setText("Wave " + wave);
        } else {
            waves.setText("Wave " + wave + " - map won!");
        }
    }

    /**
     * This method is only for testing and returns the text displayed
     * @return the text displayed
     */
    public String getWaveText() {
        return waves.getText();
    }

    public void render(ProximityBatch batch) {
        waves.draw(batch);
    }
}
