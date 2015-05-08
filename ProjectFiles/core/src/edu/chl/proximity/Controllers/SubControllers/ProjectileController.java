package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Projectiles.Projectile;

import java.util.List;

/**
 * Created by Johan on 2015-04-11. Group work with Linda
 */
public class ProjectileController {

    private List<Projectile> projectiles;
    private List<Creep> creeps;
    private Map map;

    public ProjectileController(Map map){
        this.map = map;

    }

    public void update() {

    }
}
