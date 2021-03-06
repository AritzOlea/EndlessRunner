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
import com.endlessrunner.ayuda.Ajustes;

import static com.endlessrunner.ayuda.DatosUsuarioXML.NombreDeUsuarioAdecuado;
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
                if (usuarioLoginString.length() < 4 || usuarioLoginString.length() > 10){
                    mensaje.setColor(Color.RED);
                    if (Ajustes.Idioma.equals("ES")) {
                        mensaje.setText("El usuario debe tener entre 4 y 10 caracteres.");
                    }else if (Ajustes.Idioma.equals("EN")) {
                        mensaje.setText("Username must have between 4 and 10 characters.");
                    }else {
                        mensaje.setText("Erabiltzailea 4 eta 10 karaktere artekoa izan behar da.");
                    }
                }else if(passLoginString.length() < 4 || passLoginString.length() > 10){
                    mensaje.setColor(Color.RED);
                    if (Ajustes.Idioma.equals("ES")) {
                        mensaje.setText("La contrasena debe tener entre 4 y 10 caracteres.");
                    }else if (Ajustes.Idioma.equals("EN")) {
                        mensaje.setText("Password must have between 4 and 10 characters.");
                    }else {
                        mensaje.setText("Pasahitza 4 eta 10 karaktere artekoa izan behar da.");
                    }
                }else if(!passLoginString.equals(pass2LoginString)) {
                    mensaje.setColor(Color.RED);
                    if (Ajustes.Idioma.equals("ES")) {
                        mensaje.setText("La contrasena se debe de repetir bien.");
                    }else if (Ajustes.Idioma.equals("EN")) {
                        mensaje.setText("Password must be repeated OK.");
                    }else {
                        mensaje.setText("Pasahitza ondo errepikatu behar da.");
                    }
                }else if(! NombreDeUsuarioAdecuado(usuarioLoginString)) {
                    mensaje.setColor(Color.RED);
                    if (Ajustes.Idioma.equals("ES")) {
                        mensaje.setText("El nombre de usuario solo puede contener caracteres y letras.\n" +
                                "Ademas, el lenguaje debe de ser apropiado. ");
                    }else if (Ajustes.Idioma.equals("EN")) {
                        mensaje.setText("Username can only contain characters and letters.\n" +
                                "Moreover, the language must be appropriate. ");
                    }else {
                        mensaje.setText("Erabiltzaile izenak karaketereak eta letrak soilik eduki ditzazke.\n" +
                                "Gainera, bertan erabilitako hizkuntza egokia izan behar da. ");
                    }
                }else if(passLoginString.contains(" ")){
                    mensaje.setColor(Color.RED);
                    if (Ajustes.Idioma.equals("ES")) {
                        mensaje.setText("La contrasena no puede tener espacios en blanco!");
                    }else if (Ajustes.Idioma.equals("EN")) {
                        mensaje.setText("Password cannot have empty spaces!");
                    }else {
                        mensaje.setText("Pasahitzak ezin du tarte hutsik izan!");
                    }
                }else{
                    if (erabiltzaileaErregistratu(usuarioLoginString,passLoginString)) {
                        mensaje.setColor(Color.WHITE);
                        if (Ajustes.Idioma.equals("ES")) {
                            mensaje.setText(usuarioLoginString + " se ha registrado correctamente!");
                        }else if (Ajustes.Idioma.equals("EN")) {
                            mensaje.setText(usuarioLoginString + " signed up successfully!");
                        }else {
                            mensaje.setText(usuarioLoginString + " ondo erregistratua izan da!");
                        }
                    }else {
                        mensaje.setColor(Color.RED);
                        if (Ajustes.Idioma.equals("ES")) {
                            mensaje.setText("Ha habido un error al registrarse... Intentelo de nuevo.");
                        }else if (Ajustes.Idioma.equals("EN")) {
                            mensaje.setText("There has been an error while signing up... Try again.");
                        }else {
                            mensaje.setText("Errorea gertatu da erregistratzean... Saiatu berriro.");
                        }
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
        usuarioLoginString=""; passLoginString=""; pass2LoginString="";
        mensaje.setText("");
        usuarioLogin.setText("Erabiltzailea:\n");
        passLogin.setText("Pasahitza:\n");
        passLogin2.setText("Pasahitza errepikatu:\n");
        if (Ajustes.Idioma.equals("ES")) {
            usuarioLogin.setText("Usuario:\n");
            passLogin.setText("Contrasena:\n");
            passLogin2.setText("Repetir contrasena:\n");
            atzera.setText("Atras");
            hasierara.setText("Principio");
            signUp.setText("Registrarse");
        }else if (Ajustes.Idioma.equals("EN")) {
            usuarioLogin.setText("Username:\n");
            passLogin.setText("Password:\n");
            passLogin2.setText("Repeat password:\n");
            atzera.setText("Back");
            hasierara.setText("Home");
            signUp.setText("Sign up");
        }else {
            usuarioLogin.setText("Erabiltzailea:\n");
            passLogin.setText("Pasahitza:\n");
            passLogin2.setText("Pasahitza errepikatu:\n");
            atzera.setText("Atzera");
            hasierara.setText("Hasierara");
            signUp.setText("Erregistratu");
        }
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
                    if (Ajustes.Idioma.equals("ES")) {
                        usuarioLogin.setText("Usuario:\n"+usuarioLoginString);
                    }else if (Ajustes.Idioma.equals("EN")) {
                        usuarioLogin.setText("Username:\n"+usuarioLoginString);
                    }else {
                        usuarioLogin.setText("Erabiltzailea:\n"+usuarioLoginString);
                    }
                    break;

                case 1:
                    passLoginString=input;
                    String passLag="";
                    for(int i=0;i<passLoginString.length();i++)passLag=passLag+"*";
                    if (Ajustes.Idioma.equals("ES")) {
                        passLogin.setText("Contrasena:\n"+passLag);
                    }else if (Ajustes.Idioma.equals("EN")) {
                        passLogin.setText("Password:\n"+passLag);
                    }else {
                        passLogin.setText("Pasahitza:\n"+passLag);
                    }
                    break;

                case 2:
                    pass2LoginString=input;
                    String pass2Lag="";
                    for(int i=0;i<pass2LoginString.length();i++)pass2Lag=pass2Lag+"*";
                    if (Ajustes.Idioma.equals("ES")) {
                        passLogin2.setText("Repetir contrasena:\n"+pass2Lag);
                    }else if (Ajustes.Idioma.equals("EN")) {
                        passLogin2.setText("Repeat password:\n"+pass2Lag);
                    }else {
                        passLogin2.setText("Pasahitza errepikatu:\n"+pass2Lag);
                    }
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
