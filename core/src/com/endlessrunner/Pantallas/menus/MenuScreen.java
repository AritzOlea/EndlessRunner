package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.ayuda.DatosUsuarioXML;

/**
 * Created by Jongui on 27/03/2018.
 */

public class MenuScreen extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;
    private Image tituloa;
    private TextButton jokatu,puntuazioak,aukerak,kontua,kredituak;


    public MenuScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("paisajes/dia/png/BG/BG.png");

        jokatu = new TextButton("Jokatu", skin);
        puntuazioak = new TextButton("Puntuazioak", skin);
        aukerak = new TextButton("Aukerak", skin);
        kontua = new TextButton("Kontua", skin);
        kredituak = new TextButton("Kredituak", skin);

        tituloa = new Image(jokoa.getManager().get("titulo.png", Texture.class));

        jokatu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.gameScreen);
            }
        });

        puntuazioak.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.topPartidaBasica);
            }
        });

        kredituak.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.creditosScreen);
            }
        });

        kontua.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(DatosUsuarioXML.user.equals("Anonimo")){
                    jokoa.setScreen(jokoa.userLogInScreen);
                }else{
                    jokoa.setScreen(jokoa.userScreen);
                }

            }
        });

        //tituloa.setPosition(320 - tituloa.getWidth() / 2, 320 - tituloa.getHeight());
        tituloa.setPosition( 20, 250 );

        jokatu.setSize(200, 80);
        puntuazioak.setSize(200, 80);
        aukerak.setSize(160, 50);
        kontua.setSize(160, 50);
        kredituak.setSize(160, 50);

        jokatu.setPosition(30, 110);
        puntuazioak.setPosition(30, 20);
        kredituak.setPosition(470, 20);
        aukerak.setPosition(470, 80);
        kontua.setPosition(470, 140);

        stage.addActor(tituloa);
        stage.addActor(jokatu);
        stage.addActor(puntuazioak);
        stage.addActor(aukerak);
        stage.addActor(kontua);
        stage.addActor(kredituak);

    }

    @Override
    public void show() {
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
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth() + Gdx.graphics.getWidth() * 1.75f, Gdx.graphics.getHeight() * 1.75f);
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

