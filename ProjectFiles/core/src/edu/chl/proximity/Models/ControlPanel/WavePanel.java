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
 */
public class WavePanel extends BoardObject {

    private ProximityFont waves;
    private static ProximityVector position = new ProximityVector(10, 10);


    public WavePanel() {
        super(new ProximityVector(position), null, 0);
        waves = new ProximityFont(position, "Wave 0");
        waves.setSize(30);

    }

    public void updateWaves(int wave) {
        waves.setText("Wave " + wave);
    }

    public void render(ProximityBatch batch) {
        waves.draw(batch);
    }
}
