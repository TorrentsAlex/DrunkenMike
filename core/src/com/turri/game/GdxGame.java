package com.turri.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.turri.interfaces.imageManagerInterface;
import com.turri.manager.ImageManager;

public class GdxGame extends ApplicationAdapter implements imageManagerInterface {
	SpriteBatch batch;
	ImageManager manager;

	boolean imagesLoaded = false;

	@Override
	public void create () {
		batch = new SpriteBatch();


		manager = new ImageManager(this);
		manager.loadImages(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		manager.drawFarmer();
		batch.end();

		manager.updateBackground();
	}

	public void imagesLoaded() {
		imagesLoaded = true;
	}
}
