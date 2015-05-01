package edu.chl.proximity.Models.Player.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.FillerBase;
import edu.chl.proximity.Models.Map.Bases.ShardBase;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.ChainLightning;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.FireField;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.FrostField;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.WallOfStone;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Hanna on 2015-05-01.
 */
public class Filler extends Faction {
    public Filler(){
        super("Filler", new Image(Constants.filePath + "Bases/cocoBase.png"));
    }

    @Override
    public Base getNewBase() {
        return new FillerBase();
    }

    @Override
    public void configureSpells() {
        addSpell(new FrostField());
        addSpell(new WallOfStone());
        addSpell(new FireField());
        addSpell(new ChainLightning());
    }
}
