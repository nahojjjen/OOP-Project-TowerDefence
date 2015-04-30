package edu.chl.proximity.Models.Player.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.ShardBase;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.ChainLightning;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.FireField;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.FrostField;
import edu.chl.proximity.Models.Player.Spells.ConcreteSpells.WallOfStone;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 */
public class Planes extends Faction {

    public Planes(){
        super(); //should also specify whay spells should be added
    }

    @Override
    public Base getNewBase() {
        return new ShardBase();
    }

    @Override
    public void configureSpells() {
        addSpell(new FrostField());
        addSpell(new WallOfStone());
        addSpell(new FireField());
        addSpell(new ChainLightning());
    }
}
