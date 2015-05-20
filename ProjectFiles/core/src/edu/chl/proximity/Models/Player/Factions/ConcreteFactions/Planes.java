package edu.chl.proximity.Models.Player.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.ShardBase;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.ChainLightning;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.FireField;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.FrostField;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.WallOfStone;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan Swanberg
 * @date 2015-04-16
 *
 */
public class Planes extends Faction {

    public Planes(){
        super("Planes", new Image(Constants.FILE_PATH + "Bases/base1.png")); //should also specify whay spells should be added

    }

    @Override
    public Base getNewBase(Path path, ParticleManager particleManager) {
        return new ShardBase(path, particleManager);
    }

    @Override
    public void configureSpells(ParticleManager particleManager) {
        addSpell(new FrostField(particleManager));
        addSpell(new WallOfStone(particleManager));
        addSpell(new FireField(particleManager));
        addSpell(new ChainLightning(particleManager));
    }
}
