package com.turri.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.turri.manager.ImageManager;

/**
 * Created by alex on 12/10/2015.
 */
public class TurriListener implements GestureDetector.GestureListener {
    ImageManager imageManager;

    public TurriListener() {
        imageManager = ImageManager.sharedManager();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        // Cut the screen in 4 segments, the first segment, the left, is for mike actions
        if (x < Gdx.graphics.getWidth()/4) {
            imageManager.jumpFarmer();
        } else {
            imageManager.newBullet(x, y);
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom (float originalDistance, float currentDistance){

        return false;
    }

    @Override
    public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){

        return false;
    }
}

