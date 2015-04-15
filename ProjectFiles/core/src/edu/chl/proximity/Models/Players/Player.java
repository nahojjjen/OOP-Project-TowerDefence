package edu.chl.proximity.Models.Players;

import edu.chl.proximity.Models.ResourceSystem.Resources;

/**
 * Created by User on 2015-04-15.
 */
public class Player {
    private Resources resources;

    public Player(){
        resources=new Resources(300, 300, 0);
    }

    public Resources getResources(){
        return resources;
    }
}
