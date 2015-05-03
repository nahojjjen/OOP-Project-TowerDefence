package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Map.Paths.ConcretePaths.TestPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Römer
 * @date 2015-05-03
 */
public class FillerMap extends Map{
    private static Path path = new TestPath();


    /**
     * creates the map instance
     */
    public FillerMap() {
        super(path, new Background(new Image(Constants.filePath + "Backgrounds/moonBackground.png")),"FillerMap");
    }

}
