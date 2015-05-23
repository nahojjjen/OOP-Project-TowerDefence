package test.edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.ControlPanel.ResourceDisplayerCollection;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Floompa on 2015-05-23.
 */
public class ResourceDisplayerCollectionTest {

    @Test
    public void testUpdateResourcesAndGetResources() throws Exception {
        ProximityVector defaultVector = new ProximityVector(0, 0);
        ResourceDisplayerCollection resourceDisplayerCollection = new ResourceDisplayerCollection(defaultVector, 0f, 12, ResourceDisplayerCollection.Direction.Horizontal);

        assertTrue(resourceDisplayerCollection.getResources() == null);

        Resources resources = new Resources(10, 20, 30);
        resourceDisplayerCollection.updateResources(resources);

        Resources gottenResources = resourceDisplayerCollection.getResources();
        assertTrue(gottenResources.getPoints() == 10);
        assertTrue(gottenResources.getLines() == 20);
        assertTrue(gottenResources.getPolygons() == 30);

        resourceDisplayerCollection.updateResources(null);
        gottenResources = resourceDisplayerCollection.getResources();
        assertTrue(gottenResources.getPoints() == 10);
        assertTrue(gottenResources.getLines() == 20);
        assertTrue(gottenResources.getPolygons() == 30);

    }


}