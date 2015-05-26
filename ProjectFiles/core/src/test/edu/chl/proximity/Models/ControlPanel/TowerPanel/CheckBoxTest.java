package test.edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.ControlPanel.TowerPanel.CheckBox;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Linda Evaldsson
 * @date 2015-05-26
 */
public class CheckBoxTest {

    @Test
    public void testSetAsChecked() throws Exception {

        CheckBox checkbox = new CheckBox(new ProximityVector(0, 0), new StandardMap(new ParticleManager(new Settings())), "");
        checkbox.setAsChecked();
        assertTrue(checkbox.isChecked());

    }

    @Test
    public void testSetAsUnchecked() throws Exception {
        CheckBox checkbox = new CheckBox(new ProximityVector(0, 0), new StandardMap(new ParticleManager(new Settings())), "");
        checkbox.setAsUnchecked();
        assertTrue(!checkbox.isChecked());
    }
}