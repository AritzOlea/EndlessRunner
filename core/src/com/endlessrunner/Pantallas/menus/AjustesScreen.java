package com.endlessrunner.Pantallas.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.Pantallas.partida_basica.BaseScreen;
import com.endlessrunner.ayuda.Ajustes;
import com.endlessrunner.ayuda.DatosUsuarioXML;

import static com.endlessrunner.ayuda.Ajustes.DatuakGordeXMLajustes;

/**
 * Created by Jongui on 20/04/2018.
 */

public class AjustesScreen extends BaseScreen {


    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;
    private ImageButton idiomaEuskera,idiomaGaztelera,idiomaIngles;
    private ImageButton sonido,musica;
    private ImageButton atras;

    public AjustesScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("bg/FondoAjustes.png");

        String rutaAtras,rutaIdiomaEuskera=".png",rutaIdiomaGaztelera=".png",rutaIdiomaIngelesa=".png";


        if(Ajustes.Idioma.equals("EUS")){
            rutaIdiomaEuskera="_aukeratuta.png";
            rutaAtras="euskera";
        }else if(Ajustes.Idioma.equals("ES")){
            rutaIdiomaGaztelera="_aukeratuta.png";
            rutaAtras="gaztelera";
        }else{//EN
            rutaIdiomaIngelesa="_aukeratuta.png";
            rutaAtras="ingelesa";
        }

        idiomaEuskera = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/eus"+rutaIdiomaEuskera)))));
        idiomaGaztelera =new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/es"+rutaIdiomaGaztelera)))));
        idiomaIngles = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/en"+rutaIdiomaIngelesa)))));
        atras = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+rutaAtras+"/atzera.png")))));


        if(Ajustes.Musica){
            musica = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/musica_on.png")))));
        }else{
            musica = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/musica_off.png")))));
        }
        if(Ajustes.Sonidos){
            sonido = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/sonidos_on.png")))));
        }else{
            sonido = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/sonidos_off.png")))));
        }





        idiomaEuskera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Ajustes.Idioma="EUS";
                idiomaEuskera.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/eus_aukeratuta.png"))));
                idiomaGaztelera.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/es.png"))));
                idiomaIngles.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/en.png"))));
                atras.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/euskera/atzera.png"))));

            }
        });

        idiomaGaztelera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Ajustes.Idioma="ES";
                idiomaEuskera.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/eus.png"))));
                idiomaGaztelera.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/es_aukeratuta.png"))));
                idiomaIngles.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/en.png"))));
                atras.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/gaztelera/atzera.png"))));

            }
        });

        idiomaIngles.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Ajustes.Idioma="EN";
                idiomaEuskera.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/eus.png"))));
                idiomaGaztelera.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/es.png"))));
                idiomaIngles.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/idiomas/en_aukeratuta.png"))));
                atras.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/ingelesa/atzera.png"))));
            }
        });


        musica.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                if(Ajustes.Musica){
                    Ajustes.Musica=false;
                    musica.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/musica_off.png"))));
                }else{
                    Ajustes.Musica=true;
                    musica.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/musica_on.png"))));
                }


            }
        });

        sonido.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(Ajustes.Sonidos){
                    Ajustes.Sonidos=false;
                    sonido.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/sonidos_off.png"))));
                }else{
                    Ajustes.Sonidos=true;
                    sonido.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/musica/sonidos_on.png"))));
                }

            }
        });

        atras.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
                DatuakGordeXMLajustes();
            }
        });


        sonido.setSize(40, 40);
        musica.setSize(40, 40);

        idiomaEuskera.setSize(80, 80);
        idiomaGaztelera.setSize(80, 80);
        idiomaIngles.setSize(80, 80);

        atras.setSize(100, 80);



        sonido.setPosition(300, 250);
        musica.setPosition(360, 250);

        idiomaEuskera.setPosition(80, 100);
        idiomaGaztelera.setPosition(200, 100);
        idiomaIngles.setPosition(320, 100);

        atras.setPosition(500,50);


        stage.addActor(sonido);
        stage.addActor(musica);
        stage.addActor(idiomaEuskera);
        stage.addActor(idiomaGaztelera);
        stage.addActor(idiomaIngles);
        stage.addActor(atras);

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
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        jokoa.batch.begin();
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
