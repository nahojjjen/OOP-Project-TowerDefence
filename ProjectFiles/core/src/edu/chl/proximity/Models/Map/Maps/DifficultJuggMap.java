package edu.chl.proximity.Models.Map.Maps;


import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.SmallSpiralPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan
 * @date 5/22/2015.
 */
public class DifficultJuggMap extends Map{

    private static Path path = new SmallSpiralPath();


    /**
     * creates the map instance
     */
    public DifficultJuggMap(ParticleManager particleManager) {
        super(path, new Background(new Image(Constants.FILE_PATH + "Backgrounds/moonBackground.png")), "Standard", particleManager);
    }

    public Map getNew(){
        return new DifficultJuggMap(getParticleManager());
    }
}
