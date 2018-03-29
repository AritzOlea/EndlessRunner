package com.endlessrunner.entidades.actorAventurero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.endlessrunner.Pantallas.GameScreen;

import org.w3c.dom.Text;

import static com.endlessrunner.Pantallas.GameScreen.velocidadJugador;
import static com.endlessrunner.ayuda.Constantes.IMPULSO_DE_SALTO;
import static com.endlessrunner.ayuda.Constantes.PIXELS_POR_METRO;
import static com.endlessrunner.ayuda.Constantes.VELOCIDAD_JUGADOR;

/**
 * Created by Jongui on 27/03/2018.
 */

public class ActorAventurero extends Actor{

    //private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;

    //Tamaño
    private int anchuraPersonaje=64,alturaPersonaje=128;


    //Condiciones
    private boolean vivo;
    public boolean isVivo() {return vivo;}
    public void setVivo(boolean vivo) {this.vivo = vivo;}

    private boolean saltandoUno;
    public boolean isSaltandoUno() {return saltandoUno;}
    public void setSaltandoUno(boolean saltando) {this.saltandoUno = saltando;}

    private boolean saltandoDos;
    public boolean isSaltandoDos() {return saltandoDos;}
    public void setSaltandoDos(boolean saltando) {this.saltandoDos = saltando;}

    private boolean debeSaltar;
    public boolean isDebeSaltar() {return debeSaltar;}
    public void setDebeSaltar(boolean debeSaltar) {this.debeSaltar = debeSaltar;}

    private boolean pegadoAlSuelo;
    public boolean isPegadoAlSuelo() {return pegadoAlSuelo;}
    public void setPegadoAlSuelo(boolean pegadoAlSuelo) {this.pegadoAlSuelo = pegadoAlSuelo;}

    //Texturas del jugador
    Texture[] corriendoTextures,saltandoTextures, muertoTextures;
    int posicionTextura,cadaTexturaXveces;//corriendo
    int posicionTexturaMuerto ,cadaTexturaMuertoXveces;//morir

    public ActorAventurero(World world, Texture texture, Vector2 position, Texture[] corriendo,Texture[] saltandoTex, Texture[] muertoTex){
        this.vivo=true;
        this.saltandoUno=false;
        this.saltandoDos=false;
        this.debeSaltar=false;
        this.pegadoAlSuelo=false;

        //this.texture=texture;
        this.corriendoTextures=corriendo;
        this.world=world;
        this.posicionTextura=0;
        this.posicionTexturaMuerto=0;
        this.cadaTexturaXveces=0;
        this.cadaTexturaMuertoXveces=0;
        this.saltandoTextures=saltandoTex;
        this.muertoTextures=muertoTex;


        //Body jugador
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type=BodyDef.BodyType.DynamicBody;
        body=world.createBody(def);


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

        if(saltandoUno){
            if(cadaTexturaXveces<4){
                cadaTexturaXveces++;
            }else{
                cadaTexturaXveces=0;
                if(posicionTextura+1==corriendoTextures.length)posicionTextura=-1;
                posicionTextura++;
            }
            batch.draw(saltandoTextures[posicionTextura],getX(),getY(),getWidth(),getHeight());

        }else if(vivo){
            if(cadaTexturaXveces<4){
                cadaTexturaXveces++;
            }else{
                cadaTexturaXveces=0;
                if(posicionTextura+1==corriendoTextures.length)posicionTextura=-1;
                posicionTextura++;
            }
            batch.draw(corriendoTextures[posicionTextura],getX(),getY(),getWidth(),getHeight());
        }else{
            if (posicionTexturaMuerto+1 != muertoTextures.length) {
                if (cadaTexturaXveces < 1) {
                    cadaTexturaXveces++;
                } else {
                    cadaTexturaXveces = 0;
                    posicionTexturaMuerto++;
                }
            }
            batch.draw(muertoTextures[posicionTexturaMuerto],getX(),getY(),getWidth(),getHeight());
        }





    }

    private boolean sum=true;

    @Override
    public void act(float delta) {

        if (Gdx.input.justTouched()) {
            salto();
        }

        /*if (debeSaltar) {
            debeSaltar = false;
            salto();
        }*/

        if (vivo) {
            float velocidadY = body.getLinearVelocity().y;
            //velocidadJugador= body.getLinearVelocity().y;


            //body.setLinearVelocity(VELOCIDAD_JUGADOR, velocidadY);

            if(sum){
                sum=false;
                body.setLinearVelocity(VELOCIDAD_JUGADOR*(1+delta-0.01f), velocidadY);
            }else{
                sum=true;
                body.setLinearVelocity(VELOCIDAD_JUGADOR, velocidadY);
            }

        }

        if (saltandoUno) {
            body.applyForceToCenter(0, - IMPULSO_DE_SALTO * 1.15f, true);
        }
    }



    public void salto() {

        if (!saltandoUno && vivo){
            saltandoUno = true;

            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, IMPULSO_DE_SALTO, position.x, position.y, true);
        }else if(saltandoUno && !saltandoDos) {
            saltandoDos = true;
            Vector2 vel = body.getLinearVelocity();
            vel.y = 0f;
            body.setLinearVelocity(vel);
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, IMPULSO_DE_SALTO*1.2f, position.x, position.y, true);
            GameScreen.sonidoSalto.play();
        }
    }


    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
