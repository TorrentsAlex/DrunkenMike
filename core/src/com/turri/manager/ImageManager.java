package com.turri.manager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.turri.interfaces.imageManagerInterface;
import com.turri.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ImageManager {

    private static ImageManager imageManager ;

    private Farmer farmer;
    private List<Enemy> enemies;

//	private List<Enemy> straws;
//	private List<Bullet> bullets = new ArrayList<Bullet>();
    private Background[] aBackgrounds;
    private Background[] aBackgrounds1;
    private float backgroundWidth = 0;

    private int maxXEnemy = 0;
    private int maxXStraws = 0;

    private Random random;
    private boolean newBullet = true;
    private boolean isFinishRunnable = true;

    private static int TIMER_BULLET = 1000;

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
        aBackgrounds[1] = new Background((int) backgroundWidth, 0, "background1_1.png",Background.BackgroundType.LAST_BACKGROUND);

        aBackgrounds1[0] = new Background(0, 0, "background2_2.png",Background.BackgroundType.FIRST_BACKGROUND);
        aBackgrounds1[1] = new Background((int)backgroundWidth, 0, "background2_2.png",Background.BackgroundType.FIRST_BACKGROUND);

		float[] heighs = {height20percent, height40percent, height60percent};

        // START ENEMIES
		enemies = new ArrayList<Enemy>();
		for (int i = 0; i < 20 ; i++) {
			maxXEnemy += random.nextInt(1000);
			enemies.add(new Enemy(screenWidth + maxXEnemy,
					heighs[random.nextInt(3)], "cow.png"));
		}

        farmer = new Farmer(width20percent, height20percent, "mikespritesheet.png");

//		for (int i=0; i<5; i++) {
//			maxXStraws += random.nextInt(2500);
//			straws.add(new Enemy(context, screeenWidth+maxXStraws+3000,
//							height80percent,"straws", R.drawable.straw));
//		}

        // this callback call when the enemies, player and background has been loaded
        this.managerInterface.imagesLoaded();
    }

    // Character
    public void drawMike(SpriteBatch spriteBatch) {
        this.farmer.draw(spriteBatch);
    }

    public void jumpFarmer() {
        this.farmer.setJumping(true);
    }

    // Enemy method
    public void drawEnemies(Batch batch) {
        for (Enemy e : enemies) {
            e.drawEnemy(batch);
        }
    }

    // Background methods
    public void drawBackground(Batch batch) {
        for (Background bc : aBackgrounds) {
            bc.drawBackground(batch);
        }
        for (Background bc2 : aBackgrounds1) {
            bc2.drawBackground(batch);
        }
    }

//	// Bullets
//	public void newBullet(float finalX, float finalY) {
//		if (newBullet /*&& octoApp.getTotalBullets() > 0*/) {
//			newBullet = false;
//			bullets.add(new Bullet(mContext,
//					/* initial X */(int) width20percent
//							+ farmer.getBitmap().getWidth(),
//					/* initial Y */(int) farmer.getY() + 90,
//					/* FINAL X */finalX,
//					/* FINAL Y */finalY, R.drawable.projectile));
//
//			// Sound bullet
//			timerNewBullet();
//		}
//	}
//
//	public void timerNewBullet() {
//		new Runnable() {
//			@Override
//			public void run() {
//				TimerTask task = new TimerTask() {
//					@Override
//					public void run() {
//						Looper.prepare();
//						newBullet = true;
//						Looper.loop();
//					}
//				};
//				Timer timer = new Timer();
//				timer.schedule(task, TIMER_BULLET);
//			}
//		}.run();
//	}

//	public void drawBullets(final Canvas c) {
//		final List<Bullet> toRemove = new ArrayList<Bullet>();
//		new Runnable() {
//
//			@Override
//			public void run() {
//
//				for (Bullet b : getBullets()) {
//					// DRAW the bullets
//					c.drawBitmap(b.getBitmap(), b.getX(), b.getY(), null);
//					// MOVE the bullets
//					b.move();
//					if (b.getX() >= screenWidth) {
//						toRemove.add(b);
//					}
//				}
//			}
//		}.run();
//
//		removeBullet(toRemove);
//	}

//	public void removeBullet(List<Bullet> arg0) {
//		bullets.removeAll(arg0);
//	}

//	public List<Bullet> getBullets() {
//		return bullets;
//	}

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
