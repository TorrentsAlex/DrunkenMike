package com.turri.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.logging.FileHandler;

/**
 * Created by alex torrents on 04/11/2015.
 */
public class Menu {

    // Menus textures
    Texture menuTitle;
    Texture mike;
    Texture background;
    Texture background1;
    Texture background2;
    Texture background3;
    BitmapFont font;

    public Menu() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BEER.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 100;
        parameter.color = Color.GOLD;
        parameter.shadowColor = Color.BLACK;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        font = generator.generateFont(parameter);
        generator.dispose();

        menuTitle = new Texture(Gdx.files.internal("titlemike.png"));
        mike = new Texture(Gdx.files.internal("Run_0000_1.png"));
        background = new Texture(Gdx.files.internal("background1_1.png"));
        background1 = new Texture(Gdx.files.internal("background2_1.png"));
        background2 = new Texture(Gdx.files.internal("background3_1.png"));
        background3 = new Texture(Gdx.files.internal("Vallas.png"));
    }

    public void drawMenu(SpriteBatch batch) {
        batch.draw(background, 0, 0);
        batch.draw(background1, 0, 0);
        batch.draw(background2, 0, 0);
        batch.draw(mike, Gdx.graphics.getWidth() * 0.10f, Gdx.graphics.getHeight() * 0.15f - 50);
        batch.draw(menuTitle, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 100);
        batch.draw(background3, 0, 20);

        font.draw(batch, "Tap to play", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-font.getSpaceWidth()*"Tap to play".length()/2/2);

    }
}
