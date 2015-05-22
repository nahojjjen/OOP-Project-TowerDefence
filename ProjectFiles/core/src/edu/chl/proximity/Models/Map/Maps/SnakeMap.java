package edu.chl.proximity.Models.Map.Maps;

import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.SnakePath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan
 * @date 5/22/2015.
 */
public class SnakeMap extends Map{

    private static Path path = new SnakePath();


    /**
     * creates the map instance
     */
    public SnakeMap(ParticleManager particleManager) {
        super(path, new Background(new Image(Constants.FILE_PATH + "Backgrounds/moonBackground.png")), "Snake", particleManager);
    }
    @Override
    public String getPreviousMapName() {
        return "SmallSpiral";
    }
    public Map getNew(){
        return new SnakeMap(getParticleManager());
    }
}


