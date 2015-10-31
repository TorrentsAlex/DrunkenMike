package com.turri.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Mike {

	private float x;
	private float y;
	private float yMin;

	private boolean dead = false;

	private float gravity = -2.5f;
	private final float velocity = 45;
	private float currentVelocity = velocity;

	private boolean isDoubleJumpOn = false;
	private boolean isJumpOn = false;

	TextureRegion[] walkFrames;
	TextureRegion currentFrame;
	Animation walkAnimation;
	float stateTime;
	//private BitmapDrawable[] graphic = new BitmapDrawable[12];
	// array de bitmaps para los sprites
	// un bitmap aparte para las muertes
	//private Paint paint;

	public Mike(float x, float y, String resource) {
		this.x = x;
		this.y = y;
		this.yMin = y;
		Texture texture = new Texture(resource);
		TextureRegion[] tmp = TextureRegion.split(texture ,texture.getWidth()/11 ,texture.getHeight())[0];
		walkFrames = new TextureRegion[11];
		for (int i=0; i < 11; i++) {
			walkFrames[i] = tmp[i];
		}

		walkAnimation = new Animation(1/11f, walkFrames);
		stateTime = 0f;
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(currentFrame, this.x, this.y);
	}

	public void update() {
		// The update to be before the draw, because in the first loop currentFrame is null
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		if (isJumpOn) {
			jump();
		}
	}

	private void jump() {
		currentVelocity += gravity;
		this.y += currentVelocity;
		if (y <= yMin && !dead) {
        	resetJump();
        }
	}

	private void resetJump() {
		currentVelocity = velocity;
		isJumpOn = false;
		isDoubleJumpOn = false;
		this.y = this.yMin;
	}

	private void doubleJump() {
		if (isDoubleJumpOn) {
			return;
		}
		currentVelocity = velocity;
		isDoubleJumpOn = true;
	}

	// Get Functions
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}	
	
	public boolean isDead() {
		return this.dead;
	}

	public void setJumping(Boolean jump) {
		// If isJump is true, it means mike is on air
		if (this.isJumpOn) {
			this.doubleJump();
		}
		this.isJumpOn = jump;

	}

	public float getWidth() {
		return this.walkFrames[0].getRegionWidth();
	}

	public float getHeight() {
		return this.walkFrames[0].getRegionHeight();
	}

}
