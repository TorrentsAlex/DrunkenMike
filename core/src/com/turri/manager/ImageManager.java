package com.turri.manager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.turri.interfaces.imageManagerInterface;
import com.turri.models.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class ImageManager {

    private static ImageManager imageManager = null;

    private float screenWidth;
    private float screenHeight;

    private float width20percent;
    private float height20percent;
    private float height40percent;
    private float height60percent;
    private float height80percent;
    //
    private Farmer farmer;
    //	private List<Enemy> enemies;
//	private List<Enemy> straws;
//	private List<Bullet> bullets = new ArrayList<Bullet>();
    private Background[] listBackground;
    private Background[] listBackground1;
    private float backgroundWidth = 0;

    private int currentBackground = 0;
    private int currentBackground1 = 0;

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

    public ImageManager(imageManagerInterface handler) {
        this.managerInterface = handler;
    }

    public void loadImages(float screeenWidth,
                           float screenHeight) {
        random = new Random();
        this.screenWidth = screeenWidth;
        this.screenHeight = screenHeight;
        width20percent = screeenWidth * 0.10f;

        height20percent = screenHeight * 0.15f;
        height40percent = screenHeight * 0.30f;
        height60percent = screenHeight * 0.50f;
        height80percent = screenHeight * 0.70f;

        // capa1
        listBackground = new Background[2];
        listBackground1 = new Background[2];

        //Background background = new Background(context, 0, 0, R.drawable.capa1);
        listBackground[0] = new Background(0, 0, "background1_1.png");
        backgroundWidth = listBackground[0].getWitdh();
        listBackground[1] = new Background((int) backgroundWidth, 0, "background1_1.png");

        listBackground1[0] = new Background(0, 0, "background2_2.png");
        listBackground1[1] = new Background((int)backgroundWidth, 0, "background2_2.png");
//
//		float[] heighs = { height20percent, height40percent, height60percent};

        // START ENEMIES
//		enemies = new ArrayList<Enemy>();
//		for (int i = 0; i < 20 ; i++) {
//			maxXEnemy += random.nextInt(1000);
//			enemies.add(new Enemy(context, screenWidth + maxXEnemy,
//					heighs[random.nextInt(3)], "chick", R.drawable.chicken));
//		}

        farmer = new Farmer(width20percent, height80percent, "mikespritesheet.png");
//		farmer.setCostumes(farmerCostumes);

//		for (int i=0; i<5; i++) {
//			maxXStraws += random.nextInt(2500);
//			straws.add(new Enemy(context, screeenWidth+maxXStraws+3000,
//							height80percent,"straws", R.drawable.straw));
//		}

        // this callback mark when the enemies, player and background has been
        // loaded

        this.managerInterface.imagesLoaded();
    }

    // Characters
    public Farmer getFarmer() {
        return this.farmer;
    }

    public void drawFarmer() {
        this.farmer.draw();
    }
//
//	public List<Enemy> getEnemies() {
//		return this.enemies;
//	}

    //	public List<Enemy> getStraws() {
//		return this.straws;
//	}
//
    public void drawBackground(Batch batch) {
        for (Background bc : listBackground) {
            batch.draw(bc.getTexture(), bc.getX(), bc.getY());
        }
        for (Background bc2 : listBackground1) {
            batch.draw(bc2.getTexture(), bc2.getX(), bc2.getY());
        }
    }

    public void updateBackground () {
        for (Background bc : listBackground) {
            bc.moveBackground(4);
        }
        for (Background bc2 : listBackground1) {
            bc2.moveBackground(7);
        }
        if (-1 * listBackground[currentBackground].getX() >= listBackground[currentBackground]
                .getWitdh()) {
            listBackground[currentBackground].setX((int) backgroundWidth);
            currentBackground++;
            if (currentBackground >= 2) {
                currentBackground = 0;
            }
        }
        if (-1 * listBackground1[currentBackground1].getX() >= listBackground1[currentBackground1]
                .getWitdh()) {
            listBackground1[currentBackground1].setX((int) backgroundWidth);
            currentBackground1++;
            if (currentBackground1 >= 2) {
                currentBackground1 = 0;
            }
        }

    }


//		if (isFinishRunnable) {
//			isFinishRunnable = false;
//			new Runnable() {
//				public void run() {
//					for (Background bcImage : listBackground) {
//						c.drawBitmap(bcImage.getBitmap(),
//								bcImage.getX(),
//								bcImage.getY(), null);
//						bcImage.moveBackground(4);
//					}
//					for (Background bcImage : listBackground1) {
//						c.drawBitmap(
//								bcImage.getBitmap(),
//								bcImage.getX(),
//								bcImage.getY(), null);
//
//						bcImage.moveBackground(7);
//					}
//					// current 1
//					if (-1 * listBackground[currentBackground].getX() >= listBackground[currentBackground]
//							.getWitdh()) {
//						listBackground[currentBackground].setX((int)backgroundWidth);
//						currentBackground++;
//						if (currentBackground >= 2) {
//							currentBackground = 0;
//						}
//					}
//					// current 2
//					if (-1 * listBackground1[currentBackground1].getX() >= listBackground1[currentBackground1]
//							.getWitdh()) {
//						listBackground1[currentBackground1].setX((int)backgroundWidth);
//						currentBackground1++;
//						if (currentBackground1 >= 2) {
//							currentBackground1 = 0;
//						}
//					}
//					isFinishRunnable = true;
//				}
//			}.run();
//		}

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

    // Views size
    public float getScreenWidth() {
        return this.screenWidth;
    }

    public float getScreenHeight() {
        return this.screenHeight;
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
