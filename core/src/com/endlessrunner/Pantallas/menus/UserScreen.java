package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.ayuda.DatosUsuarioXML;

import static com.endlessrunner.ayuda.DatosUsuarioXML.saioaItxi;
import static com.endlessrunner.ayuda.servidor.ControlServidor.datuakPasaZerbitzariari;
import static com.endlessrunner.ayuda.servidor.ControlServidor.logInZuzena;

/**
 * Created by Jongui on 31/03/2018.
 */

public class UserScreen extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;

    private TextButton   atzera, usuarioLogeado,itxiKontua;

    public UserScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("paisajes/dia/png/BG/BG.png");


        atzera = new TextButton("Atzera", skin);
        usuarioLogeado = new TextButton(" ", skin);
        itxiKontua= new TextButton("Itxi", skin);



        atzera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });

        itxiKontua.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                UserLoginScreen.passLoginString="";
                UserLoginScreen.usuarioLoginString="";
                datuakPasaZerbitzariari();
                saioaItxi();
                jokoa.setScreen(jokoa.menuScreen);
            }
        });


        atzera.setSize(100,50);
        usuarioLogeado.setSize(500,280);
        itxiKontua.setSize(100,50);


        usuarioLogeado.setPosition(20, 20);
        atzera.setPosition(530,10);
        itxiKontua.setPosition(530,70);


        stage.addActor(atzera);
        stage.addActor(usuarioLogeado);
        stage.addActor(itxiKontua);


    }

    @Override
    public void show() {

        usuarioLogeado.setText("User: "+DatosUsuarioXML.user+"\nTopScore: "+DatosUsuarioXML.topScore+
                "\nAvgScore: "+DatosUsuarioXML.avgScore+"\nPlayedGames:"+DatosUsuarioXML.playedGames+"\nTotalJumps: "+DatosUsuarioXML.totallJumps+
                "\nTotalMashrooms: "+DatosUsuarioXML.totalMashrooms+"\nTotalGlues: "+DatosUsuarioXML.totalGlues+"\nFallDeaths: "+DatosUsuarioXML.fallDeaths+
                "\nCollisionDeaths: "+DatosUsuarioXML.collisionDeaths+"\nCameraOutDeaths: "+DatosUsuarioXML.cameraOutDeaths);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        jokoa.batch.begin();
        //HAY QUE MULTIPLICAR LA RESOLUCIÓN DE PANTALLA POR ALGÚN NUMERO PARA QUE QUEDE BIEN
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth() * 1.75f, Gdx.graphics.getHeight() * 1.75f);
        jokoa.batch.end();

        stage.draw();
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
