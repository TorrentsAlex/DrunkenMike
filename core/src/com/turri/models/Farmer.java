package com.turri.models;

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
	private BitmapDrawable[] graphic = new BitmapDrawable[12];
	// array de bitmaps para los sprites
	// un bitmap aparte para las muertes
	private Paint paint;

	public Farmer(float x, float y, string resource) {
		this.context = context;
		this.x = x;
		this.y = y;
		this.initY = y;
		this.yMIN = y;

		paint = new Paint();
	}

	public void setCostumes(int[] outfits) {
		for (int i = 0; i < outfits.length; i++) {
			graphic[i] = (BitmapDrawable) context.getResources().getDrawable(
					outfits[i]);
		}
	}

	public void goTo(float xPos, float yPos) {
		this.x = xPos;
		this.y = yPos;
	}

	public void render() {
		if (this.isJumpOn) {
			this.jump();
		}
		c.drawBitmap(this.getBitmap(), this.getX(), this.getY(),
				this.getPaint());
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

	public Bitmap getBitmap() {
		setCurrentCostume();
		return graphic[currentCostume].getBitmap();
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

	public Paint getPaint() {
		if (detected) {
			if (imageBlink) {
				imageBlink = false;
				paint.setAlpha(0);
			} else {
				imageBlink = true;
				paint.setAlpha(255);
			}
		} else {
			paint.setAlpha(255);
		}
		return paint;
	}

	public void timerCollision() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Looper.prepare();
				detected = false;
				Looper.loop();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 2000);
	}
}
