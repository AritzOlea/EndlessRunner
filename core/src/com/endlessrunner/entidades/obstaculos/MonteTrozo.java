package com.endlessrunner.entidades.obstaculos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Jongui on 27/03/2018.
 */

public class MonteTrozo extends Actor {

    private Texture monte;

    private int anchuraPersonaje=64,alturaPersonaje=64;

    public MonteTrozo(Texture monte){
        this.monte=monte;
        setSize(anchuraPersonaje,alturaPersonaje);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(monte,getX(),getY(),anchuraPersonaje,alturaPersonaje);
    }



    @Override
    public void act(float delta) {

        setX(getX()-250*delta);

    }


}
