package edu.chl.proximity.Models.Map.Towers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan
 * @date 2015-05-26.
 *
 * A tower which shoots fire whenever creeps come close
 */
public class FireTower extends Tower{
    //Tower stats
    private static Resources resources = new Resources(100, 100, 2);
    private static double fireRange = 100f;
    private static int reloadTime = 150;
    private static int emberTime = 50;
    private int currentEmber = 0;
    private int currentReload = 0;

    private TargetingMethod targetingMethod;

    private static Image img = new Image(Constants.FILE_PATH + "Towers/Flamethrower/1.png");

    public FireTower(ProximityVector pos, TargetingMethod targetingMethod, ParticleManager particleManager) {
        super(pos, img, 0, "Flame Spire");
        range = fireRange;
        setParticleManager(particleManager);
        this.cost = resources;
        this.targetingMethod = targetingMethod;
    }


    /**
     * get all targets in fireRange of the tower
     * @param creeps what creeps should be searched among
     * @return a list of creeps within fireRange of the tower
     */
    private List<Creep> targetsInRange(List<Creep> creeps){
        List<Creep> list = new ArrayList<Creep>();

        for (Creep creep:creeps){
            if (targetingMethod.isWithinRange(creep, this.getCenter(), fireRange)){
                list.add(creep);
            }
        }
        return list;
    }

    /**
     * Attempts to shoot nearby enemies, if the tower is on cooldown nothing will happen.
     * @param creeps what creeps should the tower compare its logic too
     */
    public void shoot(List<Creep> creeps){
        if (creeps != null){
            List<Creep> killableCreeps = targetsInRange(creeps);

            if (currentEmber > 0){ //the residual burning field,
                if (currentEmber % 15 == 0) { //avoid devolving every frame, too good for the player
                    attackNearbyCreeps(killableCreeps);
                }

                currentEmber--;
            }
            if (currentReload <= 0  && killableCreeps.size() > 0) { //shoot if no reload && creeps nearby
                startTowerAttack(killableCreeps);
            }
        }
        currentReload--;
    }

    /**
     * start a fire attack, causes particle effects to shoot out
     * @param killableCreeps
     */
    private void startTowerAttack(List<Creep> killableCreeps){
        createFireEffect();
            attackNearbyCreeps(killableCreeps);
        currentEmber = emberTime;
        currentReload = reloadTime;
    }

    /**
     * devolves nearby creeps and makes them smoke / ignite
     * @param creeps what creeps to damage
     */
    private void attackNearbyCreeps(List<Creep> creeps){
        for (Creep creep :creeps){
            creep.devolve();
            getParticleManager().getFireCreepEffect().createEffect(creep.getCenter());
        }
    }
    /**
     * surrounds the tower with fire effects
     */
    private void createFireEffect(){
        for (int i = 0; i<9; i++){
            float angle = 40*i; //create fire effects in all 4 directions
            getParticleManager().getFireBurstEffect().createEffect(getCenter(), angle, 30);
        }
    }
    public String getDescription() {
        return  "\n " + "A fire spire that creates waves of flame that burn alla enemies in a circle around it";
    }
    @Override
    public void update(List<Creep> creeps) {
        shoot(creeps);
    }

    @Override
    public Tower getNewUpgrade() {
        return null;
    }

}
