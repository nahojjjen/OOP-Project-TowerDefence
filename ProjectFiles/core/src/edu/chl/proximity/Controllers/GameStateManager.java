package edu.chl.proximity.Controllers;

import edu.chl.proximity.Controllers.GameStates.ConcreteGameStates.PlayState;
import edu.chl.proximity.Controllers.GameStates.GameState;

/**
 * Created by Johan on 2015-04-02.
 */
public class GameStateManager {

    private GameState gameState;
    public static final int MENU = 1347;
    public static final int PLAY = 1337;
    public static final int PAUSE = 0000;

    public GameStateManager(){
        setState(PLAY);

    }

    public void setState(int state){
        if (gameState != null) gameState.dispose();
        if (state == MENU){
            //switch to menu state .. gameState = new menuState
        } else if(state == PLAY){
            gameState = new PlayState(this);

        }
        else if(state == PAUSE){
            //switch to pause state
        }
    }

    public void update (float dt){
        gameState.update(dt);
    }

    public void draw(){
        gameState.draw();
    }

}
