package edu.chl.proximity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Controllers.GameStates.MenuScreen;
import edu.chl.proximity.Models.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Maps.StandardMap;
import edu.chl.proximity.Models.Players.Player;


public class Proximity extends Game {

	
	@Override
	public void create () {
		this.setScreen(new GameScreen(this, new StandardMap(), new Player(new Planes())));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //This is here so the previous frame does not flicker into the next frame
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screen.render(Gdx.graphics.getDeltaTime());
	}

}
