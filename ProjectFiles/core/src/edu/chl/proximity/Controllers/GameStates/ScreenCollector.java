package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;

/**
 * @author Linda Evaldsson
 * @date 2015-05-26
 *
 * A class that collects the screens, when a new screen is needed in
 * one of the screens it can be gotten through this class.
 */

public class ScreenCollector {

    private static MenuScreen menuScreen;
    private static GameScreen gameScreen;
    private static GameOverScreen gameOverScreen;

    /**
     * Changes screen to the menu screen
     * @param g The current game played
     * @param player The player that is playing
     * @param viewport The viewport that is used
     */
    public static void setMenuScreen(Game g, Player player, Viewport viewport) {
        if(menuScreen == null) {
            menuScreen = new MenuScreen(g, player, viewport);
        }
        menuScreen.initiateNew(player);
        g.setScreen(menuScreen);
        garbageCollect();
    }

    /**
     * Changes screen to the game screen
     * @param g The current game played
     * @param map The map that should be started
     * @param player The player that is playing
     * @param viewport The viewport that is used
     */
    public static void setGameScreen(Game g, Map map, Player player, Viewport viewport) {
        if(gameScreen == null) {
            gameScreen = new GameScreen(g, map, player, viewport);
        }
        gameScreen.initiateNew(map, player);
        g.setScreen(gameScreen);
        garbageCollect();
    }

    /**
     * Changes screen to the game over screen
     * @param g The current game played
     * @param map The map that should be started
     * @param player The player that is playing
     * @param viewport The viewport that is used
     */
    public static void setGameOverScreen(Game g, Map map, Player player, Viewport viewport) {
        if (gameOverScreen == null) {
            gameOverScreen = new GameOverScreen(g, map, player, viewport);
        }
        gameOverScreen.initiateNew(g, map, player);
        g.setScreen(gameOverScreen);
        garbageCollect();
    }

    /**
     * This method simply calls the java garbage collector
     *
     * FindBugs comment: If this is not here the program heap size goes up to 6GB and does not garbage collect.
     * Findbugs explicit GC warning not applicable in manual handling of memory in games. This is a known bug.
     * This method will only be called when the screen is changed. Does not cause performance issue
     */
    private static void garbageCollect() {
        System.gc();
    }
}
