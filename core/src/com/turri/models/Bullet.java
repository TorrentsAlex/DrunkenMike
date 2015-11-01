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

	private final static float velocity = 1000;
	float angle;

	public Bullet(float x, float y, float finalX, float finalY, String stringName) {
		this.x = x;
		this.y = y;
		this.finalX = finalX;
		this.finalY = finalY;

		this.bulletTexture = new Texture(stringName);

		// Calculate the trajectory
		float xDistance = finalX - x;
		float yDistance =  finalY - y;

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
				bulletTexture.getWidth()*1.0f, bulletTexture.getHeight()*1.0f, 1f, 1f, rotate,0,0,bulletTexture.getWidth(),bulletTexture.getHeight(),false,false);
		batch.draw(bulletTexture, /* Texture */
				this.x, /* x coordinate */
				this.y, /* y coordinate */
				bulletTexture.getWidth()/2.0f, /* The x-coordinate of the rotation relative to the screen */
				bulletTexture.getHeight()/2.0f,/* The y-coordinate of the rotation relative to the screen */
				bulletTexture.getWidth()*1.0f,/* The width */
				bulletTexture.getHeight()*1.0f,/* The height */
				1f, /* scale x */
				1f, /* scale y */
				rotate, /* The angle of counter rotation*/
				0, /* the x coordinate who start the texture */
				0, /* the y coordinate who start the texture */
				bulletTexture.getWidth(), /* the source in texels */
				bulletTexture.getHeight(), /* the source in texels*/
				false, /* Flip horizontally */
				false);/* Flip vertically */

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
