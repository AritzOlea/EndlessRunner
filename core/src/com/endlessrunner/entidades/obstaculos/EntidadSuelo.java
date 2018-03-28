package com.endlessrunner.entidades.obstaculos;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.endlessrunner.ayuda.Constantes.PIXELS_POR_METRO;

/**
 * Created by Jongui on 27/03/2018.
 */

public class EntidadSuelo extends Actor {

    private Texture floor, overfloor;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;


    public EntidadSuelo(World world, Texture floor, Texture overfloor, float x, float width, float y) {
        this.world = world;
        this.floor = floor;
        this.overfloor = overfloor;

        // Create the floor body.
        BodyDef def = new BodyDef();
        def.position.set(x + width / 2, y - 0.5f);
        body = world.createBody(def);

        // Give it a box shape.
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, 0.5f);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("suelo");
        box.dispose();


        BodyDef leftDef = new BodyDef();
        leftDef.position.set(x, y - 0.55f);
        leftBody = world.createBody(leftDef);


        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.02f, 0.45f);
        leftFixture = leftBody.createFixture(leftBox, 1);
        leftFixture.setUserData("monte");
        leftBox.dispose();


        setSize(width * PIXELS_POR_METRO, PIXELS_POR_METRO);
        setPosition(x * PIXELS_POR_METRO, (y - 1) * PIXELS_POR_METRO);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Render both textures.
        batch.draw(floor, getX(), getY(), getWidth(), getHeight());
        batch.draw(overfloor, getX(), getY() + 0.9f * getHeight(), getWidth(), 0.1f * getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
