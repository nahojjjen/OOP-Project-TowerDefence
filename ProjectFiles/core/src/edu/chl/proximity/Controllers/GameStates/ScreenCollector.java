package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;

/**
 * @author Linda Evaldsson
 * @date 2015-05-26
 */
public class ScreenCollector {

    private static MenuScreen menuScreen;
    private static GameScreen gameScreen;
    private static GameOverScreen gameOverScreen;

    protected static void setMenuScreen(Game g, Player player, Viewport viewport) {
        if(menuScreen == null) {
            menuScreen = new MenuScreen(g, player, viewport);
        }
        menuScreen.initiateNew(player);
        g.setScreen(menuScreen);
    }
    protected static void setGameScreen(Game g, Map map, Player player, Viewport viewport) {
        if(gameScreen == null) {
            gameScreen = new GameScreen(g, map, player, viewport);
        }
        gameScreen.initiateNew(map, player);
        g.setScreen(gameScreen);
    }
    protected static void setGameOverScreen(Game g, Map map, Player player, Viewport viewport) {
        if(gameOverScreen == null) {
            gameOverScreen = new GameOverScreen(g, map, player, viewport);
        }
        gameOverScreen.initiateNew(g, map, player);
        g.setScreen(gameOverScreen);
    }
}
