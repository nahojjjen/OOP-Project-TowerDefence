package edu.chl.proximity.Viewers;

import edu.chl.proximity.Models.WonLostModels.GameOver;
import edu.chl.proximity.Utilities.ProximityBatch;

/**
 * @author Hanna Romer
 * @date 2015-05-15
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
