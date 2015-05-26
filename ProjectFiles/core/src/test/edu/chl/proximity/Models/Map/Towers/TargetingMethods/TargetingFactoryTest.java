package test.edu.chl.proximity.Models.Map.Towers.TargetingMethods;

import edu.chl.proximity.Models.Map.Towers.TargetingMethods.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Johan on 2015-05-19.
 */
public class TargetingFactoryTest {

    @Test
    public void testGetters() throws Exception {
        TargetingFactory fac = new TargetingFactory();
        TargetingMethod first= fac.getTargetFirst();
        TargetingMethod second = fac.getTargetClosest();
        TargetingMethod third = fac.getTargetLast();

        assertTrue(first != null && first instanceof TargetFirst);
        assertTrue(second != null && second instanceof TargetClosest);
        assertTrue(third != null && third instanceof TargetLast);
    }

}