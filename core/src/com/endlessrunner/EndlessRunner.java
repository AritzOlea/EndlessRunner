package com.endlessrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndlessRunner extends Game {

    private AssetManager manager;

    public BaseScreen gameScreen;

    @Override
    public void create() {
        manager = new AssetManager();
        /*
        Aquí cargaremos todos los recursos (imagenes, audios, ...)
         */

        //por ahora solo añado el juego, sin menu ni nada
        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);
    }

    public AssetManager getManager() {
        return manager;
    }
}
