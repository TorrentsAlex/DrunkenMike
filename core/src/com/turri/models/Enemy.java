package com.turri.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {
	private float x;
	private float y;
	private String name;
	private int costume;
	private int currentCostume;
	Sprite sprites;
	private boolean dead = false;

	public Enemy (float x, float y, String resource ) {
		this.x = x;
		this.y = y;
		Texture spriteTexture = new Texture(resource);
		this.sprites = new Sprite(spriteTexture,0,0,0,0);
	}
	
	public void goTo(float xPos, float yPos) {
		this.x = xPos;
		this.y = yPos;
	}

	public void setCurrentCostume(int i) {
		currentCostume = i;
	}

	public void setX(float x) {
		this.x = x;	
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

	public int getCostume() {
		return costume;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public boolean getDead() {
		return this.dead;
	}

}
