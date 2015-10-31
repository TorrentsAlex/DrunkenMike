package com.turri.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bullet {
	private float x;
	private float y;
	private float finalX;
	private float finalY;
	
	private float goX;
	private float goY;

	private boolean remove = false;

	private Texture bulletTexture;
	private final static int X_MOVE = 30;
	private final static int Y_MOVE = 30;
	public Bullet(float x, float y, float finalX, float finalY, String stringName) {
		this.x = x;
		this.y = y;
		this.finalX = finalX;
		this.finalY = finalY;

		this.bulletTexture = new Texture(stringName);

		// Calculate the trajectory
		float xDistance = finalX - x;
		xDistance = xDistance/X_MOVE;
		
		float yDistance = finalY - y;
		yDistance = yDistance/Y_MOVE;

		goX = xDistance;
		goY = yDistance;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}

	public boolean getRemove() {
		return this.remove;
	}

	private void go() {
		this.x += goX;
		this.y += goY;
	}

	private float rotate() {
		return 0;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(bulletTexture, this.x, this.y);
	}

	public void update() {
		// Set to remove if pass the screenwidth
		if (this.x > Gdx.graphics.getWidth()) {
				this.remove = true;
		}
		this.go();
		this.rotate();
	}

}
