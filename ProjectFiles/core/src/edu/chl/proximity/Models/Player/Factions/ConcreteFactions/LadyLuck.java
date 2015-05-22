package edu.chl.proximity.Models.Player.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.WingedBase;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.*;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 */
public class LadyLuck extends Faction{
    public LadyLuck(){
        super("Lady Luck", new Image(Constants.FILE_PATH + "Bases/wingedBase.png"));
    }

    @Override
    public Base getNewBase(Path path, ParticleManager particleManager) {
        return new WingedBase(path, particleManager);
    }

    @Override
    public void configureSpells(ParticleManager particleManager) {
        addSpell(new LifeGamble(particleManager));
        addSpell(new SpeedGamble(particleManager));
        addSpell(new CoinFlip(particleManager));
        addSpell(new PickACard(particleManager));
    }
}
