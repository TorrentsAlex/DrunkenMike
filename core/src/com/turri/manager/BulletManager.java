package com.turri.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.turri.models.Bullet;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alex Torrents on 31/10/2015.
 */
public class BulletManager {

    private static BulletManager bManager;

    private static int TIMER_BULLET = 500;

    private boolean newBullet = true;

    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Bullet> bulletsToRemove = new ArrayList<Bullet>();

    // The projectile name i use
    private String projectileString = "beerProjectile.png";

    private BulletManager() {

    }

    public static BulletManager shareBulletManager() {
        if (bManager == null) {
            bManager = new BulletManager();
        }
        return bManager;
    }


    public void createNewBullet(float originX, float originY, float finalX, float finalY) {
        if (newBullet) {
            newBullet = false;
            bullets.add(new Bullet(
			/* initial X */ originX,
					/* initial Y */originY,
					/* FINAL X */finalX,
					/* FINAL Y */finalY, projectileString));
            // Count the time for the next projectile
            timerNewBullet();
        }
    }

    public void drawBullets(SpriteBatch batch) {
        for (Bullet b : bullets) {
            b.draw(batch);
        }
    }

    public void updateBullets() {
        for (Bullet b : bullets) {
            b.update();
            if (b.getRemove()) {
                this.bulletsToRemove.add(b);
            }
        }
        // Remove all bullets
        this.bullets.removeAll(this.bulletsToRemove);
    }

    private void timerNewBullet() {
        new Runnable() {
            @Override
            public void run() {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        newBullet = true;
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, TIMER_BULLET);
            }
        }.run();
    }

    public List<Bullet> getBullets() {
        return this.bullets;
    }

}
