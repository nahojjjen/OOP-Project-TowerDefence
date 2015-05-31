package edu.chl.proximity.Models.Player.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.FillerBase;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.BloodCarnage;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.BloodPool;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.BloodSipper;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.Sacrifice;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-01.
 *
 * A concrete faction; Beast
 */
public class Filler extends Faction {
    public Filler(){
        super("Beast", new Image(Constants.FILE_PATH + "Bases/stackedbase.png"));
    }

    @Override
    public Base getNewBase(Path path, ParticleManager particleManager) {
        return new FillerBase(path, particleManager);
    }

    @Override
    public void configureSpells(ParticleManager particleManager) {
        addSpell(new BloodCarnage(particleManager));
        addSpell(new BloodPool(particleManager));
        addSpell(new BloodSipper(particleManager));
        addSpell(new Sacrifice(particleManager));
    }
}
