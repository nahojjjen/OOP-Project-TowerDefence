package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.Gdx;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Holdables.Hand;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Proximity;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-08
 *
 * This class keeps track of the player.
 *
 * 23/04 edited by Hanna Romer. Added ButtonPanel, PropertiesPanel and sound volume of game.
 * 24/04 edited by Hanna Romer. Added Proximity and MainMenu + their setters and getters
 * 29/04 edited by Hanna Romer. Removed ButtonPanel and PropertiesButton.
 * 06/04 modified by Linda Evaldsson. Removing Map fron GameData, adding it as parameter to the classes that need it instead.
 * 07/04 modified by Linda Evaldsson. Removed other info from GameData as well. Volume and Gamespeed moved to class Settings.
 * MainMenu and Proximity also moved to other classes to prevent them from being accessible from everywhere in the program.
 */
public class GameData {

    private static GameData gameData;
    private Player player;

    private Hand hand = new Hand();
    private MainMenu mainMenu;

    private int gameSpeed = 1;

    private Proximity proximity;

    public static float VOLUME= 0.1f;


    private GameData() {}

    /**
     * Returns the only instance of GameData
     * @return the only instance of GameData
     */
    public static GameData getInstance() {
        if(gameData == null) {
            gameData = new GameData();
        }
        return gameData;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player inputPlayer) {
        player = inputPlayer;
    }

}
