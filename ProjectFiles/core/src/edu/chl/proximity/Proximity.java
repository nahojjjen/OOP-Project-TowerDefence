package edu.chl.proximity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Controllers.GameStateManager;
import edu.chl.proximity.Controllers.GameStates.GameState;

public class Proximity extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("core/assets/badlogic.jpg");

		gsm = new GameStateManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
	}
}
