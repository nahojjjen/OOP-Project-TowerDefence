package edu.chl.proximity.Models.Bases;

import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Particles.ProximityEffect;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Johan on 2015-04-16.
 *
 * The base of the Planes Faction
 */
public class ShardBase extends Base {
    public ShardBase(){
        super(new Image(Constants.filePath + "Bases/base1.png"),GameData.getInstance().getMap().getParticleManager().getBaseDamageEffect(), GameData.getInstance().getMap().getParticleManager().getBaseCracksEffect());

    }

}
