package com.endlessrunner.entidades.actorAventurero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.endlessrunner.ayuda.Constantes.IMPULSO_DE_SALTO;
import static com.endlessrunner.ayuda.Constantes.PIXELS_POR_METRO;
import static com.endlessrunner.ayuda.Constantes.VELOCIDAD_JUGADOR;

/**
 * Created by Jongui on 27/03/2018.
 */

public class ActorAventurero extends Actor{

    private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;

    //Tamaño
    private int anchuraPersonaje=64,alturaPersonaje=128;


    //Condiciones
    private boolean vivo;
    public boolean isVivo() {return vivo;}
    public void setVivo(boolean vivo) {this.vivo = vivo;}

    private boolean saltando;
    public boolean isSaltando() {return saltando;}
    public void setSaltando(boolean saltando) {this.saltando = saltando;}

    private boolean debeSaltar;
    public boolean isDebeSaltar() {return debeSaltar;}
    public void setDebeSaltar(boolean debeSaltar) {this.debeSaltar = debeSaltar;}



    public ActorAventurero(World world, Texture texture, Vector2 position){
        this.vivo=true;
        this.saltando=false;
        this.debeSaltar=false;

        this.texture=texture;
        this.world=world;


        //Body jugador
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type=BodyDef.BodyType.DynamicBody;
        this.body=world.createBody(def);


        //Una forma al jugador
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f,0.5f);
        fixture=body.createFixture(shape,3);
        fixture.setUserData("jugador");
        shape.dispose();


        //Establecer tamaño
        setSize(PIXELS_POR_METRO,PIXELS_POR_METRO);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * PIXELS_POR_METRO,
                    (body.getPosition().y - 0.5f) * PIXELS_POR_METRO);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void act(float delta) {

        if (Gdx.input.justTouched()) {
            salto();
        }

        if (debeSaltar) {
            debeSaltar = false;
            salto();
        }

        if (vivo) {
            float velocidadY = body.getLinearVelocity().y;
            body.setLinearVelocity(VELOCIDAD_JUGADOR, velocidadY);
        }

        if (saltando) {
            body.applyForceToCenter(0, IMPULSO_DE_SALTO * 1.15f, true);
        }
    }



    public void salto() {

        if (!saltando && vivo) {
            saltando = true;

            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, IMPULSO_DE_SALTO, position.x, position.y, true);
        }
    }


    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
