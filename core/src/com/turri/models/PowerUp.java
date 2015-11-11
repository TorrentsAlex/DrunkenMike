package com.turri.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by alex torrents on 11/11/2015.
 */
public class PowerUp {
    private float x;
    private float y;
    Sprite sprites;
    private Texture spriteTexture;
    private float velocity = 8;

    private Rectangle boundingCircle;

    public PowerUp (float x, float y, String resource, String particleResource ) {
        this.x = x;
        this.y = y;
        spriteTexture = new Texture(resource);
        boundingCircle = new Rectangle();
    }

    private void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getWidth() {
        return spriteTexture.getWidth();
    }

    public int getHeight() {
        return spriteTexture.getHeight();
    }

    public Rectangle getBoundingCircle() {
        return this.boundingCircle;
    }

    private Texture getTexture() {
        return spriteTexture;
    }

    // Public methods
    public void drawPowerUp(Batch batch) {
        batch.draw(this.spriteTexture, this.x, this.y);
    }

    public void updatePowerUp() {
        boundingCircle.set(x, y, getWidth(), getHeight());
        x -= velocity;
    }

    /**
     *
     * @param maxXEnemy the x max enemies
     * @param heigh the altitud we put the enemy
     */
    public void restart(float maxXEnemy, float heigh) {
        this.x =(float)Math.random()*10000 + Gdx.graphics.getWidth();
        this.y = heigh;
        this.velocity = 8;
        boundingCircle.set(x, y, getWidth(), getHeight());
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }
}
