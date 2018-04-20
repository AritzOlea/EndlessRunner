package com.endlessrunner.entidades.objetos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.endlessrunner.Pantallas.partida_basica.GameScreen;
import com.endlessrunner.ayuda.Ajustes;

import static com.endlessrunner.ayuda.Constantes.PIXELS_POR_METRO;

/**
 * Created by Jongui on 29/03/2018.
 */

public class EntidadSetaSinSalto  extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean sinCoger;

    public EntidadSetaSinSalto(World world, Texture texture, float x, float y) {
        this.world = world;
        this.texture = texture;
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
        fixture.setUserData("setaSinSalto");
        box.dispose();*/

        setPosition((x - 0.5f) * PIXELS_POR_METRO, y * PIXELS_POR_METRO);
        setSize(PIXELS_POR_METRO*2, PIXELS_POR_METRO*2);
    }

    public boolean areCollided(Contact contact, Object userA, Object userB) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA == null || userDataB == null) {
            return false;
        }

        return (userDataA.equals(userA) && userDataB.equals(userB)) ||
                (userDataA.equals(userB) && userDataB.equals(userA));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

        if(sinCoger && GameScreen.jugador.isVivo()) {
            if (getX() + getWidth() > GameScreen.jugador.getX() && GameScreen.jugador.getX() > getX()
                    && GameScreen.jugadorEnElSuelo
                    ) {

                GameScreen.timer = 2;
                GameScreen.cantidadColas++;
                GameScreen.jugador.setPegadoAlSuelo(true);
                GameScreen.labelTiempo.setText(String.format("Cuenta atras: %03d", GameScreen.timer));
                GameScreen.labelTiempo.setColor(Color.RED);
                sinCoger = false;
                if(Ajustes.Sonidos) {
                    GameScreen.sonidoCharco.play();
                }

            } else {

                batch.draw(texture, getX(), getY(), getWidth(), getHeight());

            }
        }

    }

    public void detach() {
        //body.destroyFixture(fixture);
        //world.destroyBody(body);
    }
}