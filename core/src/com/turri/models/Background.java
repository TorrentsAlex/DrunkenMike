package com.turri.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class Background {
	private Context context;
	private int x;
	private int y;
	private BitmapDrawable image;
	
	
	public Background (Context context, int x, int y, int image) {
		this.context = context;
		this.x = x;
		this.y = y;
		this.image = (BitmapDrawable)context.getResources().getDrawable(image);
	}
	
	public void moveBackground (int x) {
		this.x -= x;
	}
	
	public BitmapDrawable getBitmapDrawable() {
		return image;
	}
	
	public Bitmap getBitmap() {
		return image.getBitmap();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getWitdh() {
		return image.getBitmap().getWidth();
	}
	public int getY() {
		return this.y;
	}
}
