package com.turri.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {
	private float x;
	private float y;
	Sprite sprites;
	private Texture spriteTexture;

	public Enemy (float x, float y, String resource ) {
		this.x = x;
		this.y = y;
		spriteTexture = new Texture(resource);
	}
	
	private void goTo(float xPos, float yPos) {
		this.x = xPos;
		this.y = yPos;
	}

	private void setX(float x) {
		this.x = x;	
	}
	private int getWidth() {
		return spriteTexture.getWidth();
	}
	private Texture getTexture() {
		return spriteTexture;
	}

	private void restartEnemy() {
		// at first not due to restart
		//if (-1 * this.x >= this.getWidth()) {}
	}

	private void moveEnemy() {
		this.goTo(this.x - 10, this.y);
	}

	// Public methods
	public void drawEnemy(Batch batch) {
		batch.draw(this.spriteTexture , this.x, this.y);
	}

	public void updateEnemy() {
		this.moveEnemy();
		this.restartEnemy();
	}
}
