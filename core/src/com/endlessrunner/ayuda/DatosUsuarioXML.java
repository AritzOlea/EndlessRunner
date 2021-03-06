package com.endlessrunner.ayuda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.endlessrunner.Pantallas.partida_basica.GameScreen;

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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Jongui on 31/03/2018.
 */

public class DatosUsuarioXML {

    public static String user;

    public static int topScore;

    public static float avgScore;

    public static int playedGames;

    public static int totallJumps;

    public static int totalMashrooms;

    public static int totalGlues;

    public static int fallDeaths;

    public static int collisionDeaths;

    public static int cameraOutDeaths;


    public static int ultimaPuntuacion=0;


    //RUTA XML
    public static final String localizacionXML = "xml/datos.xml";


    public static void hasieratuDatuakXML(){

        //XML
        try {

            //File dir = new File("xml");
            //dir.mkdirs();

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

                Node node;


                node = doc.getElementsByTagName("User").item(0);
                user=node.getTextContent();

                node = doc.getElementsByTagName("TopScore").item(0);
                topScore=Integer.parseInt(node.getTextContent());

                node = doc.getElementsByTagName("AvgScore").item(0);
                avgScore=Float.parseFloat(node.getTextContent());

                node = doc.getElementsByTagName("PlayedGames").item(0);
                playedGames=Integer.parseInt(node.getTextContent());

                node = doc.getElementsByTagName("TotalJumps").item(0);
                totallJumps=Integer.parseInt(node.getTextContent());

                node = doc.getElementsByTagName("TotalMashrooms").item(0);
                totalMashrooms=Integer.parseInt(node.getTextContent());

                node = doc.getElementsByTagName("TotalGlues").item(0);
                totalGlues=Integer.parseInt(node.getTextContent());

                node = doc.getElementsByTagName("FallDeaths").item(0);
                fallDeaths=Integer.parseInt(node.getTextContent());

                node = doc.getElementsByTagName("CollisionDeaths").item(0);
                collisionDeaths=Integer.parseInt(node.getTextContent());

                node = doc.getElementsByTagName("CameraOutDeaths").item(0);
                cameraOutDeaths=Integer.parseInt(node.getTextContent());


            }else {//SI EL XML NO EXISTE Y HAY QUE CREARLO

                user="Anonimo";
                topScore=0;
                avgScore=0;
                playedGames=0;
                totallJumps=0;
                totalMashrooms=0;
                totalGlues=0;
                fallDeaths=0;
                collisionDeaths=0;
                cameraOutDeaths=0;
                datuakGordeXML();

            }

        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Ha habido un error al crear el archivo XML, imposible guardar datos de partida");
        }

    }


    public static void datuakGordeXML(){

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("UserData");
            doc.appendChild(rootElement);

            Element user = doc.createElement("User");
            user.appendChild(doc.createTextNode(DatosUsuarioXML.user));
            rootElement.appendChild(user);

            Element topScore = doc.createElement("TopScore");
            topScore.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.topScore)));
            rootElement.appendChild(topScore);

            Element avgScore = doc.createElement("AvgScore");
            avgScore.appendChild(doc.createTextNode(Float.toString(DatosUsuarioXML.avgScore)));
            rootElement.appendChild(avgScore);

            Element playedGames = doc.createElement("PlayedGames");
            playedGames.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.playedGames)));
            rootElement.appendChild(playedGames);

            Element totalJumps = doc.createElement("TotalJumps");
            totalJumps.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.totallJumps)));
            rootElement.appendChild(totalJumps);

            Element totalMashrooms = doc.createElement("TotalMashrooms");
            totalMashrooms.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.totalMashrooms)));
            rootElement.appendChild(totalMashrooms);

            Element totalGlues = doc.createElement("TotalGlues");
            totalGlues.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.totalGlues)));
            rootElement.appendChild(totalGlues);

            Element fallDeaths = doc.createElement("FallDeaths");
            fallDeaths.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.fallDeaths)));
            rootElement.appendChild(fallDeaths);

            Element collisionDeaths = doc.createElement("CollisionDeaths");
            collisionDeaths.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.collisionDeaths)));
            rootElement.appendChild(collisionDeaths);

            Element cameraOutDeaths = doc.createElement("CameraOutDeaths");
            cameraOutDeaths.appendChild(doc.createTextNode(Integer.toString(DatosUsuarioXML.cameraOutDeaths)));
            rootElement.appendChild(cameraOutDeaths);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Gdx.files.getLocalStoragePath() +  "/" + localizacionXML).getPath());

            transformer.transform(source, result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void saioaItxi(){

        user="Anonimo";
        topScore=0;
        avgScore=0;
        playedGames=0;
        totallJumps=0;
        totalMashrooms=0;
        totalGlues=0;
        fallDeaths=0;
        collisionDeaths=0;
        cameraOutDeaths=0;
        datuakGordeXML();
    }

    public static final String[] Comprobaciones={"cabron","puta","ostia","joder","tonto","polla","coño","culo","tetas",
            "subnormal","imbecil","gilipollas","nazi","hitler","franco","sindrome","maricon","gay","homosexual","putos",
            "puto","facha","fatxa","eta","ETA","pichu","cocaina","sanax","heroina","marihuana","droga","sexo","alcohol",
            "alkol","tabaco","tabako","puta","mierda","whore","fuck","silly","cock","pussy","ass","boobs","asshole",
            "syndrome","fucking","fucking","facha","cocaine","sanax","heroin","marijuana","drug","sex","alcohol","tobacco","shit"};

    public static boolean NombreDeUsuarioAdecuado(String user){

        user=user.toLowerCase();

        if(!user.matches("\\w*")){
            return false;
        }

        for(String s: Comprobaciones)
            if(user.contains(s))
                return false;




        return true;
    }


}
