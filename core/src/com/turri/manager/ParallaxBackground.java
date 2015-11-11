package com.turri.manager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.turri.models.ParallaxLayer;


/**
 * Created by alex torrents on 05/11/2015.
*/
public class ParallaxBackground {

    private ParallaxLayer[] layers;
    private Camera camera;
    private SpriteBatch batch;
    private Vector2 speed = new Vector2();

    /**
     * @param layers  The  background layers
     * @param width   The screenWith
     * @param height The screenHeight
     * @param speed A Vector2 attribute to point out the x and y speed
     */
    public ParallaxBackground(ParallaxLayer[] layers,float width,float height,Vector2 speed){
        this.layers = layers;
        this.speed.set(speed);
        camera = new OrthographicCamera(width, height);
        batch = new SpriteBatch();
    }

    public void render(float delta){
        this.camera.position.add(speed.x*delta,speed.y*delta, 0);
        for(ParallaxLayer layer:layers){
            batch.setProjectionMatrix(camera.projection);
            batch.begin();
            float currentX = - camera.position.x*layer.parallaxRatio.x % ( layer.region.getWidth() + layer.padding.x) ;

            if( speed.x < 0 )currentX += -( layer.region.getWidth() + layer.padding.x);
            do{
                float currentY = - camera.position.y*layer.parallaxRatio.y % ( layer.region.getHeight() + layer.padding.y) ;
                if( speed.y < 0 )currentY += - (layer.region.getHeight()+layer.padding.y);
                do{
                    batch.draw(layer.region,
                            -this.camera.viewportWidth/2+currentX + layer.startPosition.x ,
                            -this.camera.viewportHeight/2 + currentY +layer.startPosition.y);
                    currentY += ( layer.region.getHeight() + layer.padding.y );
                }while( currentY < camera.viewportHeight);
                currentX += ( layer.region.getWidth()+ layer.padding.x);
            }while( currentX < camera.viewportWidth);
            batch.end();
        }
    }

    public void setVelocity(Vector2 speed) {
        this.speed = speed;
    }
}