package com.turri.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.turri.manager.GameState;
import com.turri.manager.ImageManager;

/**
 * Created by alex torrents on 12/10/2015.
 */
public class TurriListener implements GestureDetector.GestureListener {
    ImageManager imageManager;
    private GameState gameState;

    public TurriListener() {
        imageManager = ImageManager.sharedManager();
        gameState = GameState.sharedState();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (gameState.getGameState() == GameState.MENU) {
            gameState.setState(GameState.PLAY);
            return false;
        }
        // Cut the screen in 4 segments, the first segment, the left, is for mike action
        if (x < Gdx.graphics.getWidth()/4) {
            imageManager.jumpFarmer();
        } else {
            // Flip the coordinates because libgdx use (0,0) in left down corner
            // but the inputs are in left up corner
            y = Gdx.graphics.getHeight() - y;
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

