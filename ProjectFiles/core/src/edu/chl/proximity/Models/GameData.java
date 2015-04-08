package edu.chl.proximity.Models;

import edu.chl.proximity.Models.Maps.Map;

/**
 * Created by Floompa on 2015-04-08.
 */
public class GameData {

    private static GameData gameData;
    private Map map;

    private GameData() {

    }
    public static GameData getInstance() {
        if(gameData == null) {
            gameData = new GameData();
        }
        return gameData;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }
}
