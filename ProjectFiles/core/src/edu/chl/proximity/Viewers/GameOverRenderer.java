package edu.chl.proximity.Viewers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Models.WonLostModels.GameOver;

/**
 * @author Hanna Römer
 * @date 2015-05-15
 */
public class GameOverRenderer {
    private GameOver gameOver;

    public GameOverRenderer(GameOver gameOver){
        this.gameOver=gameOver;
    }

    public void render(SpriteBatch batch){
        gameOver.render(batch);
    }
}
