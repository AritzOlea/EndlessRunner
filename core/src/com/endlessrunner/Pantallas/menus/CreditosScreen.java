package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.Pantallas.partida_basica.BaseScreen;
import com.endlessrunner.ayuda.Ajustes;

/**
 * Created by Jongui on 27/03/2018.
 */

public class CreditosScreen extends BaseScreen{

    private Stage stage;

    private Skin skin;
    private Image tituloa;
    private Label credits;

    private ImageButton atzera;

    private Texture fondoBackground;




    public CreditosScreen(final EndlessRunner game) {
        super(game);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        fondoBackground=jokoa.getManager().get("bg/FondoCreditos.png");

    }

    @Override
    public void show() {

        stage.clear();


        tituloa = new Image(jokoa.getManager().get("titulo.png", Texture.class));
        tituloa.setPosition( 20, 250 );
        tituloa.setScale(0.8f);
        stage.addActor(tituloa);


        if (Ajustes.Idioma.equals("ES")) {
            credits = new Label(
                    "Alumnos de la asignatura Softwarea Garatzeko\nTresna Aurreratuak (SGTA) 2018:\n" +
                    "Jon Guillo, Aritz Olea y Ander Lopez.\n\n" +

                    "Musica: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Sonidos: freesound.org", skin);
        }else if (Ajustes.Idioma.equals("EN")) {
            credits = new Label(
                    "Students from subject Softwarea Garatzeko\nTresna Aurreratuak (SGTA) 2018:\n" +
                    "Jon Guillo, Aritz Olea and Ander Lopez.\n\n" +

                    "Music: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Sounds: freesound.org", skin);
        }else {
            credits = new Label(
                    "Softwarea Garatzeko Tresna Aurreratuak\n(SGTA) 2018 irakasgaiko ikasleak:\n" +
                    "Jon Guillo, Aritz Olea eta Ander Lopez.\n\n" +

                    "Musika: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Soinuak: freesound.org", skin);
        }
        credits.setPosition(45, 50);
        stage.addActor(credits);


        String rutaAtras;
        if(Ajustes.Idioma.equals("EUS")){
            rutaAtras="euskera";
        }else if(Ajustes.Idioma.equals("ES")){
            rutaAtras="gaztelera";
        }else{//EN
            rutaAtras="ingelesa";
        }

        atzera = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+rutaAtras+"/atzera.png")))));
        atzera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });
        atzera.setSize(160, 50);

        atzera.setPosition(470, 20);
        stage.addActor(atzera);



        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        // Dispose assets.
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();


        jokoa.batch.begin();
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        jokoa.batch.end();

        stage.draw();
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void resize(int width, int height) {}

}
