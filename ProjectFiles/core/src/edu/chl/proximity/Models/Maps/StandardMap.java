package edu.chl.proximity.Models.Maps;

import edu.chl.proximity.Models.Backgrounds.Background;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Paths.ConcretePaths.FirstPath;
import edu.chl.proximity.Models.Paths.ConcretePaths.SecondTestPath;
import edu.chl.proximity.Models.Paths.ConcretePaths.TestPath;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson and Johan Swanberg (Group work)
 * @date 2015-04-08
 */
public class StandardMap extends Map {

    private static Path path = new SecondTestPath();


    /**
     * creates the map instance
     */
    public StandardMap() {
        super(path, new Background(new Image(Constants.filePath + "Backgrounds/test.jpg")));
    }
}
