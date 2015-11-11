package com.turri.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.turri.manager.BulletManager;

import java.util.List;

public class Mike {

	private float x;
	private float y;
	private float yMin;

	private boolean dead = false;

	private float gravity = -2f;
	private final float velocity = 35;
	private float currentVelocity = velocity;

	private boolean isDoubleJumpOn = false;
	private boolean isJumpOn = false;

	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private Animation walkAnimation;
	float stateTime;
	private BulletManager bManager;

	private ParticleEffect pe;
	private boolean isShooting;

	private Circle boundingCircle;

	private static int xSpaceGun = 50;

	public Mike(float x, float y, String resource, String particleResource) {
		this.x = x;
		this.y = y;
		this.yMin = y;

		// Animation
		Texture texture = new Texture(resource);
		TextureRegion[][] tmp = TextureRegion.split(texture ,texture.getWidth()/4 ,texture.getHeight()/3);
		walkFrames = new TextureRegion[11];
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (index == 11) {
					break;
				}
				walkFrames[index++] = tmp[i][j];
			}
		}

		walkAnimation = new Animation(1/11f, walkFrames);
		stateTime = 0f;

		// Particle
		pe = new ParticleEffect();
		pe.load(Gdx.files.internal(particleResource), Gdx.files.internal(""));
		pe.start();

		this.bManager = BulletManager.shareBulletManager();
		boundingCircle = new Circle();
	}
	public float getWidth() {
		return this.walkFrames[0].getRegionWidth();
	}

	public float getHeight() {
		return this.walkFrames[0].getRegionHeight();
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(currentFrame, this.x, this.y, this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(), 0.8f, 0.8f, 0);
		if (isShooting) {
			this.pe.draw(spriteBatch);
		}
		bManager.drawBullets(spriteBatch);
	}

	public void update() {
		// The update to be before the draw, because in the first loop currentFrame is null
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		if (isShooting)  {
			pe.getEmitters().first().setPosition(this.getWidth()+this.x-xSpaceGun, this.getHeight()/2+this.y);
			this.pe.update(Gdx.graphics.getDeltaTime());
		}

		bManager.updateBullets();

		if (isJumpOn) {
			jump();
		}
		if (pe.isComplete()) {
			isShooting = false;
			pe.reset();
		}
		boundingCircle.set(x+getWidth()/3f, y+getHeight()/2f, getWidth()/3f);

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

	public Circle getBoundingCircle() {
		return this.boundingCircle;
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

	public void setShooting(boolean shooting) {
		this.isShooting = shooting;
	}


	public void shoot(float finalX, float finalY) {
		this.setShooting(true);
		bManager.createNewBullet(this.getX() + this.getWidth()-xSpaceGun,
				this.getY() + this.getHeight()/2,
				finalX,
				finalY);
	}

	public List<Bullet> getBullets() {
		return this.bManager.getBullets();
	}
}
