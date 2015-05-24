package test.edu.chl.proximity.Models.Map.Spells;

import edu.chl.proximity.Models.Map.Spells.PersistentObject;
import edu.chl.proximity.Utilities.ProximityVector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author simongislen.
 * @date 21/05/15.
 *
 *
 * A Test class for PersistentObject
 */
public class PersistentObjectTest {

    private PersistentObject persistentObject;
    private boolean flag = false;

    @Before
    public void setUp() throws Exception {
        persistentObject = new PersistentObject(new ProximityVector(5,5), null, 10) {
            @Override
            public void performEffect(int counter) {
                flag = true;
            }
        };
    }

    @Test
    public void testStart() throws Exception {
        persistentObject.start();
        assertTrue(persistentObject.isStarted());
    }

    @Test
    public void testTick() throws Exception {
        int initial = persistentObject.getCounter();
        persistentObject.start();
        persistentObject.tick();
        int after = persistentObject.getCounter();
        int expected = initial - 1;
        assertTrue(after == expected);
    }

    @Test
    public void testPerformEffect() throws Exception {
        persistentObject.performEffect(1);
        assertTrue(flag);
    }
}