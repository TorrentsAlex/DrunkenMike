package com.turri.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture b1;
	Texture b2;
	Texture title;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("jump.png");
		b1 = new Texture("background1_1.png");
		b2 = new Texture("background2_2.png");
		title = new Texture("titlemike.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(b1, 0, 0);
		batch.draw(b2, 0, 0);
		batch.draw(title ,Gdx.graphics.getWidth()/2-851/2 ,Gdx.graphics.getHeight()/2-453/2,851,453 );
		batch.draw(img, 2, 2);
		batch.end();
	}
}
