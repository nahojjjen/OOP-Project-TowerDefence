package edu.chl.proximity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Controllers.GameStates.MenuScreen;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Player.Players.Player;
/**
 *
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 08/04 modified by Linda Evaldsson. Changes in structure, removed SpriteBatch.
 * 24/04 modified by Hanna R�mer. Added method to change screen, between GameScreen and MenuScreen
 */

public class Proximity extends Game {
	public enum State{GAME,MAIN_MENU}

	public void changeScreen(State state, Map map,MainMenu mainMenu, Player player){
		switch(state){
			case GAME:
				this.setScreen(new GameScreen(this,map,player));
				break;
			case MAIN_MENU:

				this.setScreen(new MenuScreen(this, new MainMenu(),map, player));
				GameData.getInstance().setGame(this);
		}
	}
	public State getCurrentScreen(){
		if(this.getScreen() instanceof GameScreen){
			return State.GAME;
		}else{
			return State.MAIN_MENU;
		}
	}

	@Override
	public void create () {
		//this.setScreen(new GameScreen(this, new StandardMap(), new Player(new Planes())));
		this.setScreen(new MenuScreen(this,new MainMenu(),new StandardMap(), new Player(new Planes())));
		GameData.getInstance().setGame(this);
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //This is here so the previous frame does not flicker into the next frame
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screen.render(Gdx.graphics.getDeltaTime());
	}

}
