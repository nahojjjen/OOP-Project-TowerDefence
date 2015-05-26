package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.ZigZagPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan
 * @date 5/22/2015.
 */
public class ZigZagMap extends Map {
    private static Path path = new ZigZagPath();


    /**
     * creates the map instance
     */
    public ZigZagMap(ParticleManager particleManager) {
        super(path, new Background(new Image(Constants.FILE_PATH + "Backgrounds/smoothBackground.png")), "ZigZag", particleManager);
    }

    @Override
    public String getPreviousMapName() {
        return "DifficultJugg";
    }

    public Map getNew(){
        return new ZigZagMap(getParticleManager());
    }
}
