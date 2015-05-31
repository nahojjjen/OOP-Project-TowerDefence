package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.FillerPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-03
 *
 * A class for handling a concrete map.
 */
public class FillerMap extends Map{
    private static Path path = new FillerPath();


    /**
     * creates the map instance
     */
    public FillerMap(ParticleManager particleManager) {
        super(path, new Background(new Image(Constants.FILE_PATH + "Backgrounds/moonBackground.png")),"Filler", particleManager);
    }
    @Override
    public String getPreviousMapName() {
        return "Standard";
    }
    public Map getNew(){
        return new FillerMap(getParticleManager());
    }

}
