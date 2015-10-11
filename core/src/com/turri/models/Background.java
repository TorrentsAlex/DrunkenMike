package com.turri.models;


import com.badlogic.gdx.graphics.Texture;

public class Background {
	private int x;
	private int y;
	Texture background;
	
	public Background (int x, int y, String resource) {
		this.x = x;
		this.y = y;
		background = new Texture(resource);
	}
	
	public void moveBackground (int x) {
		this.x -= x;
	}
	
//	public BitmapDrawable getBitmapDrawable() {
//		return image;
//	}
//
//	public Bitmap getBitmap() {
//		return image.getBitmap();
//	}
	
	public void setX(int x) {
		this.x = x;
	}
	public Texture getTexture() {
		return this.background;
	}
	
	public int getX() {
		return this.x;
	}
	public int getWitdh() {
		return background.getWidth();
	}
	public int getY() {
		return this.y;
	}
}
