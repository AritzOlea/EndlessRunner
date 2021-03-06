package com.endlessrunner.Pantallas.partida_basica;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.ayuda.Ajustes;
import com.endlessrunner.ayuda.DatosUsuarioXML;
import com.endlessrunner.ayuda.servidor.ControlServidor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.endlessrunner.ayuda.DatosUsuarioXML.hasieratuDatuakXML;
import static com.endlessrunner.ayuda.DatosUsuarioXML.localizacionXML;
import static com.endlessrunner.ayuda.servidor.ControlServidor.TopEnString;
import static com.endlessrunner.ayuda.servidor.ControlServidor.conseguirTopEnString;
import static com.endlessrunner.ayuda.servidor.ControlServidor.datuakPasaZerbitzariari;

/**
 * Created by Jongui on 27/03/2018.
 */

public class GameOverScreen extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {


    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;

    //private Image gameover;
    //private TextButton retry, menu,stats,ranking;

    private ImageButton jokatu,atzera;

    private Label resultadoLabel,rankingLabel;

    public GameOverScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("bg/FondoPantallaPostPartida.png");




    }

    @Override
    public void show() {

        stage.clear();

        jokatu = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/jokatu.png")))));
        jokatu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.gameScreen);
            }
        });
        jokatu.setSize(196,64);
        jokatu.setPosition(93,19);
        stage.addActor(jokatu);


        atzera = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("input/"+ Ajustes.Ruta+"/atzera.png")))));
        atzera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });
        atzera.setSize(196,64);
        atzera.setPosition(353,19);
        stage.addActor(atzera);



        resultadoLabel = new Label("",new Label.LabelStyle(jokoa.getManager().get("size20.ttf", BitmapFont.class), Color.valueOf("333030")));
        if (Ajustes.Idioma.equals("ES")) {
            resultadoLabel.setText("Puntuacion: \n"+GameScreen.puntuacion);
        }else if (Ajustes.Idioma.equals("EN")) {
            resultadoLabel.setText("Puntuation: \n"+GameScreen.puntuacion);
        }else{
            resultadoLabel.setText("Puntuaketa: \n        "+GameScreen.puntuacion);
        }
        rankingLabel = new Label("\n\n\n\nCargando\n\n\n\n\n\n",new Label.LabelStyle(jokoa.getManager().get("size20.ttf", BitmapFont.class), Color.BLACK));

        resultadoLabel.setX(75);
        resultadoLabel.setY(185);

        rankingLabel.setX(400);
        rankingLabel.setY(80);

        stage.addActor(resultadoLabel);
        stage.addActor(rankingLabel);



        Gdx.input.setInputProcessor(stage);


        //XML
        try {

            File myDir = new File(Gdx.files.getLocalStoragePath(), "xml");
            myDir.mkdir();

            File f = new File(Gdx.files.getLocalStoragePath() +  "/" + localizacionXML);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //SI EL XML EXISTE DESDE ANTES
            if(f.exists() && !f.isDirectory()) {
                String xmlContent = "";

                Scanner sc= new Scanner (new FileReader(Gdx.files.getLocalStoragePath() +  "/" + localizacionXML));

                while(sc.hasNext()) {
                    if (sc.hasNext())
                        xmlContent += sc.next() + " ";
                    else
                        xmlContent += sc.next();
                }

                Document doc = docBuilder.parse(new InputSource(new StringReader(xmlContent)));

                //CONSIGO EL ELEMENTO ROOT DEL XML
                Node root = doc.getFirstChild();

                //HAGO LAS ACTUALIZACIONES QUE HAY QUE HACER
                Node topScore = doc.getElementsByTagName("TopScore").item(0);
                if (GameScreen.puntuacion > Integer.parseInt(topScore.getTextContent()))
                    topScore.setTextContent(Integer.toString(GameScreen.puntuacion));

                Node avgScore = doc.getElementsByTagName("AvgScore").item(0);
                Node playedGames = doc.getElementsByTagName("PlayedGames").item(0);
                float totalScore = Float.parseFloat(avgScore.getTextContent()) * Integer.parseInt(playedGames.getTextContent());
                playedGames.setTextContent(Integer.toString(Integer.parseInt(playedGames.getTextContent()) + 1));
                avgScore.setTextContent(Float.toString((totalScore + GameScreen.puntuacion) / Integer.parseInt(playedGames.getTextContent())));

                Node totalJumps = doc.getElementsByTagName("TotalJumps").item(0);
                totalJumps.setTextContent(Integer.toString(Integer.parseInt(totalJumps.getTextContent()) + GameScreen.cantidadSaltos));

                Node totalMashrooms = doc.getElementsByTagName("TotalMashrooms").item(0);
                totalMashrooms.setTextContent(Integer.toString(Integer.parseInt(totalMashrooms.getTextContent()) + GameScreen.cantidadSetas));

                Node totalGlues = doc.getElementsByTagName("TotalGlues").item(0);
                totalGlues.setTextContent(Integer.toString(Integer.parseInt(totalGlues.getTextContent()) + GameScreen.cantidadColas));

                if (GameScreen.causaMuerte == GameScreen.CausaMuerte.MONTAÑA){
                    Node collisionDeaths = doc.getElementsByTagName("CollisionDeaths").item(0);
                    collisionDeaths.setTextContent(Integer.toString(Integer.parseInt(collisionDeaths.getTextContent()) + 1));
                }else if (GameScreen.causaMuerte == GameScreen.CausaMuerte.CAIDA){
                    Node fallDeaths = doc.getElementsByTagName("FallDeaths").item(0);
                    fallDeaths.setTextContent(Integer.toString(Integer.parseInt(fallDeaths.getTextContent()) + 1));
                }else if (GameScreen.causaMuerte == GameScreen.CausaMuerte.FUERA_CAMARA){
                    Node cameraOutDeaths = doc.getElementsByTagName("CameraOutDeaths").item(0);
                    cameraOutDeaths.setTextContent(Integer.toString(Integer.parseInt(cameraOutDeaths.getTextContent()) + 1));
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(Gdx.files.getLocalStoragePath() +  "/" + localizacionXML).getPath());

                transformer.transform(source, result);

            //SI EL XML NO EXISTE Y HAY QUE CREARLO
            }else {
                //root
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("UserData");
                doc.appendChild(rootElement);

                //elementos que cuelgan de root
                Element user = doc.createElement("User");
                user.appendChild(doc.createTextNode("Anonimo"));
                rootElement.appendChild(user);

                Element topScore = doc.createElement("TopScore");
                topScore.appendChild(doc.createTextNode(Integer.toString(GameScreen.puntuacion)));
                rootElement.appendChild(topScore);

                Element avgScore = doc.createElement("AvgScore");
                avgScore.appendChild(doc.createTextNode(Integer.toString(GameScreen.puntuacion)));
                rootElement.appendChild(avgScore);

                Element playedGames = doc.createElement("PlayedGames");
                playedGames.appendChild(doc.createTextNode("1"));
                rootElement.appendChild(playedGames);

                Element totalJumps = doc.createElement("TotalJumps");
                totalJumps.appendChild(doc.createTextNode(Integer.toString(GameScreen.cantidadSaltos)));
                rootElement.appendChild(totalJumps);

                Element totalMashrooms = doc.createElement("TotalMashrooms");
                totalMashrooms.appendChild(doc.createTextNode(Integer.toString(GameScreen.cantidadSetas)));
                rootElement.appendChild(totalMashrooms);

                Element totalGlues = doc.createElement("TotalGlues");
                totalGlues.appendChild(doc.createTextNode(Integer.toString(GameScreen.cantidadColas)));
                rootElement.appendChild(totalGlues);

                Element fallDeaths = doc.createElement("FallDeaths");
                if (GameScreen.causaMuerte == GameScreen.CausaMuerte.CAIDA)
                    fallDeaths.appendChild(doc.createTextNode("1"));
                else
                    fallDeaths.appendChild(doc.createTextNode("0"));
                rootElement.appendChild(fallDeaths);

                Element collisionDeaths = doc.createElement("CollisionDeaths");
                if (GameScreen.causaMuerte == GameScreen.CausaMuerte.MONTAÑA)
                    collisionDeaths.appendChild(doc.createTextNode("1"));
                else
                    collisionDeaths.appendChild(doc.createTextNode("0"));
                rootElement.appendChild(collisionDeaths);

                Element cameraOutDeaths = doc.createElement("CameraOutDeaths");
                if (GameScreen.causaMuerte == GameScreen.CausaMuerte.FUERA_CAMARA)
                    cameraOutDeaths.appendChild(doc.createTextNode("1"));
                else
                    cameraOutDeaths.appendChild(doc.createTextNode("0"));
                rootElement.appendChild(cameraOutDeaths);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(Gdx.files.getLocalStoragePath() +  "/" + localizacionXML).getPath());

                transformer.transform(source, result);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Ha habido un error al crear el archivo XML, imposible guardar datos de partida");
        }

        hasieratuDatuakXML();


        DatosUsuarioXML.ultimaPuntuacion=GameScreen.puntuacion;

        GameScreen.cantidadSaltos = 0;
        GameScreen.cantidadSetas = 0;
        GameScreen.cantidadColas = 0;
        GameScreen.puntuacion = 0;


        listo=false;
        new Thread() {
            public void run() {
                if(!DatosUsuarioXML.user.equals("Anonimo")) {
                    ControlServidor.insertarRegistro(1, DatosUsuarioXML.topScore, DatosUsuarioXML.user);
                    datuakPasaZerbitzariari();
                }
                conseguirTopEnString(5,1);
                listo=true;
            }
        }.start();

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


    private int indiceCargando=0;
    String[] cargandoES={"Cargando","Cargando","Cargando","Cargando.","Cargando.","Cargando.","Cargando..","Cargando..","Cargando..","Cargando...","Cargando...","Cargando..."};
    String[] cargandoEU={"Kargatzen","Kargatzen","Kargatzen","Kargatzen.","Kargatzen.","Kargatzen.","Kargatzen..","Kargatzen..","Kargatzen..","Kargatzen...","Kargatzen...","Kargatzen..."};
    String[] cargandoEN={"Loading","Loading","Loading","Loading.","Loading.","Loading.","Loading..","Loading..","Loading..","Loading...","Loading...","Loading..."};

    private boolean listo;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        if(listo){
            rankingLabel.setText(TopEnString);

        }else{
            indiceCargando++;if(indiceCargando==cargandoES.length)indiceCargando=0;

            if (Ajustes.Idioma.equals("ES")) {
                rankingLabel.setText("\n\n"+cargandoES[indiceCargando]+"\n\n\n");
            }else if (Ajustes.Idioma.equals("EN")) {
                rankingLabel.setText("\n\n"+cargandoEN[indiceCargando]+"\n\n\n");
            }else{
                rankingLabel.setText("\n\n"+cargandoEU[indiceCargando]+"\n\n\n");
            }

        }

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