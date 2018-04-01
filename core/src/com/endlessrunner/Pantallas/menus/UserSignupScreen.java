package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.Pantallas.partida_basica.BaseScreen;

import static com.endlessrunner.ayuda.servidor.ControlServidor.erabiltzaileaErregistratu;
import static com.endlessrunner.ayuda.servidor.ControlServidor.logInZuzena;

/**
 * Created by aritz on 31/03/2018.
 */

public class UserSignupScreen extends BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;

    private TextButton usuarioLogin,passLogin,passLogin2,signUp,atzera,hasierara;
    private int sarrera;
    private Label mensaje;


    public static String usuarioLoginString="", passLoginString="", pass2LoginString="";

    public UserSignupScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("paisajes/dia/png/BG/BG.png");

        usuarioLogin = new TextButton("Erabiltzailea:\n", skin);
        passLogin = new TextButton("Pasahitza:\n", skin);
        passLogin2 = new TextButton("Pasahitza errepikatu:\n", skin);
        signUp = new TextButton("Sign Up", skin);
        atzera = new TextButton("Atzera", skin);
        hasierara = new TextButton("Hasierara", skin);

        mensaje = new Label("", skin);

        signUp.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (usuarioLoginString.length() < 4){
                    mensaje.setColor(Color.RED);
                    mensaje.setText("Erabiltzailea gutxienez 4 karaketekoa izan behar da.");
                }else if(passLoginString.length() < 4){
                    mensaje.setColor(Color.RED);
                    mensaje.setText("Pasahitza gutxienez 4 karaketekoa izan behar da.");
                }else if(!passLoginString.equals(pass2LoginString)){
                    mensaje.setColor(Color.RED);
                    mensaje.setText("Pasahitza ondo errepikatu behar da");
                }else{
                    if (erabiltzaileaErregistratu(usuarioLoginString,passLoginString)) {
                        mensaje.setColor(Color.WHITE);
                        mensaje.setText(usuarioLoginString + " ondo erregistratua izan da!");
                    }else {
                        mensaje.setColor(Color.RED);
                        mensaje.setText("Errorea gertatu da erregistratzean... Saiatu berriro.");
                    }
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

        passLogin2.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                sarrera=2;
                Gdx.input.getTextInput(textListener, "Pasahitza errepikatu: ", "", "");
            }
        });


        atzera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.userLogInScreen);
            }
        });

        hasierara.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });




        usuarioLogin.setSize(400, 80);
        passLogin.setSize(195, 80);
        passLogin2.setSize(195, 80);
        signUp.setSize(400, 50);
        atzera.setSize(100,50);
        hasierara.setSize(100, 50);


        usuarioLogin.setPosition(45, 240);
        passLogin.setPosition(45, 150);
        passLogin2.setPosition(250, 150);
        signUp.setPosition(45, 90);
        atzera.setPosition(530,10);
        hasierara.setPosition(530, 70);
        mensaje.setColor(Color.WHITE);
        mensaje.setPosition(45, 60);


        stage.addActor(usuarioLogin);
        stage.addActor(passLogin);
        stage.addActor(passLogin2);
        stage.addActor(signUp);
        stage.addActor(atzera);
        stage.addActor(hasierara);
        stage.addActor(mensaje);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        mensaje.setText("");
        usuarioLogin.setText("");
        passLogin.setText("");
        passLogin2.setText("");
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

                case 2:
                    pass2LoginString=input;
                    String pass2Lag="";
                    for(int i=0;i<pass2LoginString.length();i++)pass2Lag=pass2Lag+"*";
                    passLogin2.setText("Pasahitza errepikatu:\n"+pass2Lag);
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
