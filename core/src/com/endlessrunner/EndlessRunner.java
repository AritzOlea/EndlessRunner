package com.endlessrunner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.endlessrunner.Pantallas.BaseScreen;
import com.endlessrunner.Pantallas.CargandoScreen;
import com.endlessrunner.Pantallas.GameOverScreen;
import com.endlessrunner.Pantallas.GameScreen;

public class EndlessRunner extends Game {

    private AssetManager manager;


    //Todas las pantallas tienen que ponerse aqui...
    public BaseScreen gameScreen,menuScreen,gameOverScreen,cargandoScreen;


    public SpriteBatch batch;

    @Override
    public void create() {

        batch = new SpriteBatch();

        manager = new AssetManager();
        /*
        Aquí cargaremos todos los recursos (imagenes, audios, ...)
         */

        //personajes
        manager.load("personajes/aventurero/Idle__000.png",Texture.class);

        //paisajes
        manager.load("paisajes/dia/png/Object/Stone.png",Texture.class);
        manager.load("paisajes/dia/png/Tiles/2.png",Texture.class);

        //menus
        manager.load("gameover.png",Texture.class);

        //sonidos & musica
        manager.load("audio/die.ogg", Sound.class);
        manager.load("audio/jump.ogg", Sound.class);
        manager.load("audio/song.ogg", Music.class);


        //Meto la pantalla de carga para iniciar el juego
        cargandoScreen = new CargandoScreen(this);
        this.setScreen(cargandoScreen);
    }


    public void finishLoading() {
        //menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        //creditsScreen = new CreditsScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        batch.dispose();
    }

    @Override
    public void render () {
        super.render();
    }

    public AssetManager getManager() {
        return manager;
    }


}
