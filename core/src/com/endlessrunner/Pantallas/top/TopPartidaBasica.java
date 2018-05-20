package com.endlessrunner.Pantallas.top;

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
import com.endlessrunner.ayuda.servidor.ControlServidor;

import static com.endlessrunner.ayuda.servidor.ControlServidor.datuakPasaZerbitzariari;
import static com.endlessrunner.ayuda.servidor.ControlServidor.nicks;
import static com.endlessrunner.ayuda.servidor.ControlServidor.perderTiempo;
import static com.endlessrunner.ayuda.servidor.ControlServidor.puntuaciones;

/**
 * Created by Jongui on 30/03/2018.
 */

public class TopPartidaBasica extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;
    private Image tituloa;
    private ImageButton atzera;
    private Label user1,user2,user3,user4,user5,puntosUser1,puntosUser2,puntosUser3,puntosUser4,puntosUser5;
    private Label user,puntos;


    public TopPartidaBasica(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("bg/fondoTops.png");









    }


    private boolean cargado=true,listo=false;

    @Override
    public void show() {

        stage.clear();

        //Puntuazioak lortzeko funtzioari deitu...()
        user1=new Label("",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser1=new Label("",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user1.setX(75);user1.setY(240);puntosUser1.setX(180);puntosUser1.setY(240);
        stage.addActor(user1);stage.addActor(puntosUser1);

        user2=new Label("",new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        puntosUser2=new Label("",new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        user2.setX(75);user2.setY(210);puntosUser2.setX(180);puntosUser2.setY(210);
        stage.addActor(user2);stage.addActor(puntosUser2);

        user3=new Label("",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser3=new Label("",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user3.setX(75);user3.setY(180);puntosUser3.setX(180);puntosUser3.setY(180);
        stage.addActor(user3);stage.addActor(puntosUser3);

        user4=new Label("",new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        puntosUser4=new Label("",new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        user4.setX(75);user4.setY(150);puntosUser4.setX(180);puntosUser4.setY(150);
        stage.addActor(user4);stage.addActor(puntosUser4);

        user5=new Label("",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser5=new Label("",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        user5.setX(75);user5.setY(120);puntosUser5.setX(180);puntosUser5.setY(120);
        stage.addActor(user5);stage.addActor(puntosUser5);


        user=new Label(DatosUsuarioXML.user,new Label.LabelStyle(new BitmapFont(), Color.valueOf("333030") ));
        puntos=new Label(DatosUsuarioXML.topScore+"",new Label.LabelStyle(new BitmapFont(), Color.valueOf("333030") ));
        user.setX(444);user.setY(210);puntos.setX(500);puntos.setY(180);
        stage.addActor(user);stage.addActor(puntos);

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



        listo=false;
        cargado=true;

        new Thread() {
            public void run() {
                if(!DatosUsuarioXML.user.equals("Anonimo")) {
                    ControlServidor.insertarRegistro(1, DatosUsuarioXML.topScore, DatosUsuarioXML.user);
                    datuakPasaZerbitzariari();

                    /*perderTiempo();
                    perderTiempo();
                    perderTiempo();
                    perderTiempo();*/
                }

                ControlServidor.conseguirTop(5,1);
                listo=true;
                cargado=false;
            }
        }.start();










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



    int n=0;
    String[] cargandoS={".",".",".","..","..","..","...","...","..."};

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();


        if(listo){
            user1.setText("1. "+nicks.get(0));
            user2.setText("2. "+nicks.get(1));
            user3.setText("3. "+nicks.get(2));
            user4.setText("4. "+nicks.get(3));
            user5.setText("5. "+nicks.get(4));

            puntosUser1.setText(puntuaciones.get(0));
            puntosUser2.setText(puntuaciones.get(1));
            puntosUser3.setText(puntuaciones.get(2));
            puntosUser4.setText(puntuaciones.get(3));
            puntosUser5.setText(puntuaciones.get(4));

            listo=false;
        }

        if(cargado){
            n++;if(n==cargandoS.length)n=0;
            puntosUser3.setText(cargandoS[n]);
        }







        jokoa.batch.begin();
        //HAY QUE MULTIPLICAR LA RESOLUCIÓN DE PANTALLA POR ALGÚN NUMERO PARA QUE QUEDE BIEN
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
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

