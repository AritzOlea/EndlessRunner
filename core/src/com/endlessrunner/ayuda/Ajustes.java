package com.endlessrunner.ayuda;

import com.badlogic.gdx.audio.Music;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Jongui on 20/04/2018.
 */

public class Ajustes {

    public static final String localizacionXMLAjustes = "xml/ajustes.xml";

                                //aukerak: ES,EUS,EN
    public static String Idioma="EUS";
    public static String Ruta="euskera";

    public static boolean Musica=true;
    public static boolean Sonidos=true;


    public static void InicializarDatosDesdeXML(){

        //XML
        try {

            File f = new File(localizacionXMLAjustes);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //SI EL XML EXISTE DESDE ANTES
            if(f.exists() && !f.isDirectory()) {
                Document doc = docBuilder.parse(localizacionXMLAjustes);

                Node node;String comprobarBool;


                node = doc.getElementsByTagName("Idioma").item(0);
                Idioma=node.getTextContent();

                if(Idioma.equals("ES"))Ruta="gaztelera";
                if(Idioma.equals("EUS"))Ruta="euskera";
                if(Idioma.equals("EN"))Ruta="ingelesa";

                node = doc.getElementsByTagName("Musica").item(0);
                comprobarBool=node.getTextContent();
                if(comprobarBool.equals("Si"))Musica=true;else Musica=false;

                node = doc.getElementsByTagName("Sonido").item(0);
                comprobarBool=node.getTextContent();
                if(comprobarBool.equals("Si"))Sonidos=true;else Sonidos=false;



            }else {//SI EL XML NO EXISTE Y HAY QUE CREARLO

                DatuakGordeXMLajustes();

            }



        }catch (Exception e){
            System.err.println("Ha habido un error al crear el archivo XML, imposible inicializar los ajustes!");
        }

    }

    public static void DatuakGordeXMLajustes(){

        try {

            File f = new File(localizacionXMLAjustes);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Ajustes");
            doc.appendChild(rootElement);

            Element user = doc.createElement("Idioma");
            user.appendChild(doc.createTextNode(Idioma));
            rootElement.appendChild(user);

            Element musica = doc.createElement("Musica");
            if(Musica)musica.appendChild(doc.createTextNode("Si"));else musica.appendChild(doc.createTextNode("No"));
            rootElement.appendChild(musica);

            Element sonido = doc.createElement("Sonido");
            if(Sonidos)sonido.appendChild(doc.createTextNode("Si"));else sonido.appendChild(doc.createTextNode("No"));
            rootElement.appendChild(sonido);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(localizacionXMLAjustes));

            transformer.transform(source, result);

        }catch (Exception e){

        }

    }


    public static void GuardarDatosEnXML(){

    }

}
