package com.turri.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.turri.inputs.TurriListener;
import com.turri.interfaces.imageManagerInterface;
import com.turri.manager.ImageManager;

public class TurriGame extends ApplicationAdapter implements imageManagerInterface {
	SpriteBatch batch;
	ImageManager manager;

	boolean imagesLoaded = false;
	private static TurriGame game;

	public static TurriGame sharedGame() {
		if (game == null) {
			game = new TurriGame();
		}
		return game;
	}
	@Override
	public void create () {
		batch = new SpriteBatch();

		manager = new ImageManager(this);
		manager.loadImages(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(new GestureDetector(new TurriListener()));
	}

	@Override
	public void render () {
		//TODO:: change this with play states
		if (!imagesLoaded) {
			return;
		}
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		manager.drawBackground(batch);
		manager.drawEnemies(batch);
		batch.end();

		// Draw sprites after end batch, the sprites uses other batch to render the texture
		manager.drawFarmer();

		// Update positions
		manager.updateEnemies();
		manager.updateBackground();
	}

	public void imagesLoaded() {
		imagesLoaded = true;
	}
}
