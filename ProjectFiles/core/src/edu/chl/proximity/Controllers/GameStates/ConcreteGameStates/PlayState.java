package edu.chl.proximity.Controllers.GameStates.ConcreteGameStates;

import edu.chl.proximity.Controllers.GameStateManager;
import edu.chl.proximity.Controllers.GameStates.GameState;
import edu.chl.proximity.Viewers.Renderer;

/**
 * Created by Johan on 2015-04-02.
 */
public class PlayState extends GameState {

    public PlayState(GameStateManager gsm){
        super(gsm);
        init();
    }


    @Override
    public void init() {
        System.out.println("Starting GameState");
    }

    @Override
    public void update(float deltaTime) {
        System.out.println("Updating PlayState!");
    }

    @Override
    public void draw() {
        System.out.println("Drawing PlayState!");
        Renderer.render();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
