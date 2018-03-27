package com.endlessrunner.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;

/**
 * Created by Jongui on 27/03/2018.
 */

public class CargandoScreen extends BaseScreen{


    private Stage stage;

    private Skin skin;

    private Label loading;

    public CargandoScreen(EndlessRunner game) {
        super(game);

        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        loading = new Label("Loading...", skin);
        loading.setPosition(320 - loading.getWidth() / 2, 180 - loading.getHeight() / 2);
        stage.addActor(loading);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (jokoa.getManager().update()) {
            jokoa.finishLoading();
        } else {
            int progress = (int) (jokoa.getManager().getProgress() * 100);
            loading.setText("Loading... " + progress + "%");
        }

        stage.act();
        stage.draw();
    }


    @Override
    public void dispose() {
        stage.dispose();
        //skin.dispose();
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

    @Override
    public void hide() {

    }


}