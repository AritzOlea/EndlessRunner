package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.ayuda.Ajustes;
import com.endlessrunner.ayuda.DatosUsuarioXML;

import java.text.DecimalFormat;

import static com.endlessrunner.ayuda.DatosUsuarioXML.avgScore;
import static com.endlessrunner.ayuda.DatosUsuarioXML.saioaItxi;
import static com.endlessrunner.ayuda.DatosUsuarioXML.topScore;
import static com.endlessrunner.ayuda.DatosUsuarioXML.user;
import static com.endlessrunner.ayuda.servidor.ControlServidor.datuakPasaZerbitzariari;
import static com.endlessrunner.ayuda.servidor.ControlServidor.logInZuzena;

/**
 * Created by Jongui on 31/03/2018.
 */

public class UserScreen extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;


    private ImageButton atzera,itxi;

    private Label user,topScore,avgScore, games, jumps, mashrooms, glues, falls, collision, cameraout;



    public UserScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("bg/fondoUser.png");


    }

    @Override
    public void show() {

        stage.clear();



        atzera = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/atzera.png")))));
        atzera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });
        atzera.setSize(100,50);
        atzera.setPosition(100,30);

        stage.addActor(atzera);



        itxi = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/itxi.png")))));
        itxi.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                UserLoginScreen.passLoginString="";
                UserLoginScreen.usuarioLoginString="";
                datuakPasaZerbitzariari();
                saioaItxi();
                jokoa.setScreen(jokoa.menuScreen);
            }
        });
        itxi.setSize(100,50);
        itxi.setPosition(112,150);
        stage.addActor(itxi);


        BitmapFont fuente = jokoa.getManager().get("size10.ttf", BitmapFont.class);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String puntosMedia = df.format(DatosUsuarioXML.avgScore);

        if(Ajustes.Idioma.equals("EUS")){
            user =new Label("Erabiltzailea: \n     "+DatosUsuarioXML.user, new Label.LabelStyle(fuente, Color.valueOf("333030")) );
            topScore =new Label("Puntuazio altuena: "+DatosUsuarioXML.topScore, new Label.LabelStyle(fuente, Color.BLACK) );
            avgScore =new Label("Puntuen media: "+puntosMedia, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            games =new Label("Jokatutako partidak: "+DatosUsuarioXML.playedGames, new Label.LabelStyle(fuente, Color.BLACK) );
            jumps =new Label("Jauzi kopurua: "+DatosUsuarioXML.totallJumps, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            mashrooms =new Label("Perretxiko kopurua: "+DatosUsuarioXML.totalMashrooms, new Label.LabelStyle(fuente, Color.BLACK) );
            glues =new Label("Kola kopurua: "+DatosUsuarioXML.totalGlues, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            falls =new Label("Erorketa heriotzak: "+DatosUsuarioXML.fallDeaths, new Label.LabelStyle(fuente, Color.BLACK) );
            collision =new Label("Talka heriotzak: "+DatosUsuarioXML.collisionDeaths, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            cameraout =new Label("Kamera heriotzak: "+DatosUsuarioXML.collisionDeaths, new Label.LabelStyle(fuente, Color.BLACK) );

        }else if(Ajustes.Idioma.equals("ES")){
            user =new Label("Usuario: \n     "+DatosUsuarioXML.user, new Label.LabelStyle(fuente, Color.valueOf("333030")) );
            topScore =new Label("Puntuaje máximo: "+DatosUsuarioXML.topScore, new Label.LabelStyle(fuente, Color.BLACK) );
            avgScore =new Label("Media de puntos: "+puntosMedia, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            games =new Label("Partidas jugadas: "+DatosUsuarioXML.playedGames, new Label.LabelStyle(fuente, Color.BLACK) );
            jumps =new Label("Total de saltos: "+DatosUsuarioXML.totallJumps, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            mashrooms =new Label("Total de setas: "+DatosUsuarioXML.totalMashrooms, new Label.LabelStyle(fuente, Color.BLACK) );
            glues =new Label("Total de colas: "+DatosUsuarioXML.totalGlues, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            falls =new Label("Muertes por caida: "+DatosUsuarioXML.fallDeaths, new Label.LabelStyle(fuente, Color.BLACK) );
            collision =new Label("Muertes por colision: "+DatosUsuarioXML.collisionDeaths, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            cameraout =new Label("Muertes por camara: "+DatosUsuarioXML.collisionDeaths, new Label.LabelStyle(fuente, Color.BLACK) );

        }else{//EN
            user =new Label("User: \n     "+DatosUsuarioXML.user, new Label.LabelStyle(fuente, Color.valueOf("333030")) );
            topScore =new Label("TopScore: "+DatosUsuarioXML.topScore, new Label.LabelStyle(fuente, Color.BLACK) );
            avgScore =new Label("AvgScore: "+puntosMedia, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            games =new Label("Played Games: "+DatosUsuarioXML.playedGames, new Label.LabelStyle(fuente, Color.BLACK) );
            jumps =new Label("Total Jumps: "+DatosUsuarioXML.totallJumps, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            mashrooms =new Label("Total Mashrooms: "+DatosUsuarioXML.totalMashrooms, new Label.LabelStyle(fuente, Color.BLACK) );
            glues =new Label("Total Glues: "+DatosUsuarioXML.totalGlues, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            falls =new Label("Fall Deaths: "+DatosUsuarioXML.fallDeaths, new Label.LabelStyle(fuente, Color.BLACK) );
            collision =new Label("Collision Deaths: "+DatosUsuarioXML.collisionDeaths, new Label.LabelStyle(fuente, Color.DARK_GRAY) );
            cameraout =new Label("CameraOut Deaths: "+DatosUsuarioXML.collisionDeaths, new Label.LabelStyle(fuente, Color.BLACK) );

        }

        user.setX(46);user.setY(240);stage.addActor(user);


        topScore.setX(340);topScore.setY(280);stage.addActor(topScore);
        avgScore.setX(340);avgScore.setY(255);stage.addActor(avgScore);
        games.setX(340);games.setY(230);stage.addActor(games);
        jumps.setX(340);jumps.setY(205);stage.addActor(jumps);
        mashrooms.setX(340);mashrooms.setY(180);stage.addActor(mashrooms);
        glues.setX(340);glues.setY(155);stage.addActor(glues);
        falls.setX(340);falls.setY(130);stage.addActor(falls);
        collision.setX(340);collision.setY(105);stage.addActor(collision);
        cameraout.setX(340);cameraout.setY(80);stage.addActor(cameraout);



        Gdx.input.setInputProcessor(stage);

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
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth() , Gdx.graphics.getHeight() );
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
