package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 * The base of the Planes Faction
 */
public class ShardBase extends Base {
    public ShardBase(){
        super(new Image(Constants.filePath + "Bases/base1.png"),GameData.getInstance().getMap().getParticleManager().getBaseDamageEffect(), GameData.getInstance().getMap().getParticleManager().getBaseCracksEffect());

    }

}
