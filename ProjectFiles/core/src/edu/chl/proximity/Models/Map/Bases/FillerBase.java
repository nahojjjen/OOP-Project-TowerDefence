package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-01
 *
 * A representation of the FillerBase for the Beast faction
 *
 */
public class FillerBase extends Base{
    public FillerBase(Path path, ParticleManager particleManager){
        super(path, new Image(Constants.FILE_PATH + "Bases/stackedbase.png"), particleManager.getBaseDamageEffect(), particleManager.getBaseCracksEffect());

    }
}
