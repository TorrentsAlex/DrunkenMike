package com.turri.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.FloatMath;

public class Bullet {
	private Context context;
	private int x;
	private int y;
	private float finalX;
	private float finalY;
	
	private float moveX;
	private float moveY;
	
	private BitmapDrawable custome;
	
	private final static int X_MOVE = 30;
	
	public Bullet(Context context, int x, int y, float finalX, float finalY, int outfit) {
		this.context = context;
		this.x = x;
		this.y = y;
		this.finalX = finalX;
		this.finalY = finalY;
		this.custome = (BitmapDrawable) context.getResources().getDrawable(
				outfit);
		
		// calculando la trayectoria
		float xDistance = finalX - x;
		xDistance = xDistance/X_MOVE;
		
		float yDistance = finalY-y;
		yDistance = yDistance/xDistance;
		moveX = X_MOVE;
		moveY = yDistance;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void move() {
		this.x += moveX;
		this.y += moveY;
	}
	
	public Bitmap getBitmap() {
		return custome.getBitmap();
	}
}
