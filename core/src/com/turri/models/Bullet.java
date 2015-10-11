package com.turri.models;

public class Bullet {
	private int x;
	private int y;
	private float finalX;
	private float finalY;
	
	private float moveX;
	private float moveY;
	

	private final static int X_MOVE = 30;
	
	public Bullet(int x, int y, float finalX, float finalY, int outfit) {
		this.x = x;
		this.y = y;
		this.finalX = finalX;
		this.finalY = finalY;
		
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

}
