package com.turri.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Timer;
import java.util.TimerTask;

public class Farmer {

	private float x;
	private float y;
	private float initY;
	private float yMIN;

	private boolean dead = false;

	private float gravity = -3f;
	private float velocity = 45;

	public boolean detected = false;
	private boolean imageBlink = true;
	private String name;
	private int currentCostume = 0;
	public boolean isJumpOn = false;

	TextureRegion[] walkFrames;
	TextureRegion currentFrame;
	Animation walkAnimation;
	SpriteBatch spriteBatch;
	float stateTime;
	//private BitmapDrawable[] graphic = new BitmapDrawable[12];
	// array de bitmaps para los sprites
	// un bitmap aparte para las muertes
	//private Paint paint;

	public Farmer(float x, float y, String resource) {
		this.x = x;
		this.y = y;
		this.initY = y;
		this.yMIN = y;
		Texture texture = new Texture(resource);
		TextureRegion[] tmp = TextureRegion.split(texture ,texture.getWidth()/11 ,texture.getHeight())[0];
		walkFrames = new TextureRegion[11];
		for (int i=0; i < 11; i++) {
			walkFrames[i] = tmp[i];
		}

		walkAnimation = new Animation(1/11f, walkFrames);

		spriteBatch = new SpriteBatch();
		stateTime = 0f;
	}


	public void goTo(float xPos, float yPos) {
		this.x = xPos;
		this.y = yPos;
	}

	public void draw() {
		/// update method
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		///
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, this.x, this.y);
		spriteBatch.end();

	}

	public void jump() {
		velocity += gravity;
		this.y -= velocity;
		if (y >= yMIN && !dead) {
			velocity = 45;
			isJumpOn = false;
			this.y = this.initY;
		}
	}
	
	public void setGravity(int gravity) {
		this.velocity = gravity;
	}

	public void setCurrentCostume() {
		if (dead) {
			currentCostume = 11;
		} else {
			currentCostume++;
			if (currentCostume >= 11) {
				currentCostume = 0;
			}
		}
	}

	// Get Functions
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String getName() {
		return name;
	}


	public void setDead(boolean dead) {
		this.dead = dead;
	}	
	
	public boolean isDead() {
		return this.dead;
	}
	public void setJumping(Boolean jump) {
		this.isJumpOn = jump;
	}

//	public void timerCollision() {
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				Looper.prepare();
//				detected = false;
//				Looper.loop();
//			}
//		};
//		Timer timer = new Timer();
//		timer.schedule(task, 2000);
//	}
}
