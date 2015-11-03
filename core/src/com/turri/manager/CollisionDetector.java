package com.turri.manager;

import com.turri.models.Bullet;
import com.turri.models.Enemy;
import com.turri.models.Mike;

/**
 * Created by alex torrents on 01/11/2015.
 */
public class CollisionDetector {
    private CollisionDetector() {}

    public static boolean getCollision(Bullet b, Enemy e) {
        if(e.getDead()) {
            return false;
        }
        if (b.getX() > e.getX() && b.getX() < e.getX() + e.getWidth()) {
            if (b.getY() > e.getY() && b.getY() < e.getY() + e.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public static boolean getCollision(Mike m, Enemy e) {

        return false;
    }
}
