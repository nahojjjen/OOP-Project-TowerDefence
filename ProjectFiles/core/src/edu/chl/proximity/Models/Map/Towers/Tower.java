package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Holdables.Holdable;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;


/**
 * @author Johan Swanberg
 * @date 2015-04-02
 *
 * ---
 * 08/04 modified by Linda Evaldsson. Refactoring to Tower instead of AbstracTower. Removed Projectile as parameter for the constructor.
 * 03-05-2015 Modified by Simon Gislen. Tiny spell check.
 * 04-05-2015 Modified by Simon Gislen. Moved projectile functionality to ShootingTower
 * 08/05 modified by Hanna Römer. Added name and Tower Upgrade
 * 10/05 modified by Hanna Römer. Removed class variable upgrade, made getUpgrade abstract.
 */
public abstract class Tower extends BoardObject implements Holdable, Cloneable{
    protected Resources cost;
    protected double range;
    private String name;
    private Tower upgrade;
    private ParticleManager particleManager;
    private boolean isPlaced=false;

    public Tower(ProximityVector pos, Image image, int angle, String name) {
        super(pos, image, angle);
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public abstract void update();


    public Resources getUpgradeCost() {
        return getUpgrade().getCost();
    }

    @Override
    public void preparePlacing(ProximityVector position) {
        this.setCenter(position);
        GameData.getInstance().getPlayer().getResources().removeResources(getCost());
        isPlaced=true;
    }

    @Override
    public double getRange(){
        return range;
    }

    public Resources getCost() {
        return cost;
    }

    /**
     * The tower this tower will upgrade into
     * @return the upgraded version of this
     */
    public abstract Tower getNewUpgrade();

    public Tower getUpgrade(){
        if(upgrade == null) {
            upgrade = getNewUpgrade();
        }
        return upgrade;
    }
    public boolean getIfPlaced(){
        return isPlaced;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }

    public void setParticleManager(ParticleManager particleManager) {
        this.particleManager = particleManager;
    }
}
