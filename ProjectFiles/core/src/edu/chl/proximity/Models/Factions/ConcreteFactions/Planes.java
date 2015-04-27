package edu.chl.proximity.Models.Factions.ConcreteFactions;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Bases.Base;
import edu.chl.proximity.Models.Bases.ShardBase;
import edu.chl.proximity.Models.Factions.Faction;
import edu.chl.proximity.Models.Spells.ConcreteSpells.ChainLightning;
import edu.chl.proximity.Models.Spells.ConcreteSpells.FireField;
import edu.chl.proximity.Models.Spells.ConcreteSpells.FrostField;
import edu.chl.proximity.Models.Spells.ConcreteSpells.WallOfStone;

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
        addSpell(new FrostField(null));
        addSpell(new WallOfStone(null));
        addSpell(new FireField(null));
        addSpell(new ChainLightning(null));
    }
}
