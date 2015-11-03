package com.turri.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.turri.interfaces.imageManagerInterface;
import com.turri.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ImageManager {

    private static ImageManager imageManager;

    private Mike mike;
    private List<Enemy> enemies;

    //	private List<Enemy> straws;
    private Background[] aBackgrounds;
    private Background[] aBackgrounds1;
    private float backgroundWidth = 0;

    private int maxXEnemy = 0;
    private int maxXStraws = 0;

    private Random random;
    private boolean isFinishRunnable = true;

    private imageManagerInterface managerInterface;

    public static ImageManager sharedManager() {
        if (imageManager == null) {
            imageManager = new ImageManager();
        }
        return imageManager;
    }

    public ImageManager() {
    }

    public void setImageManagerInterface(imageManagerInterface handler) {
        this.managerInterface = handler;
    }

    public void loadImages(float screenWidth,
                           float screenHeight) {
        float width20percent;
        float height20percent;
        float height40percent;
        float height60percent;
        float height80percent;
        random = new Random();

        width20percent = screenWidth * 0.10f;

        height20percent = screenHeight * 0.15f;
        height40percent = screenHeight * 0.30f;
        height60percent = screenHeight * 0.50f;
        height80percent = screenHeight * 0.70f;

        // capa1
        aBackgrounds = new Background[2];
        aBackgrounds1 = new Background[2];
        //Background background = new Background(context, 0, 0, R.drawable.capa1);
        aBackgrounds[0] = new Background(0, 0, "background1_1.png", Background.BackgroundType.LAST_BACKGROUND);
        backgroundWidth = aBackgrounds[0].getWitdh();
        aBackgrounds[1] = new Background((int) backgroundWidth, 0, "background1_1.png", Background.BackgroundType.LAST_BACKGROUND);

        aBackgrounds1[0] = new Background(0, 0, "background2_2.png", Background.BackgroundType.FIRST_BACKGROUND);
        aBackgrounds1[1] = new Background((int) backgroundWidth, 0, "background2_2.png", Background.BackgroundType.FIRST_BACKGROUND);

        float[] heighs = {height20percent, height40percent, height60percent};

        // START ENEMIES
        enemies = new ArrayList<Enemy>();
        for (int i = 0; i < 20; i++) {
            maxXEnemy += random.nextInt(1000);
            enemies.add(new Enemy(screenWidth + maxXEnemy,
                    heighs[random.nextInt(3)], "cow.png", "explosionCowsParticles"));
        }
        // Init the Character
        mike = new Mike(width20percent, height20percent, "RunAnimation.png", "fire");




        //		for (int i=0; i<5; i++) {
        //			maxXStraws += random.nextInt(2500);
        //			straws.add(new Enemy(context, screeenWidth+maxXStraws+3000,
        //							height80percent,"straws", R.drawable.straw));
        //		}

        // this callback call when the enemies, player and background has been loaded
        this.managerInterface.imagesLoaded();
    }

    public void draw(SpriteBatch batch) {
        // First draw the background
        for (Background bc : aBackgrounds) {
            bc.drawBackground(batch);
        }

        for (Background bc2 : aBackgrounds1) {
            bc2.drawBackground(batch);
        }

        // Now draw the Enemies
        for (Enemy e : enemies) {
            e.drawEnemy(batch);
        }

        // And finally mike
        this.mike.draw(batch);

        // Draw the particles
        /* I need something like particleManage
        *   load all particles from the class
        *   but... the x and y? how i know it?
        */

        // collissions test

    }

    public void update() {
        // First draw the background
        for (Background bc : aBackgrounds) {
            bc.updateBackground();
        }

        for (Background bc2 : aBackgrounds1) {
            bc2.updateBackground();
        }

        // Now draw the Enemies
        for (Enemy e : enemies) {
            e.updateEnemy();
        }

        // And finally mike
        this.mike.update();
    }

    public void physics() {
        List<Bullet> bullets = this.mike.getBullets();
        for (Bullet bullet : bullets) {
            for (Enemy e: enemies) {
                if(CollisionDetector.getCollision(bullet, e)) {
                    Gdx.app.log("mike","return true collision detector");
                    e.setDead();
                    bullet.stop();
                }
            }
        }
    }

    public void jumpFarmer() {
        this.mike.setJumping(true);
    }

    // Bullets
    public void newBullet(float finalX, float finalY) {
      this.mike.shoot(finalX, finalY);
    }

    // Utilities
    public Random getRandom() {
        return this.random;
    }

    public int getMaxXEnemy() {
        return this.maxXEnemy;
    }

    public int getMaxXStraw() {
        return this.maxXStraws;
    }


    //	public void removeEnemy(List<Enemy> removeEnemies) {
    //		this.enemies.remove(removeEnemies);
    //	}
}
