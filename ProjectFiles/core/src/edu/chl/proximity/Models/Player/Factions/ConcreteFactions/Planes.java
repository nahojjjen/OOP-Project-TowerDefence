package edu.chl.proximity.Models.Player.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.ShardBase;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.ChainLightning;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.FireField;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.FrostField;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.WallOfStone;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 */
public class Planes extends Faction {

    public Planes(){
        super("Planes", new Image(Constants.filePath + "Bases/base1.png")); //should also specify whay spells should be added

    }

    @Override
    public Base getNewBase(Map map) {
        return new ShardBase(map);
    }

    @Override
    public void configureSpells(Map map) {
        addSpell(new FrostField(map));
        addSpell(new WallOfStone(map));
        addSpell(new FireField(map));
        addSpell(new ChainLightning(map));
    }
}
