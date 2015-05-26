package edu.chl.proximity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Controllers.GameStates.GameOverScreen;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Controllers.GameStates.MenuScreen;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Players.Player;

/**
 * This is the entry point of the appliaction. On application start a new Player is created, and the menu is loaded.
 * The Game model does not load until the player enters a game. The player is loaded because the main menu items
 * adjust depending on the player save data.
 *
 * 08/04 Modified by Johan Swanberg. Switch to ScreenType from GameState.
 * 08/04 modified by Linda Evaldsson. Changes in structure, removed SpriteBatch.
 * 24/04 modified by Hanna Romer. Added method to change screen, between GameScreen and MenuScreen
 */

public class Proximity extends Game {

	@Override
	public void create () {
		Player player = new Player(null);
		this.setScreen(new MenuScreen(this, player, null));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //This is here so the previous frame does not flicker into the next frame
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		screen.render(Gdx.graphics.getDeltaTime()); //render the applications current screen
	}

}
