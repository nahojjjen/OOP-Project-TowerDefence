package test.edu.chl.proximity.Models.Maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import edu.chl.proximity.Utilities.GdxTestRunner;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;

/**
 *   @author Simon Gislen
 *   @date 04/05/2015
 * Test class for map
 *
 */
@RunWith(GdxTestRunner.class)
public class MapTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Gdx.gl = mock(GL20.class);
    }

    @Test
    public void testAddCreep() throws Exception {

        Texture t = new Texture("core/assets/Backgrounds/test.jpg");
        System.out.print(t);
    }

    public void testAddProjectile() throws Exception {

    }

    public void testAddTower() throws Exception {

    }
}