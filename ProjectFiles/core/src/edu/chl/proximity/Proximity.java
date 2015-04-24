package edu.chl.proximity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Controllers.GameStates.MenuScreen;
import edu.chl.proximity.Models.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Maps.StandardMap;
import edu.chl.proximity.Models.Players.Player;
/**
 *
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 08/04 modified by Linda Evaldsson. Changes in structure, removed SpriteBatch.
 */

public class Proximity extends Game {
	public enum State{GAME,MAIN_MENU}

	public void changeScreen(State state, Map map, Player player){
		switch(state){
			case GAME:
				this.setScreen(new GameScreen(this,map,player));
				break;
			case MAIN_MENU:
				this.setScreen(new MenuScreen(this));
		}
	}

	@Override
	public void create () {
		this.setScreen(new GameScreen(this, new StandardMap(), new Player(new Planes())));
		GameData.getInstance().setGame(this);
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //This is here so the previous frame does not flicker into the next frame
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screen.render(Gdx.graphics.getDeltaTime());
	}

}
