package edu.chl.proximity.Models.Maps;

import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Paths.ConcretePaths.TestPath;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Linda on 2015-04-08.
 */
public class StandardMap extends Map {

    private static Path path = new TestPath();
    private static Image backgroundImage = new Image(Constants.filePath + "Backgrounds/test.jpg");


    /**
     * creates the map instance
     */
    public StandardMap() {
        super(path, backgroundImage);
    }
}
