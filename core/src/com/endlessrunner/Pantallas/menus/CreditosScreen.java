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

        back = new TextButton("Atzera", skin);

        credits = new Label("EndlessRunner\n" +
                "Softwarea Garatzeko Tresna Aurreratuak (SGTA) 2018 irakasgaiko ikasleak:\n" +
                "Jon Guillo, Aritz Olea eta Ander Lopez.\n\n" +

                "Musika: \"Hot Pursuit\" (incompetech.com)\n" +
                "Soinuak: freesound.org", skin);

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
