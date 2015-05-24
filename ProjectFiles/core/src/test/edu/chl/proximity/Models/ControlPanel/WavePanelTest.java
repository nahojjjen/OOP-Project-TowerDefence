package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.WavePanel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 */
public class WavePanelTest {

    @Test
    public void testUpdateWaves() throws Exception {
        WavePanel wavePanel = new WavePanel();
        wavePanel.updateWaves(30, 30);
        assertEquals(wavePanel.getWaveText(), "Wave 30 - map won!");

        wavePanel.updateWaves(0, 30);
        assertEquals(wavePanel.getWaveText(), "Wave 0");

        wavePanel.updateWaves(-50, 30);
        assertEquals(wavePanel.getWaveText(), "Wave 0");
    }
}