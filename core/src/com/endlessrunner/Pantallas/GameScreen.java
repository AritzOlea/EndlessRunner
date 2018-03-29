package com.endlessrunner.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
import com.endlessrunner.entidades.objetos.EntidadSetaPuntos;
import com.endlessrunner.entidades.objetos.EntidadSetaSinSalto;
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
    public static ActorAventurero jugador;
    public static float velocidadJugador;

    //Mundo Box2D
    private World world;

    //Escenario Scene2D
    private Stage stage;
    private Texture fondoBackground;

    //Sonidos y musicas
    public static Sound sonidoSalto,sonidoMuerte;
    private Music musicaDeFondo;

    //Posicion de camara
    private Vector3 position;

    //Listas
    private List<EntidadSuelo> listaDeSuelo = new ArrayList<EntidadSuelo>();
    private List<EntidadMonte> listaDeMontes = new ArrayList<EntidadMonte>();
    private List<EntidadSetaPuntos> listaDeSetasPuntos = new ArrayList<EntidadSetaPuntos>();
    private List<EntidadSetaSinSalto> listaDeSetasSinSalto = new ArrayList<EntidadSetaSinSalto>();
    private List<EntidadSegundosPisos> listaSegundoPiso = new ArrayList<EntidadSegundosPisos>();

    //Puntuaciones, tiempo...
    Table table;
    public static int timer,puntuacion;
    public static float timeCount;
    public static Label labelTiempo,puntuacionTextoLabel;
    public static boolean pegadoAlSuelo;
    public static boolean jugadorEnElSuelo;

    //Pegado al suelo


    /*
    METODOS
     */

    //CONSTRUCTOR
    public GameScreen(EndlessRunner jokoa){
        super(jokoa);

        stage=new Stage(new FitViewport(640,360));
        position = new Vector3(stage.getCamera().position);

        world=new World(new Vector2(0,-15),true);
        world.setContactListener(new GameContactListener());

        fondoBackground=jokoa.getManager().get("paisajes/dia/png/BG/BG.png");

        //Sonidos y musicas...
        sonidoSalto = jokoa.getManager().get("audio/jump.ogg");
        sonidoMuerte = jokoa.getManager().get("audio/die.ogg");
        musicaDeFondo = jokoa.getManager().get("audio/song.ogg");

        timer = 0;
        timeCount = 0f;
        puntuacion=0;

        jugadorEnElSuelo = true;
    }

    @Override
    public void show() {

        FactoriaDeEntidades factory = new FactoriaDeEntidades(jokoa.getManager());

        table = new Table();
        table.top();
        table.setFillParent(true);

        //Label puntuacionLabel= new Label(String.format("%06d",0),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        puntuacionTextoLabel=new Label("Puntuacion: 000",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        labelTiempo = new Label("Cuenta atras: 000",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(puntuacionTextoLabel).expandX().padTop(10);
        table.add(labelTiempo).expandX().padTop(10);
        //table.add(puntuacionLabel).expandX().padTop(10);

        stage.addActor(table);


        // Create the player. It has an initial position.
        jugador = factory.crearJugador(world, new Vector2(0.75f, 1.5f));


        listaDeSuelo.clear();
        listaDeMontes.clear();
        listaDeSetasPuntos.clear();
        listaDeSetasSinSalto.clear();
        listaSegundoPiso.clear();

        //listaDeSuelo.add(factory.crearSuelo(world, 0, 1000, 1));
        GeneracionDeEscenario.GenerarSuelo(listaDeSuelo,world,factory,2000);
        GeneracionDeEscenario.GenerarSegundosPisos(listaSegundoPiso,world,factory,listaDeMontes,listaDeSetasPuntos);

        GeneracionDeEscenario.GenerarSinSalto(listaDeSetasSinSalto,world,factory);
        GeneracionDeEscenario.GenerarRocas(listaDeMontes,world,factory);
        GeneracionDeEscenario.GenerarSetasPositivas(listaDeSetasPuntos,world,factory);



        for (EntidadSuelo floor : listaDeSuelo)
            stage.addActor(floor);

        for (EntidadMonte monte : listaDeMontes)
            stage.addActor(monte);

        for (EntidadSetaPuntos seta : listaDeSetasPuntos)
            stage.addActor(seta);

        for (EntidadSetaSinSalto seta : listaDeSetasSinSalto)
            stage.addActor(seta);

        for (EntidadSegundosPisos piso: listaSegundoPiso)
            stage.addActor(piso);


        stage.addActor(jugador);


        stage.getCamera().position.set(position);
        stage.getCamera().update();


        musicaDeFondo.setVolume(0.75f);
        musicaDeFondo.play();
    }


    int bgIndice=0,bgInc=0,dir=-1;

    @Override
    public void render(float delta) {

        updateTimer(delta);


        //update(delta);//separar el update logico del render

        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();

        jokoa.batch.begin();
        //HAY QUE MULTIPLICAR LA RESOLUCIÓN DE PANTALLA POR ALGÚN NUMERO PARA QUE QUEDE BIEN
        jokoa.batch.draw(fondoBackground,bgIndice,0, Gdx.graphics.getWidth() + Gdx.graphics.getWidth() * 1.75f, Gdx.graphics.getHeight() * 1.75f);
        jokoa.batch.end();

        if(bgInc<5){
            bgInc++;
        }else{
            bgInc=0;
            if(bgIndice<=0 && bgIndice>-100){
                bgIndice=bgIndice+dir;
            }else{
                dir=dir*(-1);
                bgIndice=bgIndice+dir;
            }
        }



        world.step(delta,6,2);

        if (jugador.getX() > 150 && jugador.isVivo()) {
            float speed = VELOCIDAD_JUGADOR * delta *  PIXELS_POR_METRO;
            stage.getCamera().translate(speed, 0, 0);
            table.moveBy(speed, 0);
        }

        if (timer == 0)
            GameScreen.labelTiempo.setColor(Color.WHITE);

        if (jugador.getY() < -150){
            if (jugador.isVivo()) {
                jugador.setVivo(false);
                timer = 0;
                puntuacion = 0;

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

        stage.draw();
    }


    public void updateTimer(float delta){
        if (jugador.isVivo()) {
            timeCount += delta;
            if (timeCount >= 1) {

                puntuacion=puntuacion+2;
                puntuacionTextoLabel.setText(String.format("Puntuacion: %03d", GameScreen.puntuacion));


                if(timer>0)timer--;

                if(timer==0)jugador.setPegadoAlSuelo(false);

                labelTiempo.setText(String.format("Cuenta atras: %03d", GameScreen.timer));


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
        for (EntidadSetaPuntos seta : listaDeSetasPuntos)
            seta.detach();
        for (EntidadSegundosPisos piso: listaSegundoPiso)
            piso.detach();

        // Las listas (clear)
        listaDeSuelo.clear();
        listaDeMontes.clear();
        listaDeSetasPuntos.clear();
        listaDeSetasSinSalto.clear();
        listaSegundoPiso.clear();

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
        public void beginContact(Contact contact) {

            if (areCollided(contact, "jugador", "suelo")) {
                jugador.setSaltandoUno(false);
                jugador.setSaltandoDos(false);
                jugadorEnElSuelo = true;

                if (Gdx.input.isTouched()) {
                    //sonidoSalto.play();

                    jugador.setDebeSaltar(true);
                }
            }



            if (areCollided(contact, "jugador", "monte")) {
                if (jugador.isVivo()) {
                    jugador.setVivo(false);
                    timer = 0;
                    puntuacion = 0;

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


            /*if (areCollided(contact, "jugador", "setaPuntos")) {

                puntuacion=puntuacion+10;
                puntuacionTextoLabel.setText(String.format("Puntuacion: %03d", puntuacion));

            }*/

        }



        @Override
        public void endContact(Contact contact) {

            if (areCollided(contact, "jugador", "suelo")) {
                jugadorEnElSuelo = false;
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
