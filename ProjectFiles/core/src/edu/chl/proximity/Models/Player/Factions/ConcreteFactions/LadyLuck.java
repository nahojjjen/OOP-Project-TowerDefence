package edu.chl.proximity.Models.Player.Factions.ConcreteFactions;

import edu.chl.proximity.Models.Map.Bases.Base;
import edu.chl.proximity.Models.Map.Bases.WingedBase;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.CoinFlip;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.LifeGamble;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.PickACard;
import edu.chl.proximity.Models.Map.Spells.ConcreteSpells.SpeedGamble;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 *
 * A concrete faction; Lady Luck
 */
public class LadyLuck extends Faction{
    public LadyLuck(){
        super("Lady Luck", new Image(Constants.FILE_PATH + "Bases/wingedbase.png"));
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
