package com.endlessrunner.entidades.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.endlessrunner.Pantallas.partida_basica.GameScreen;

import static com.endlessrunner.ayuda.Constantes.PIXELS_POR_METRO;

/**
 * Created by Jongui on 29/03/2018.
 */

public class EntidadSetaPuntos  extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private float x,y;
    private boolean sinCoger;


    public EntidadSetaPuntos(World world, Texture texture, float x, float y) {
        this.world = world;
        this.texture = texture;
        this.x=x;
        this.y=y;
        this.sinCoger=true;

        /*BodyDef def = new BodyDef();
        def.position.set(x, y + 0.5f);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f /3, -0.5f /3);
        vertices[1] = new Vector2(0.5f /3, -0.5f /3);
        vertices[2] = new Vector2(0, 0.5f /3);
        box.set(vertices);
        fixture = body.createFixture(box, 1 / 3);
        fixture.setUserData("setaPuntos");
        box.dispose();*/

        setPosition((x - 0.5f) * PIXELS_POR_METRO, y * PIXELS_POR_METRO);
        setSize(PIXELS_POR_METRO / 3, PIXELS_POR_METRO /3);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(sinCoger){
            if((GameScreen.jugador.getX() + GameScreen.jugador.getWidth()) > getX() && GameScreen.jugador.getX() < (getX() + getWidth())
                    &&
                    (GameScreen.jugador.getY() + GameScreen.jugador.getHeight()) > getY() && GameScreen.jugador.getY() < (getY() + getHeight())){

                int puntosGanados = (int) (Math.random() * 8) + 10;
                GameScreen.timerPuntuacion = 1;
                GameScreen.labelPuntosConseguidos.setText(" +"+puntosGanados+"         ");

                GameScreen.puntuacion=GameScreen.puntuacion+puntosGanados;
                GameScreen.sonidoPunto.play();
                GameScreen.puntuacionTextoLabel.setText(String.format("Puntuacion: %03d", GameScreen.puntuacion));
                sinCoger=false;
                GameScreen.cantidadSetas++;
            }else{
                batch.draw(texture, getX(), getY(), getWidth(), getHeight());
            }
        }


    }

    public void detach() {
        //body.destroyFixture(fixture);
        //world.destroyBody(body);
    }
}