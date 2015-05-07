package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Utils.Background;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.SecondTestPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-08
 */
public class StandardMap extends Map {

    private static Path path = new SecondTestPath();


    /**
     * creates the map instance
     */
    public StandardMap() {
        super(path, new Background(null, new Image(Constants.filePath + "Backgrounds/test.jpg")), "Standard");
    }
}
