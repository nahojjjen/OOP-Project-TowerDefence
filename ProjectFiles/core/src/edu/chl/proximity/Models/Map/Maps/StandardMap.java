package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
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
    public StandardMap(ParticleManager particleManager) {
        super(path, new Background(new Image(Constants.FILE_PATH + "Backgrounds/warmBackground.png")), "Standard", particleManager);
    }

    public Map getNew(){
        return new StandardMap(getParticleManager());
    }
}
