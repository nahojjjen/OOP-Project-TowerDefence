package test.edu.chl.proximity.Models.Player.PersistentSave;

import edu.chl.proximity.Models.Player.PersistentSave.SaveManager;
import junit.framework.TestCase;

/**
 * @author Johan
 * @date 2015-05-29.
 */
public class SaveManagerTest extends TestCase {

    public void testSaveManager() throws Exception {
        SaveManager manager = new SaveManager();
        manager.write("test",9999f);
        manager.save(999);
        manager.loadSave(999);
        assertTrue(manager.get("test")==9999);
        assertTrue(manager.get("NonExsistantValue")==null);

    }
}