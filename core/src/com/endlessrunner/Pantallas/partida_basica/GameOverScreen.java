package com.endlessrunner.Pantallas.partida_basica;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;
import com.endlessrunner.ayuda.DatosUsuarioXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.endlessrunner.ayuda.DatosUsuarioXML.hasieratuDatuakXML;
import static com.endlessrunner.ayuda.DatosUsuarioXML.localizacionXML;

/**
 * Created by Jongui on 27/03/2018.
 */

public class GameOverScreen extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {

    private Stage stage;
    private Skin skin;
    private Image gameover;
    private TextButton retry, menu,stats,ranking;


    public GameOverScreen(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        retry = new TextButton("Berriz", skin);
        menu = new TextButton("Menua", skin);
        stats= new TextButton("Estadistikak", skin);
        ranking= new TextButton("Ranking", skin);

        gameover = new Image(jokoa.getManager().get("gameover.png", Texture.class));

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.gameScreen);
            }
        });

        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });

        stats.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!DatosUsuarioXML.user.equals("Anonimo"))
                    jokoa.setScreen(jokoa.userScreen);
            }
        });

        ranking.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!DatosUsuarioXML.user.equals("Anonimo"))
                    jokoa.setScreen(jokoa.topPartidaBasica);
            }
        });

        gameover.setPosition(320 - gameover.getWidth() / 2, 320 - gameover.getHeight());
        retry.setSize(200, 80);
        menu.setSize(200, 80);
        stats.setSize(200, 80);
        ranking.setSize(200, 80);

        retry.setPosition(60, 140);
        menu.setPosition(380, 140);
        stats.setPosition(60, 20);
        ranking.setPosition(380, 20);

        stage.addActor(retry);
        stage.addActor(gameover);
        stage.addActor(menu);
        stage.addActor(stats);
        stage.addActor(ranking);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        //final String localizacionXML = "xml/datos.xml";

        //XML
        try {

            File f = new File(localizacionXML);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //SI EL XML EXISTE DESDE ANTES
            if(f.exists() && !f.isDirectory()) {
                Document doc = docBuilder.parse(localizacionXML);

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
                StreamResult result = new StreamResult(new File(localizacionXML));
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
                StreamResult result = new StreamResult(new File(localizacionXML));

                transformer.transform(source, result);
            }

        }catch (Exception e){
            System.err.println("Ha habido un error al crear el archivo XML, imposible guardar datos de partida");
        }

        hasieratuDatuakXML();


        DatosUsuarioXML.ultimaPuntuacion=GameScreen.puntuacion;

        GameScreen.cantidadSaltos = 0;
        GameScreen.cantidadSetas = 0;
        GameScreen.cantidadColas = 0;
        GameScreen.puntuacion = 0;
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