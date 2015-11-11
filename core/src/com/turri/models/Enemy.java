package com.turri.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
	private float x;
	private float y;
	Sprite sprites;
	private Texture spriteTexture;
	private ParticleEffect deadParticle;
	private boolean isDead = false;
	private float velocity = 8;

	private Rectangle boundingCircle;

	public Enemy (float x, float y, String resource, String particleResource ) {
		this.x = x;
		this.y = y;
		spriteTexture = new Texture(resource);
		deadParticle = new ParticleEffect();
		deadParticle.load(Gdx.files.internal(particleResource), Gdx.files.internal(""));
		deadParticle.start();
		boundingCircle = new Rectangle();
	}

	private void setX(float x) {
		this.x = x;	
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public int getWidth() {
		return spriteTexture.getWidth();
	}

	public int getHeight() {
		return spriteTexture.getHeight();
	}

	public Rectangle getBoundingCircle() {
		return this.boundingCircle;
	}

	private Texture getTexture() {
		return spriteTexture;
	}

	private void restartEnemy() {
		// at first not due to restart
		//if (-1 * this.x >= this.getWidth()) {}
	}

	private void moveEnemy() {
		this.x -= velocity;
	}

	// Public methods
	public void drawEnemy(Batch batch) {
		if (isDead) {
			this.deadParticle.draw(batch);
		} else {
			batch.draw(this.spriteTexture, this.x, this.y);
		}
	}

	public void updateEnemy() {
		boundingCircle.set(x, y+getHeight()/4, getWidth()/2, getHeight()/2);
		this.moveEnemy();
		this.restartEnemy();
		if (isDead) {
			this.deadParticle.getEmitters().first().setPosition(this.getWidth()/2+this.x, this.getHeight()/2+this.y);
			this.deadParticle.getEmitters().get(1).setPosition(this.getWidth()/2+this.x, this.getHeight()/2+this.y);
			this.deadParticle.update(Gdx.graphics.getDeltaTime());
		}
	}

	public void setDead() {
		this.isDead = true;
	}
	public boolean getDead() {
		return this.isDead;
	}

	/**
	 *
	 * @return if the enemy finished all once dead
	 */
	public boolean getFinished() {
		if (this.x < 0) {
			return true;
		}
		if (isDead) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param maxXEnemy the x max enemies
	 * @param heigh the altitud we put the enemy
	 */
	public void restart(float maxXEnemy, float heigh) {
		this.x = maxXEnemy + (float)Math.random()*1000;
		this.y = heigh;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
}
