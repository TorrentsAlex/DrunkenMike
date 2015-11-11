package com.turri.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

	private ShapeRenderer sRender;

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
		sRender = new ShapeRenderer();

		manager = ImageManager.sharedManager();
        manager.setImageManagerInterface(this);
		Gdx.input.setInputProcessor(new GestureDetector(new TurriListener()));
		manager.loadImages(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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

		manager.drawBackground();

		//collisionDebugDraw();

		batch.begin();
		manager.draw(batch);
		batch.end();
		manager.drawForeGround();

	}

	private void collisionDebugDraw() {
		sRender.begin(ShapeRenderer.ShapeType.Filled);
		sRender.setColor(Color.RED);
		manager.debugDrawCollisions(sRender);
		sRender.end();
	}

	public void imagesLoaded() {
		Gdx.app.log("Mike", "imagesLoaded");
		imagesLoaded = true;
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
