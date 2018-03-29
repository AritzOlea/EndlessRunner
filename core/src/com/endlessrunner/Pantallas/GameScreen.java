package com.endlessrunner.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.entidades.FactoriaDeEntidades;
import com.endlessrunner.entidades.actorAventurero.ActorAventurero;
import com.endlessrunner.entidades.obstaculos.*;

import java.util.ArrayList;
import java.util.List;

import static com.endlessrunner.ayuda.Constantes.PIXELS_POR_METRO;
import static com.endlessrunner.ayuda.Constantes.VELOCIDAD_JUGADOR;

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

    //Listas
    private List<EntidadSuelo> listaDeSuelo = new ArrayList<EntidadSuelo>();
    private List<EntidadMonte> listaDeMontes = new ArrayList<EntidadMonte>();


    Table table;
    private int timer;
    private float timeCount;
    private Label labelTiempo;
    /*
    PUNTUACION
     */








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
        sonidoSalto = jokoa.getManager().get("audio/jump.ogg");
        sonidoMuerte = jokoa.getManager().get("audio/die.ogg");
        musicaDeFondo = jokoa.getManager().get("audio/song.ogg");

        timer = 0;
        timeCount = 0f;

    }


    @Override
    public void show() {

        FactoriaDeEntidades factory = new FactoriaDeEntidades(jokoa.getManager());

        table = new Table();
        table.top();
        table.setFillParent(true);

        //Label puntuacionLabel= new Label(String.format("%06d",0),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label puntuacionTextoLabel=new Label("Puntuacion: ",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        labelTiempo = new Label("Tiempo: 000",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(puntuacionTextoLabel).expandX().padTop(10);
        table.add(labelTiempo).expandX().padTop(10);
        //table.add(puntuacionLabel).expandX().padTop(10);

        stage.addActor(table);


        // Create the player. It has an initial position.
        jugador = factory.crearJugador(world, new Vector2(1.5f, 1.5f));


        /*listaDeSuelo.add(factory.crearSuelo(world, 0, 10, 1));

        listaDeSuelo.add(factory.crearSuelo(world, 12, 1000, 1));*/
        GeneracionDeEscenario.GenerarSuelo(listaDeSuelo,world,factory,2000);
        GeneracionDeEscenario.GenerarRocas(listaDeMontes,world,factory);


        //listaDeSuelo.add(factory.crearSuelo(world, 0, 1000, 1));
        //for (int i = 0; i < 500; i++) listaDeSuelo.add(factory.crearSuelo(world, 1, i, 1));
        //listaDeSuelo.add(factory.crearSuelo(world, 15, 10, 2));
        //listaDeSuelo.add(factory.crearSuelo(world, 30, 8, 2));

        //listaDeMontes.add(factory.crearMonte(world, 18, 1));
        //listaDeMontes.add(factory.crearMonte(world, 33, 1));
        //listaDeMontes.add(factory.crearMonte(world, 45, 1));
        //listaDeMontes.add(factory.crearMonte(world, 60, 1));


        for (EntidadSuelo floor : listaDeSuelo)
            stage.addActor(floor);

        for (EntidadMonte monte : listaDeMontes)
            stage.addActor(monte);


        stage.addActor(jugador);



        stage.getCamera().position.set(position);
        stage.getCamera().update();


        musicaDeFondo.setVolume(0.75f);
        musicaDeFondo.play();
    }



    @Override
    public void render(float delta) {

        updateTimer(delta);

        //update(delta);//separar el update logico del render

        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();

        world.step(delta,6,2);

        if (jugador.getX() > 150 && jugador.isVivo()) {
            float speed = VELOCIDAD_JUGADOR * delta *  PIXELS_POR_METRO;
            stage.getCamera().translate(speed, 0, 0);
            table.moveBy(speed, 0);
        }

        stage.draw();
    }


    public void updateTimer(float delta){
        if (jugador.isVivo()) {
            timeCount += delta;
            if (timeCount >= 1) {
                timer++;
                labelTiempo.setText(String.format("Tiempo: %03d", timer));
                timeCount = 0;
            }
        }
    }


    @Override
    public void dispose() {

        stage.dispose();
        world.dispose();
    }


    @Override
    public void hide() {
        stage.clear();

        // Detach todas las entidades
        jugador.detach();
        for (EntidadSuelo suelo : listaDeSuelo)
            suelo.detach();
        for (EntidadMonte monte : listaDeMontes)
            monte.detach();

        // Las listas (clear)
        listaDeSuelo.clear();
        listaDeMontes.clear();

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



            if (areCollided(contact, "jugador", "monte")) {
                if (jugador.isVivo()) {
                    jugador.setVivo(false);
                    timer = 0;

                    musicaDeFondo.stop();
                    sonidoMuerte.play();

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
                if (jugador.isVivo() && Gdx.input.isTouched()) {
                    sonidoSalto.play();
                }
            }
        }

        // Here two lonely methods that I don't use but have to override anyway.
        @Override public void preSolve(Contact contact, Manifold oldManifold) { }
        @Override public void postSolve(Contact contact, ContactImpulse impulse) { }
    }

}
