package com.turri.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.turri.models.Bullet;
import com.turri.models.Enemy;
import com.turri.models.Mike;
import com.turri.models.PowerUp;

/**
 * Created by alex torrents on 01/11/2015.
 */
public class CollisionDetector {
    private CollisionDetector() {}

    public static boolean getCollision(Bullet b, Enemy e) {
        if(e.getDead()) {
            return false;
        }
        return collision(b.getBoundleCircle(), e.getBoundingCircle());
    }

    public static boolean getCollision(Mike m, Enemy e) {
       return collision(m.getBoundingCircle(), e.getBoundingCircle());
    }

    public static boolean getCollision(Mike mike, PowerUp poweUp) {
        return collision(mike.getBoundingCircle(), poweUp.getBoundingCircle());
    }

    // Private method for collisions
    private static boolean collision(Circle c1, Circle c2) {
        return Intersector.overlaps(c1, c2);
    }
    private static boolean collision(Circle c, Rectangle r) {
        return Intersector.overlaps(c, r);
    }
}
