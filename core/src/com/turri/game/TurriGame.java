package com.turri.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.turri.inputs.TurriListener;
import com.turri.interfaces.imageManagerInterface;
import com.turri.manager.GameState;
import com.turri.manager.ImageManager;

public class TurriGame extends ApplicationAdapter implements imageManagerInterface {
	SpriteBatch batch;
	ImageManager manager;

	boolean imagesLoaded = false;
	private static TurriGame game;
	private GameState gameState;
	private Menu menu;

	public static TurriGame sharedGame() {
		if (game == null) {
			game = new TurriGame();
		}
		return game;
	}
	@Override
	public void create () {
		gameState = GameState.sharedState();
		gameState.setState(GameState.MENU);
		batch = new SpriteBatch();
		menu = new Menu();

		manager = ImageManager.sharedManager();
        manager.setImageManagerInterface(this);
        manager.loadImages(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(new GestureDetector(new TurriListener()));
	}

	@Override
	public void render () {
		//TODO:: change this with play states
		switch(gameState.getGameState()) {
			case GameState.MENU:
				drawMenu();
				break;
			case GameState.PLAY:
				if (!imagesLoaded) {
					return;
				}
				drawGame();
				break;
			case GameState.EXIT:
				break;
		}
	}
	private void drawMenu() {
		clearScreen();
		batch.begin();
		menu.drawMenu(batch);
		batch.end();
	}

	private void drawGame() {
		manager.update();

		manager.physics();

		clearScreen();
		batch.begin();
		manager.draw(batch);
		batch.end();

	}

	public void imagesLoaded() {
		imagesLoaded = true;
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
