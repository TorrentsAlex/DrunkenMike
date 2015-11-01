package com.turri.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Bullet {
	private float x;
	private float y;
	private float finalX;
	private float finalY;

	private float rotate;

	private boolean remove = false;

	private Texture bulletTexture;

	private final static float velocity = -1000;
	float angle;

	public Bullet(float x, float y, float finalX, float finalY, String stringName) {
		this.x = x;
		this.y = y;
		this.finalX = finalX;
		this.finalY = finalY;

		this.bulletTexture = new Texture(stringName);

		// Calculate the trajectory
		float xDistance = x-finalX;
		//xDistance = Math.cos(xDistance);
		
		float yDistance =  y-finalY;
		//yDistance = Math.sin(yDistance);
		angle = MathUtils.atan2(yDistance, xDistance);

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
		this.x += velocity * Gdx.graphics.getDeltaTime() * MathUtils.cos(angle);
		this.y += velocity * Gdx.graphics.getDeltaTime() * MathUtils.sin(angle);
	}

	private float rotate() {
		if (rotate < 0.0f) {
			rotate = 360.0f;
		} else {
			rotate -= 20;
		}
		return 0;
	}

	public void draw(SpriteBatch batch) {
		//batch.draw(bulletTexture, this.x, this.y);
		batch.draw(bulletTexture, this.x, this.y, bulletTexture.getWidth()/2.0f,bulletTexture.getHeight()/2.0f,
				bulletTexture.getWidth()*1.0f, bulletTexture.getHeight()*1.0f, 1f, 1f, rotate,0,0,bulletTexture.getWidth(),bulletTexture.getHeight(),false,false);


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
