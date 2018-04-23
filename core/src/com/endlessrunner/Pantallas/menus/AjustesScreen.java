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
    private Texture per1Texture,per2Texture;
    private Texture[] per1TextureMov,per2TextureMov;
    private Stage stage;
    private Skin skin;
    private ImageButton idiomaEuskera,idiomaGaztelera,idiomaIngles;
    private ImageButton sonido,musica;
    private ImageButton per1,per2;
    private ImageButton atras;

    public AjustesScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("bg/FondoAjustes.png");

        CargarPersonajes();

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


        if(Ajustes.Personaje.equals("aventurero")){
            per1= new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje1_aukeratuta.png")))));
            per2= new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje2.png")))));
        }else{
            per1= new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje1.png")))));
            per2= new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje2_aukeratuta.png")))));
        }

        per1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Ajustes.Personaje="aventurero";IndiceTextura=0;
                per1.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje1_aukeratuta.png"))));
                per2.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje2.png"))));
            }
        });

        per2.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Ajustes.Personaje="ninja";IndiceTextura=0;
                per1.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje1.png"))));
                per2.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/personajes/personaje2_aukeratuta.png"))));
            }
        });


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


        per1.setSize(80, 50);
        per2.setSize(80, 50);

        sonido.setSize(40, 40);
        musica.setSize(40, 40);

        idiomaEuskera.setSize(80, 80);
        idiomaGaztelera.setSize(80, 80);
        idiomaIngles.setSize(80, 80);

        atras.setSize(100, 80);



        sonido.setPosition(400, 300);
        musica.setPosition(460, 300);

        idiomaEuskera.setPosition(80, 200);
        idiomaGaztelera.setPosition(200, 200);
        idiomaIngles.setPosition(320, 200);

        atras.setPosition(500,50);

        per1.setPosition(100, 20);
        per2.setPosition(280, 20);



        stage.addActor(per1);
        stage.addActor(per2);
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

    public int IndiceTextura,cadaTexturaXveces;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        jokoa.batch.begin();
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        if(cadaTexturaXveces<3){
            cadaTexturaXveces++;
        }else{
            cadaTexturaXveces=0;
            IndiceTextura++;if(IndiceTextura==10)IndiceTextura=0;
        }

        if(Ajustes.Personaje.equals("aventurero")){
            jokoa.batch.draw(per1TextureMov[IndiceTextura],100, 80, 80 , 80 );
            jokoa.batch.draw(per2Texture,270, 80, 80 , 80 );
        }else{
            jokoa.batch.draw(per1Texture,100, 80, 80 , 80 );
            jokoa.batch.draw(per2TextureMov[IndiceTextura],270, 80, 80 , 80 );
        }

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

    public void CargarPersonajes(){

        //personajes
        IndiceTextura=0;cadaTexturaXveces=0;
        per1Texture=jokoa.getManager().get("personajes/aventurero/Idle__000.png");

        per1TextureMov = new  Texture[10];
        per1TextureMov[0] = jokoa.getManager().get("personajes/aventurero/Run__000.png",Texture.class);
        per1TextureMov[1] = jokoa.getManager().get("personajes/aventurero/Run__001.png",Texture.class);
        per1TextureMov[2] = jokoa.getManager().get("personajes/aventurero/Run__002.png",Texture.class);
        per1TextureMov[3] = jokoa.getManager().get("personajes/aventurero/Run__003.png",Texture.class);
        per1TextureMov[4] = jokoa.getManager().get("personajes/aventurero/Run__004.png",Texture.class);
        per1TextureMov[5] = jokoa.getManager().get("personajes/aventurero/Run__005.png",Texture.class);
        per1TextureMov[6] = jokoa.getManager().get("personajes/aventurero/Run__006.png",Texture.class);
        per1TextureMov[7] = jokoa.getManager().get("personajes/aventurero/Run__007.png",Texture.class);
        per1TextureMov[8] = jokoa.getManager().get("personajes/aventurero/Run__008.png",Texture.class);
        per1TextureMov[9] = jokoa.getManager().get("personajes/aventurero/Run__009.png",Texture.class);

        per2Texture=jokoa.getManager().get("personajes/ninja/Idle__000.png");
        per2TextureMov = new  Texture[10];
        per2TextureMov[0] = jokoa.getManager().get("personajes/ninja/Run__000.png",Texture.class);
        per2TextureMov[1] = jokoa.getManager().get("personajes/ninja/Run__001.png",Texture.class);
        per2TextureMov[2] = jokoa.getManager().get("personajes/ninja/Run__002.png",Texture.class);
        per2TextureMov[3] = jokoa.getManager().get("personajes/ninja/Run__003.png",Texture.class);
        per2TextureMov[4] = jokoa.getManager().get("personajes/ninja/Run__004.png",Texture.class);
        per2TextureMov[5] = jokoa.getManager().get("personajes/ninja/Run__005.png",Texture.class);
        per2TextureMov[6] = jokoa.getManager().get("personajes/ninja/Run__006.png",Texture.class);
        per2TextureMov[7] = jokoa.getManager().get("personajes/ninja/Run__007.png",Texture.class);
        per2TextureMov[8] = jokoa.getManager().get("personajes/ninja/Run__008.png",Texture.class);
        per2TextureMov[9] = jokoa.getManager().get("personajes/ninja/Run__009.png",Texture.class);
    }

}
