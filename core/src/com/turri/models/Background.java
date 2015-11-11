package com.turri.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Background {
	private float x;
	private float y;
	private float initialX;
	private Texture background;
	private int backgroundType;

	public interface BackgroundType {
		static int LAST_BACKGROUND = 0;
		static int MIDDLE_BACKGROUND = 1;
		static int FIRST_BACKGROUND = 2;
	}

	public Background (float x, float y, String resource, int backgroundType) {
		this.x = x;
		this.y = y;
		this.initialX = x;
		this.background = new Texture(resource);
		this.backgroundType = backgroundType;
	}

	public void setX(float x) {
		this.x = x;
	}

	public Texture getTexture() {
		return this.background;
	}
	
	public float getX() {
		return this.x;
	}

	public float getWitdh() {
		return this.background.getWidth();
	}

	public void drawBackground(Batch batch) {
		batch.draw(this.getTexture(), this.x, this.y);
	}

	public void updateBackground() {
		moveBackground();
		restartBackground();
	}

	private void moveBackground() {
		// Moving background dependently if be in front or back
		switch(backgroundType) {
			case BackgroundType.LAST_BACKGROUND:
				this.x -= 4;
				break;
			case BackgroundType.MIDDLE_BACKGROUND:
				this.x -= 6;
				break;
			case BackgroundType.FIRST_BACKGROUND:
				this.x -= 8;
				break;
		}
	}

	private void restartBackground() {
		// When the background is out of the screen restart the position
		if (-1 * this.getX() >= this.getWitdh()) {
			this.setX(this.getWitdh());
		}
	}
}
