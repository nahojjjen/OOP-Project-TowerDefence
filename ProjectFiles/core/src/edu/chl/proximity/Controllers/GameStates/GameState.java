package edu.chl.proximity.Controllers.GameStates;

import edu.chl.proximity.Controllers.GameStateManager;

/**
 * Created by Johan on 2015-04-02.
 *
 * Could maybe be replaced by libGDX "Screen", needs discussion in group, but so far it works
 * so unless we run into problems, we can keep this structure. (Possibly can cause errors with 2dGUI class)
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void init();
    public abstract void update(float deltaTime);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
