package edu.chl.proximity.Viewers;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.WonLostModels.GameOver;

/**
 * @author Hanna Romer
 * @date 2015-05-15
 *
 * A class that handles the rendering of the game over screen
 *
 */
public class GameOverRenderer {
    private GameOver gameOver;

    public GameOverRenderer(GameOver gameOver){
        this.gameOver=gameOver;
    }

    public void render(ProximityBatch batch){
        gameOver.render(batch);
    }
}
