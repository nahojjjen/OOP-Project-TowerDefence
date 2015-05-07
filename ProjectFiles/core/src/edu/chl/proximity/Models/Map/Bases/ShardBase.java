package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 * The base of the Planes Faction
 */
public class ShardBase extends Base {
    public ShardBase(Map map){
        super(map, new Image(Constants.FILE_PATH + "Bases/base1.png"),map.getParticleManager().getBaseDamageEffect(), map.getParticleManager().getBaseCracksEffect());

    }

}
