package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 * The base of the Planes Faction
 */
public class ShardBase extends Base {
    public ShardBase(Path path, ParticleManager particleManager){
        super(path, new Image(Constants.FILE_PATH + "Bases/base1.png"), particleManager.getBaseDamageEffect(), particleManager.getBaseCracksEffect());

    }

}
