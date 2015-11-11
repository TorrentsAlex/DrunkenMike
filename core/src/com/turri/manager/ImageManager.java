package com.turri.manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.turri.interfaces.imageManagerInterface;
import com.turri.models.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ImageManager {


    private Mike mike;
    private List<Enemy> enemies;
    private PowerUp poweUp;

    float[] heighs = new float[3];
    
    private int maxXEnemy = 0;

    private Random random;
    private static ImageManager imageManager;

    private imageManagerInterface managerInterface;
    private BackgroundManager bManager;

    private ParallaxBackground rbg1;
    private ParallaxBackground rbg2;
    private ParallaxBackground rbg3;
    private ParallaxBackground rbg4;

    private float points;
    private static float maxSpeed = 10;

    private static float initFfbSpeed = 4;
    private static float initSbSpeed = 6;
    private static float initPlayerSpeed = 8;

    private float fbSpeed;
    private float sbSpeed;
    private float playerSpeed;

    private boolean isPowerReleased = false;

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
        fbSpeed = initFfbSpeed;
        sbSpeed = initSbSpeed;
        playerSpeed = initPlayerSpeed;

        float width20percent;
        float height20percent;
        float height40percent;
        float height60percent;
        float height80percent;
        random = new Random();
        bManager = BackgroundManager.sharedBackgroundManager();

        width20percent = screenWidth * 0.10f;

        height20percent = screenHeight * 0.15f;
        height40percent = screenHeight * 0.30f;
        height60percent = screenHeight * 0.50f;
        height80percent = screenHeight * 0.70f;

        heighs[0] = height20percent;
        heighs[1] = height40percent;
        heighs[2] = height60percent;
        // START ENEMIES
        enemies = new ArrayList<Enemy>();
        for (int i = 0; i < 50; i++) {
            maxXEnemy += random.nextInt(1000);
            enemies.add(new Enemy(screenWidth + maxXEnemy,
                    heighs[random.nextInt(3)], "cow.png", "explosionCowsParticles"));
        }
        // Init the Character
        mike = new Mike(width20percent, height20percent-50, "RunAnimation.png", "fire");

       // bManager.initBackgrounds();
        rbg1 = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(new Texture("background1_1.png"),new Vector2(1f,0f),new Vector2(), new Vector2()),
                new ParallaxLayer(new Texture("background1_2.png"),new Vector2(1f,0f),new Vector2(), new Vector2(2600, 0)),
        }, screenWidth, screenHeight,new Vector2(4,0));
        rbg2 = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(new Texture("background2_1.png"),new Vector2(1f,1f),new Vector2()),
                new ParallaxLayer(new Texture("background2_2.png"),new Vector2(1f,1f),new Vector2(), new Vector2(2600, 0)),
        }, screenWidth, screenHeight,new Vector2(6,0));
        rbg3 = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(new Texture("background3_1.png"),new Vector2(1f,1f),new Vector2()),
                new ParallaxLayer(new Texture("background3_2.png"),new Vector2(1f,1f),new Vector2(), new Vector2(2600, 0)),
        }, screenWidth, screenHeight,new Vector2(8,0));
        rbg4 = new ParallaxBackground(new ParallaxLayer[]{
                new ParallaxLayer(new Texture("Vallas.png"),new Vector2(1f,0f),new Vector2()),
                new ParallaxLayer(new Texture("Vallas.png"),new Vector2(1f,0f),new Vector2(), new Vector2(3500, 0)),
        }, screenWidth, 300,new Vector2(8,0));

        poweUp = new PowerUp(screenWidth+1000,heighs[random.nextInt(3)], "beer.png", "starParticle");

        // this callback call when the enemies, player and background has been loaded
        this.managerInterface.imagesLoaded();
    }

    public void drawBackground() {
        rbg1.render(1);
        rbg2.render(1);
        rbg3.render(1);
    }

    public void drawForeGround() {
     //   rbg4.render(1);
    }
    public void draw(SpriteBatch batch) {

      //  bManager.drawBackground(batch);
        // Now draw the Enemies
        for (Enemy e : enemies) {
            e.drawEnemy(batch);
        }

        if (isPowerReleased) {
            this.poweUp.drawPowerUp(batch);
        }

        // And finally mike
        this.mike.draw(batch);

       // bManager.drawForeground(batch);
        // Draw the particles
        /* I need something like particleManage
        *   load all particles from the class
        *   but... the x and y? how i know it?
        */

        // collissions test


    }

    public void update() {
      //  bManager.updateBackground();
        // Now draw the Enemies
        for (Enemy e : enemies) {
            e.updateEnemy();
            if (e.getFinished()) {
                e.restart(maxXEnemy, heighs[random.nextInt(3)]);
            }
        }
        if (isPowerReleased) {
            this.poweUp.updatePowerUp();
        }
        // And finally mike
        this.mike.update();
    }

    public void physics() {
        List<Bullet> bullets = this.mike.getBullets();
        for (Bullet bullet : bullets) {
            for (Enemy e: enemies) {
                if(CollisionDetector.getCollision(bullet, e)) {
                    e.setDead();
                    bullet.stop();
                    points++;
                    plusVelocity();
                }
            }
        }
        for (Enemy e: enemies) {
            if (CollisionDetector.getCollision(mike,e)) {
                mike.setDead(true);
            }
        }

        if (CollisionDetector.getCollision(this.mike, this.poweUp)) {

            fbSpeed = initFfbSpeed;
            sbSpeed = initSbSpeed;
            playerSpeed = initPlayerSpeed;
            rbg1.setVelocity(new Vector2(fbSpeed, 0));
            rbg2.setVelocity(new Vector2(sbSpeed, 0));
            rbg3.setVelocity(new Vector2(playerSpeed, 0));
            rbg4.setVelocity(new Vector2(playerSpeed, 0));
            for (Enemy e: enemies) {
                e.setVelocity(playerSpeed);
            }
            isPowerReleased = false;
            this.poweUp.restart(0, heighs[random.nextInt(3)]);
        }
    }

    private void plusVelocity() {
        if (points%5 == 0) {
            fbSpeed++;
            sbSpeed++;
            playerSpeed++;
            if (playerSpeed == maxSpeed) {
                isPowerReleased = true;
            }
            rbg1.setVelocity(new Vector2(fbSpeed, 0));
            rbg2.setVelocity(new Vector2(sbSpeed, 0));
            rbg3.setVelocity(new Vector2(playerSpeed, 0));
            rbg4.setVelocity(new Vector2(playerSpeed, 0));
            for (Enemy e: enemies) {
                e.setVelocity(playerSpeed);
            }
        }
    }

    private void releasePowerUp() {
    }

    public void jumpFarmer() {
        this.mike.setJumping(true);
    }

    // Bullets
    public void newBullet(float finalX, float finalY) {
        this.mike.shoot(finalX, finalY);
    }

    public void debugDrawCollisions(ShapeRenderer sRender) {
        for (Enemy e: enemies) {
            Rectangle r = e.getBoundingCircle();
            sRender.rect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        }

        for (Bullet b: mike.getBullets()) {
            Circle c = b.getBoundleCircle();
            sRender.circle(c.x, c.y, c.radius);
        }
        Circle cm = this.mike.getBoundingCircle();
        sRender.circle(cm.x, cm.y, cm.radius);

        Rectangle r = poweUp.getBoundingCircle();
        sRender.setColor(Color.BLUE);
        sRender.rect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }
}
