package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Hanna on 2015-05-01.
 */
public class FillerBase extends Base{
    public FillerBase(Map map){
        super(map, new Image(Constants.FILE_PATH + "Bases/cocobase.png"), map.getParticleManager().getBaseDamageEffect(), map.getParticleManager().getBaseCracksEffect());

    }
}
