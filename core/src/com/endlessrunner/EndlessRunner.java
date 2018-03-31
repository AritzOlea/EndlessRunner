package com.endlessrunner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.endlessrunner.Pantallas.menus.UserScreen;
import com.endlessrunner.Pantallas.menus.UserLoginScreen;
import com.endlessrunner.Pantallas.partida_basica.BaseScreen;
import com.endlessrunner.Pantallas.menus.CargandoScreen;
import com.endlessrunner.Pantallas.partida_basica.GameOverScreen;
import com.endlessrunner.Pantallas.partida_basica.GameScreen;
import com.endlessrunner.Pantallas.menus.MenuScreen;
import com.endlessrunner.Pantallas.top.TopPartidaBasica;

import static com.endlessrunner.ayuda.DatosUsuarioXML.hasieratuDatuakXML;

public class EndlessRunner extends Game {

    private AssetManager manager;


    //Todas las pantallas tienen que ponerse aqui...
    public BaseScreen gameScreen,menuScreen,gameOverScreen,cargandoScreen,topPartidaBasica,userLogInScreen,userScreen;


    public SpriteBatch batch;

    @Override
    public void create() {


        //XML-ko datuak irakurri
        hasieratuDatuakXML();


        batch = new SpriteBatch();

        manager = new AssetManager();
        /*
        Aquí cargaremos todos los recursos (imagenes, audios, ...)
         */

        //personajes
        manager.load("personajes/aventurero/Idle__000.png",Texture.class);
        //corriendo
        manager.load("personajes/aventurero/Run__000.png",Texture.class);
        manager.load("personajes/aventurero/Run__001.png",Texture.class);
        manager.load("personajes/aventurero/Run__002.png",Texture.class);
        manager.load("personajes/aventurero/Run__003.png",Texture.class);
        manager.load("personajes/aventurero/Run__004.png",Texture.class);
        manager.load("personajes/aventurero/Run__005.png",Texture.class);
        manager.load("personajes/aventurero/Run__006.png",Texture.class);
        manager.load("personajes/aventurero/Run__007.png",Texture.class);
        manager.load("personajes/aventurero/Run__008.png",Texture.class);
        manager.load("personajes/aventurero/Run__009.png",Texture.class);
        //saltando
        manager.load("personajes/aventurero/Jump__000.png",Texture.class);
        manager.load("personajes/aventurero/Jump__001.png",Texture.class);
        manager.load("personajes/aventurero/Jump__002.png",Texture.class);
        manager.load("personajes/aventurero/Jump__003.png",Texture.class);
        manager.load("personajes/aventurero/Jump__004.png",Texture.class);
        manager.load("personajes/aventurero/Jump__005.png",Texture.class);
        manager.load("personajes/aventurero/Jump__006.png",Texture.class);
        manager.load("personajes/aventurero/Jump__007.png",Texture.class);
        manager.load("personajes/aventurero/Jump__008.png",Texture.class);
        manager.load("personajes/aventurero/Jump__009.png",Texture.class);
        //muerto
        manager.load("personajes/aventurero/Dead__000.png",Texture.class);
        manager.load("personajes/aventurero/Dead__001.png",Texture.class);
        manager.load("personajes/aventurero/Dead__002.png",Texture.class);
        manager.load("personajes/aventurero/Dead__003.png",Texture.class);
        manager.load("personajes/aventurero/Dead__004.png",Texture.class);
        manager.load("personajes/aventurero/Dead__005.png",Texture.class);
        manager.load("personajes/aventurero/Dead__006.png",Texture.class);
        manager.load("personajes/aventurero/Dead__007.png",Texture.class);
        manager.load("personajes/aventurero/Dead__008.png",Texture.class);
        manager.load("personajes/aventurero/Dead__009.png",Texture.class);

        //paisajes
        manager.load("paisajes/dia/png/Object/Stone.png",Texture.class);
        manager.load("paisajes/dia/png/Object/charcoCola.png",Texture.class);
        manager.load("paisajes/dia/png/Object/Mushroom_2.png",Texture.class);
        manager.load("paisajes/dia/png/Tiles/2.png",Texture.class);
        manager.load("paisajes/dia/png/Tiles/14.png",Texture.class);
        manager.load("paisajes/dia/png/Tiles/17.png",Texture.class);
        manager.load("paisajes/dia/png/Tiles/1.png",Texture.class);
        manager.load("paisajes/dia/png/Tiles/3.png",Texture.class);

        //fondo
        manager.load("paisajes/dia/png/BG/BG.png",Texture.class);
        manager.load("bg/fondoTops.png",Texture.class);

        //menu
        manager.load("gameover.png",Texture.class);
        manager.load("titulo.png",Texture.class);

        //sonidos & musica
        manager.load("audio/die.ogg", Sound.class);
        manager.load("audio/jump.ogg", Sound.class);
        manager.load("audio/song.ogg", Music.class);


        //Meto la pantalla de carga para iniciar el juego
        cargandoScreen = new CargandoScreen(this);
        this.setScreen(cargandoScreen);
    }


    public void finishLoading() {
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        topPartidaBasica= new TopPartidaBasica(this);
        userScreen=new UserScreen(this);
        userLogInScreen= new UserLoginScreen(this);
        //creditsScreen = new CreditsScreen(this);
        setScreen(menuScreen);
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
