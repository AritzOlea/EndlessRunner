package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

/**
 * Created by Jongui on 27/03/2018.
 */

public class CreditosScreen extends BaseScreen{

    private Stage stage;

    private Skin skin;

    private Label credits;

    private TextButton back;

    public CreditosScreen(final EndlessRunner game) {
        super(game);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        if (Ajustes.Idioma.equals("ES")) {
            back = new TextButton("Atras", skin);
            credits = new Label("EndlessRunner\n" +
                    "Alumnos de la asignatura Softwarea Garatzeko Tresna Aurreratuak (SGTA) 2018:\n" +
                    "Jon Guillo, Aritz Olea y Ander Lopez.\n\n" +

                    "Musica: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Sonidos: freesound.org", skin);
        }else if (Ajustes.Idioma.equals("EN")) {
            back = new TextButton("Back", skin);
            credits = new Label("EndlessRunner\n" +
                    "Students from subject Softwarea Garatzeko Tresna Aurreratuak (SGTA) 2018:\n" +
                    "Jon Guillo, Aritz Olea and Ander Lopez.\n\n" +

                    "Music: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Sounds: freesound.org", skin);
        }else {
            back = new TextButton("Atzera", skin);
            credits = new Label("EndlessRunner\n" +
                    "Softwarea Garatzeko Tresna Aurreratuak (SGTA) 2018 irakasgaiko ikasleak:\n" +
                    "Jon Guillo, Aritz Olea eta Ander Lopez.\n\n" +

                    "Musika: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Soinuak: freesound.org", skin);
        }

        back.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
            }
        });

        credits.setPosition(20, 340 - credits.getHeight());
        back.setSize(200, 80);
        back.setPosition(40, 50);

        stage.addActor(back);
        stage.addActor(credits);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        if (Ajustes.Idioma.equals("ES")) {
            back.setText("Atras");
            credits.setText("EndlessRunner\n" +
                    "Alumnos de la asignatura Softwarea Garatzeko Tresna Aurreratuak (SGTA) 2018:\n" +
                    "Jon Guillo, Aritz Olea y Ander Lopez.\n\n" +

                    "Musica: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Sonidos: freesound.org");
        }else if (Ajustes.Idioma.equals("EN")) {
            back.setText("Back");
            credits.setText("EndlessRunner\n" +
                    "Students from subject Softwarea Garatzeko Tresna Aurreratuak (SGTA) 2018:\n" +
                    "Jon Guillo, Aritz Olea and Ander Lopez.\n\n" +

                    "Music: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Sounds: freesound.org");
        }else{
            back.setText("Atzera");
            credits.setText("EndlessRunner\n" +
                    "Softwarea Garatzeko Tresna Aurreratuak (SGTA) 2018 irakasgaiko ikasleak:\n" +
                    "Jon Guillo, Aritz Olea eta Ander Lopez.\n\n" +

                    "Musika: \"Hot Pursuit\" (incompetech.com)\n" +
                    "Soinuak: freesound.org");
        }
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
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void resize(int width, int height) {}

}
