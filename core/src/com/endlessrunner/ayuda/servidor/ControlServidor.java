package com.endlessrunner.ayuda.servidor;

import com.endlessrunner.ayuda.DatosUsuarioXML;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



import static com.endlessrunner.ayuda.DatosUsuarioXML.*;


/**
 * Created by Jongui on 31/03/2018.
 */

public class ControlServidor {


    public static ArrayList<String> nicks = new ArrayList<String>();
    public static ArrayList<String> puntuaciones = new ArrayList<String>();
    public static ArrayList<String> datas = new ArrayList<String>();


    public static void conseguirTop(int numeroTop,int modoDeJuego){

        try{
            nicks.clear();
            puntuaciones.clear();
            datas.clear();

            for(int j=0;j<numeroTop;j++){
                nicks.add("");
                puntuaciones.add("");
                datas.add("");
            }


            URL url = new URL("http://sgta.webcindario.com/php/conseguirTOP.php?modoDeJuego="+modoDeJuego+"&cuantos="+numeroTop);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            String line;
            String[] datos;
            String[] entradas;

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            line=br.readLine();

            entradas = line.split("&");

            for ( int i=0; i<entradas.length; i++){
                datos=entradas[i].split(" ");
                nicks.set(i,datos[0]);
                puntuaciones.set(i,datos[1]);
                datas.set(i,datos[2]);
            }



        }catch(Exception e){

        }

    }


    public static void insertarRegistro(int modoDeJuego,int puntuacion,String nick){

        try{

            System.out.println("Nick: "+nick+"   puntuacion: "+puntuacion);
            String urlS="http://sgta.webcindario.com/php/insertarRegistro.php?modoDeJuego="+modoDeJuego+"&nick="+nick+"&puntuacion="+puntuacion;
            System.out.println(urlS);
            URL url = new URL(urlS);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            urlConnection.getInputStream();
            System.out.println("2");

        }catch(Exception e){

        }

    }

    public static boolean logInZuzena(String user, String pass){
        //http://sgta.webcindario.com/php/comprobarLogeatu.php?nick=jongui&pass=jongu
        boolean emaitza=false;
        try{
            URL url = new URL("http://sgta.webcindario.com/php/comprobarLogeatu.php?nick="+user+"&pass="+pass);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line=br.readLine();
            String[] sarrerak,datuak;
            sarrerak=line.split("&");
            emaitza=sarrerak[0].equals("1");
            if(emaitza){
                datuak=sarrerak[1].split(" ");
                DatosUsuarioXML.user=user;
                topScore=Integer.parseInt(datuak[0]);
                DatosUsuarioXML.avgScore=Float.parseFloat(datuak[1]);
                DatosUsuarioXML.playedGames=Integer.parseInt(datuak[2]);
                DatosUsuarioXML.totallJumps=Integer.parseInt(datuak[3]);
                DatosUsuarioXML.totalMashrooms=Integer.parseInt(datuak[4]);
                DatosUsuarioXML.totalGlues=Integer.parseInt(datuak[5]);
                DatosUsuarioXML.fallDeaths=Integer.parseInt(datuak[6]);
                DatosUsuarioXML.collisionDeaths=Integer.parseInt(datuak[7]);
                DatosUsuarioXML.cameraOutDeaths=Integer.parseInt(datuak[8]);
                DatosUsuarioXML.datuakGordeXML();
            }

        }catch(Exception e){

        }
        return emaitza;
    }

    public static boolean erabiltzaileaErregistratu(String user, String pass){
        //http://sgta.webcindario.com/php/comprobarLogeatu.php?nick=jongui&pass=jongui
        boolean emaitza=false;
        try{
            URL url = new URL("http://sgta.webcindario.com/php/registrarUsuario.php?nick="+user+"&pass="+pass);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line=br.readLine();
            return line.equals("1");
        }catch(Exception e){

        }
        return emaitza;
    }

    public static void datuakPasaZerbitzariari(){

        try{

            String urlS="http://sgta.webcindario.com/php/actualizarTodosLosDatos.php?" +
                    "nick="+user+"&TopScore="+topScore+"&AvgScore="+avgScore+"&PlayedGames="+playedGames+"&TotalJumps="+totallJumps+
                    "&TotalMashrooms="+totalMashrooms+"&TotalGlues="+totalGlues+"&FallDeaths="+fallDeaths+"&CollisionDeaths="+collisionDeaths+"&CameraOutDeaths="+cameraOutDeaths;
            System.out.println(urlS);
            URL url = new URL(urlS);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            urlConnection.getInputStream();

        }catch(Exception e){

        }

    }

}
