package com.turri.manager;

/**
 * Created by alex torrents on 04/11/2015.
 */
public class GameState {

    // GAME STATES
    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int EXIT = 2;

    private int GAME_STATE;
    private static GameState gameState;

    private GameState() {

    }

    public static GameState sharedState() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    public void setState(int state) {
        GAME_STATE = state;
    }

    public int getGameState() {
        return this.GAME_STATE;
    }
}
