package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.ayuda.Ajustes;
import com.endlessrunner.ayuda.DatosUsuarioXML;

/**
 * Created by Jongui on 27/03/2018.
 */

public class MenuScreen extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;
    private Image tituloa;
    private BitmapFont fuente;
    private Label welcomeLbl;
    private Table taula;
    //private TextButton jokatu,puntuazioak,aukerak,kontua,kredituak;
    private ImageButton jokatuBotoia,puntuazioaBotoia,kontuaBotoia,aukeraBotoia,kredituakBotoia;


    public MenuScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("bg/FondoMenu.png");

        tituloa = new Image(jokoa.getManager().get("titulo.png", Texture.class));

        //"botones/Euskera/jokatu.png"
        jokatuBotoia = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/jokatu.png")))));
        puntuazioaBotoia =new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/puntuazioak.png")))));
        kontuaBotoia = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/kontua.png")))));
        aukeraBotoia = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/aukerak.png")))));
        kredituakBotoia = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/kredituak.png")))));


        jokatuBotoia.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.gameScreen);
            }
        });

        puntuazioaBotoia.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.topPartidaBasica);
            }
        });

        kredituakBotoia.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.creditosScreen);
            }
        });

        aukeraBotoia.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.ajustesScreen);
            }
        });

        kontuaBotoia.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(DatosUsuarioXML.user.equals("Anonimo")){
                    jokoa.setScreen(jokoa.userLogInScreen);
                }else{
                    jokoa.setScreen(jokoa.userScreen);
                }

            }
        });

        tituloa.setPosition( 20, 250 );
        tituloa.setScale(0.8f);

        jokatuBotoia.setSize(175, 80);
        puntuazioaBotoia.setSize(200, 80);
        aukeraBotoia.setSize(120, 50);
        kontuaBotoia.setSize(120, 50);
        kredituakBotoia.setSize(120, 50);

        puntuazioaBotoia.setPosition(2, 155);
        jokatuBotoia.setPosition(174, 35);
        kredituakBotoia.setPosition(520, 50);
        aukeraBotoia.setPosition(390, 100);
        kontuaBotoia.setPosition(520, 180);

        fuente = jokoa.getManager().get("size20.ttf", BitmapFont.class);
        welcomeLbl =new Label("", new Label.LabelStyle(fuente, Color.BLACK) );
        taula = new Table();
        taula.top().right();
        taula.setFillParent(true);
        taula.add(welcomeLbl);
        taula.padTop(10);
        taula.padRight(10);

        stage.addActor(taula);
        stage.addActor(tituloa);
        stage.addActor(jokatuBotoia);
        stage.addActor(puntuazioaBotoia);
        stage.addActor(aukeraBotoia);
        stage.addActor(kontuaBotoia);
        stage.addActor(kredituakBotoia);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        if(!DatosUsuarioXML.user.equals("Anonimo")){
            if (Ajustes.Idioma.equals("ES")) {
                welcomeLbl.setText("Bienvenido, " + DatosUsuarioXML.user + "!");
            }else if (Ajustes.Idioma.equals("EN")) {
                welcomeLbl.setText("Welcome, " + DatosUsuarioXML.user + "!");
            }else{
                welcomeLbl.setText("Ongi etorri, " + DatosUsuarioXML.user + "!");
            }
        }else{
            welcomeLbl.setText("");
        }
        jokatuBotoia.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/jokatu.png"))));
        puntuazioaBotoia.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/puntuazioak.png"))));
        kontuaBotoia.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/kontua.png"))));
        aukeraBotoia.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/aukerak.png"))));
        kredituakBotoia.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/kredituak.png"))));
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
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        jokoa.batch.end();

        stage.draw();
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
}

