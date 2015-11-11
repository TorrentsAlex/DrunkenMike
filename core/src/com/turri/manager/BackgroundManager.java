package com.turri.manager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.turri.models.Background;

/**
 * Created by alex torrents on 05/11/2015.
 */
public class BackgroundManager {
    private static BackgroundManager backgroundManager;
    private Background[] aBackgrounds;
    private Background[] aBackgrounds1;
    private Background[] aBackgrounds2;
    private Background[] aBackgrounds3;

    private BackgroundManager() {}

    public static BackgroundManager sharedBackgroundManager() {
        if(backgroundManager == null) {
            backgroundManager = new BackgroundManager();
        }
        return backgroundManager;
    }


    public void initBackgrounds() {
        // capa1
        aBackgrounds = new Background[2];
        aBackgrounds1 = new Background[2];
        aBackgrounds2 = new Background[3];
        aBackgrounds3 = new Background[2];

        //Background background = new Background(context, 0, 0, R.drawable.capa1);
        aBackgrounds[0] = new Background(0, 0, "background1_1.png", Background.BackgroundType.LAST_BACKGROUND);
        float  backgroundWidth = aBackgrounds[0].getWitdh();
        aBackgrounds[1] = new Background(backgroundWidth, 0, "background1_2.png", Background.BackgroundType.LAST_BACKGROUND);

        aBackgrounds1[0] = new Background(0, 0, "background2_1.png", Background.BackgroundType.MIDDLE_BACKGROUND);
        aBackgrounds1[1] = new Background(aBackgrounds1[0].getWitdh(), 0, "background.png", Background.BackgroundType.MIDDLE_BACKGROUND);

        aBackgrounds2[0] = new Background(0,0, "background3_1.png", Background.BackgroundType.FIRST_BACKGROUND);
        aBackgrounds2[1] = new Background(backgroundWidth,0, "background3_2.png", Background.BackgroundType.FIRST_BACKGROUND);
        aBackgrounds2[2] = new Background(backgroundWidth*2,0, "background3_3.png", Background.BackgroundType.FIRST_BACKGROUND);

        aBackgrounds3[0] = new Background(0,20, "Vallas.png", Background.BackgroundType.FIRST_BACKGROUND);
        aBackgrounds3[1] = new Background(2600,20, "Vallas.png", Background.BackgroundType.FIRST_BACKGROUND);
    }

    public void drawBackground(SpriteBatch batch) {
        // First draw the background
        for (Background bc : aBackgrounds) {
            bc.drawBackground(batch);
        }

        for (Background bc2 : aBackgrounds1) {
            bc2.drawBackground(batch);
        }

        for (Background bc: aBackgrounds2) {
            bc.drawBackground(batch);
        }
    }

    public void drawForeground(SpriteBatch batch) {
        // Draw Fences
        for (Background bc: aBackgrounds3) {
            bc.drawBackground(batch);
        }
    }

    public void updateBackground() {
        // First draw the background
        for (Background bc : aBackgrounds) {
            bc.updateBackground();
        }

        for (Background bc2 : aBackgrounds1) {
            bc2.updateBackground();
        }

        for (Background bc: aBackgrounds2) {
            bc.updateBackground();
        }
        for (Background bc: aBackgrounds3) {
            bc.updateBackground();
        }
    }

    public void setVelocity() {}

}
