package com.endlessrunner.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.entidades.FactoriaDeEntidades;
import com.endlessrunner.entidades.actorAventurero.ActorAventurero;
import com.endlessrunner.entidades.obstaculos.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aritz on 10/03/2018.
 */

public class GameScreen extends BaseScreen {



    /*
    VARIABLES
     */
    //Actor jugador
    private ActorAventurero jugador;

    //Mundo Box2D
    private World world;

    //Escenario Scene2D
    private Stage stage;

    //Sonidos y musicas
    private Sound sonidoSalto,sonidoMuerte;
    private Music musicaDeFondo;

    //Posicion de camara
    private Vector3 position;

    //Lista de obstaculos
    private List<EntidadSuelo> listaDeSuelo = new ArrayList<EntidadSuelo>();




    /*
    METODOS
     */

    //CONSTRUCTOR
    public GameScreen(EndlessRunner jokoa){
        super(jokoa);

        stage=new Stage(new FitViewport(640,360));
        position = new Vector3(stage.getCamera().position);

        world=new World(new Vector2(0,-10),true);
        world.setContactListener(new GameContactListener());

        //Sonidos y musicas...
        //sonidoSalto = jokoa.getManager().get("audio/jump.ogg");
        //sonidoMuerte = jokoa.getManager().get("audio/die.ogg");
        //musicaDeFondo = jokoa.getManager().get("audio/song.ogg");

    }


    @Override
    public void show() {

        FactoriaDeEntidades factory = new FactoriaDeEntidades(jokoa.getManager());

        // Create the player. It has an initial position.
        jugador = factory.crearJugador(world, new Vector2(1.5f, 1.5f));


        listaDeSuelo.add(factory.crearSuelo(world, 0, 1000, 1));
        //listaDeSuelo.add(factory.crearSuelo(world, 15, 10, 2));
        //listaDeSuelo.add(factory.crearSuelo(world, 30, 8, 2));


        for (EntidadSuelo floor : listaDeSuelo)
            stage.addActor(floor);

        stage.addActor(jugador);


        stage.getCamera().position.set(position);
        stage.getCamera().update();



/*
Musica de fondo
 */
        //musicaDeFondo.setVolume(0.75f);
        //musicaDeFondo.play();
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        world.step(1/60f/*delta*/,6,2);
        //comprobarColisiones();

        stage.draw();
    }


    @Override
    public void dispose() {

        stage.dispose();
        world.dispose();

        //texturaJugadorAventurero.dispose();
        //renderer.dispose();
    }

    @Override
    public void hide() {
        jugador.detach();
        jugador.remove();
    }





    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void resize(int width, int height) {
    }


    private class GameContactListener implements ContactListener {

        private boolean areCollided(Contact contact, Object userA, Object userB) {
            Object userDataA = contact.getFixtureA().getUserData();
            Object userDataB = contact.getFixtureB().getUserData();

            if (userDataA == null || userDataB == null) {
                return false;
            }

            return (userDataA.equals(userA) && userDataB.equals(userB)) ||
                    (userDataA.equals(userB) && userDataB.equals(userA));
        }


        @Override
        public void beginContact(Contact contact) {

            if (areCollided(contact, "jugador", "suelo")) {
                jugador.setSaltando(false);

                if (Gdx.input.isTouched()) {
                    //sonidoSalto.play();

                    jugador.setDebeSaltar(true);
                }
            }

            if (areCollided(contact, "player", "spike")) {

                if (jugador.isVivo()) {
                    jugador.setVivo(false);

                    // Sound feedback.
                    //musicaDeFondo.stop();
                    //sonidoMuerte.play();


                    stage.addAction(
                            Actions.sequence(
                                    Actions.delay(1.5f),
                                    Actions.run(new Runnable() {

                                        @Override
                                        public void run() {
                                            jokoa.setScreen(jokoa.gameOverScreen);
                                        }
                                    })
                            )
                    );
                }
            }
        }



        @Override
        public void endContact(Contact contact) {

            if (areCollided(contact, "jugador", "suelo")) {
                if (jugador.isVivo()) {
                    //sonidoSalto.play();
                }
            }
        }

        // Here two lonely methods that I don't use but have to override anyway.
        @Override public void preSolve(Contact contact, Manifold oldManifold) { }
        @Override public void postSolve(Contact contact, ContactImpulse impulse) { }
    }

}
