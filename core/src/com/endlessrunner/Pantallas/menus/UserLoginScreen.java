package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.Pantallas.partida_basica.BaseScreen;

import static com.endlessrunner.ayuda.DatosUsuarioXML.saioaItxi;
import static com.endlessrunner.ayuda.servidor.ControlServidor.datuakPasaZerbitzariari;
import static com.endlessrunner.ayuda.servidor.ControlServidor.logInZuzena;

/**
 * Created by Jongui on 31/03/2018.
 */

public class UserLoginScreen extends BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;

    private TextButton usuarioLogin, passLogin, logIn,atzera;
    private int sarrera;


    public static String usuarioLoginString="", passLoginString="";

    public UserLoginScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("paisajes/dia/png/BG/BG.png");

        usuarioLogin = new TextButton("Erabiltzailea:\n", skin);
        passLogin = new TextButton("Pasahitza:\n", skin);
        logIn = new TextButton("LogIn", skin);
        atzera = new TextButton("Atzera", skin);


        logIn.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //jokoa.setScreen(jokoa.gameScreen);
               if(logInZuzena(usuarioLoginString,passLoginString)){
                   jokoa.setScreen(jokoa.menuScreen);
                }

            }
        });

        usuarioLogin.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                sarrera=0;
                Gdx.input.getTextInput(textListener, "Erabiltzailea: ", "", "");
            }
        });

        passLogin.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                sarrera=1;
                Gdx.input.getTextInput(textListener, "Pasahitza: ", "", "");
            }
        });


        atzera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });




        usuarioLogin.setSize(200, 80);
        passLogin.setSize(200, 80);
        logIn.setSize(140, 50);
        atzera.setSize(100,50);


        usuarioLogin.setPosition(60, 190);
        passLogin.setPosition(60, 100);
        logIn.setPosition(90, 20);
        atzera.setPosition(530,10);


        stage.addActor(usuarioLogin);
        stage.addActor(passLogin);
        stage.addActor(logIn);
        stage.addActor(atzera);


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


    Input.TextInputListener textListener = new Input.TextInputListener()
    {
        @Override
        public void input(String input)
        {
            switch (sarrera){
                case 0:
                    usuarioLoginString=input;
                    usuarioLogin.setText("Erabiltzailea:\n"+usuarioLoginString);
                    break;

                case 1:
                    passLoginString=input;
                    String passLag="";
                    for(int i=0;i<passLoginString.length();i++)passLag=passLag+"*";
                    passLogin.setText("Pasahitza:\n"+passLag);
                    break;
            }
        }

        @Override
        public void canceled()
        {
            System.out.println("Aborted");
        }
    };

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