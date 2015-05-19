package test.edu.chl.proximity.Models.Map.Paths;

import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.SecondTestPath;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.TestPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @date 2015-05-19.
 */
public class AllPathTest {

    @Test
    public void testGetWaypoints() throws Exception {
        Path path1 = new SecondTestPath();
        Path path2 = new FirstPath();
        Path path3 = new TestPath();

        assertTrue(path1.getWaypoints().size() > 0);
        assertTrue(path2.getWaypoint(1) != null);
        assertTrue(path3.getWaypoint(99) == path3.getWaypoint(123123123));
    }


}