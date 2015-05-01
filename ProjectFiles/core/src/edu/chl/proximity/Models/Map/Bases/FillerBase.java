package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Hanna on 2015-05-01.
 */
public class FillerBase extends Base{
    public FillerBase(){
        super(new Image(Constants.filePath + "Bases/cocobase.png"), GameData.getInstance().getMap().getParticleManager().getBaseDamageEffect(), GameData.getInstance().getMap().getParticleManager().getBaseCracksEffect());

    }
}
