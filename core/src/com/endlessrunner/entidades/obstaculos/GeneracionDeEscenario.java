package com.endlessrunner.entidades.obstaculos;

import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.physics.box2d.World;
import com.endlessrunner.entidades.FactoriaDeEntidades;
import com.endlessrunner.entidades.objetos.EntidadSetaPuntos;
import com.endlessrunner.entidades.objetos.EntidadSetaSinSalto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Jongui on 29/03/2018.
 */

public class GeneracionDeEscenario {


    static List<Integer> espaciosOcupados = new ArrayList<Integer>();
    //static Set<Integer> espaciosOcupados = new HashSet<Integer>();

    public static void GenerarSuelo(List<EntidadSuelo> listaDeSuelo, World world, FactoriaDeEntidades factory, int maximoDeLargo){

        espaciosOcupados.clear();
        //for(int i=0;i<15;i++)espaciosOcupados.add(i);

        int i=0,anchuraDelSuelo=0,maximoDeAnchoBloque=0,minimoDeAnchoBloque=0,anchuraDelSalto,iLag;
        while(i<maximoDeLargo){

            espaciosOcupados.add(i);espaciosOcupados.add(i+1);

            if(i<100){maximoDeAnchoBloque=50;minimoDeAnchoBloque=30;}
            else if(i<200){maximoDeAnchoBloque=30;minimoDeAnchoBloque=15;}
            else if(i<300){maximoDeAnchoBloque=49;minimoDeAnchoBloque=29;}
            else if(i<400){maximoDeAnchoBloque=48;minimoDeAnchoBloque=28;}
            else if(i<500){maximoDeAnchoBloque=47;minimoDeAnchoBloque=27;}
            else if(i<600){maximoDeAnchoBloque=30;minimoDeAnchoBloque=15;}
            else if(i<700){maximoDeAnchoBloque=46;minimoDeAnchoBloque=26;}
            else if(i<800){maximoDeAnchoBloque=45;minimoDeAnchoBloque=25;}
            else if(i<900){maximoDeAnchoBloque=30;minimoDeAnchoBloque=15;}
            else if(i<1000){maximoDeAnchoBloque=44;minimoDeAnchoBloque=24;}
            else if(i<1100){maximoDeAnchoBloque=43;minimoDeAnchoBloque=23;}
            else if(i<1200){maximoDeAnchoBloque=30;minimoDeAnchoBloque=15;}
            else if(i<1300){maximoDeAnchoBloque=30;minimoDeAnchoBloque=15;}
            else if(i<1400){maximoDeAnchoBloque=30;minimoDeAnchoBloque=15;}
            else if(i<1500){maximoDeAnchoBloque=42;minimoDeAnchoBloque=22;}
            else if(i<1600){maximoDeAnchoBloque=41;minimoDeAnchoBloque=21;}
            else if(i<1700){maximoDeAnchoBloque=40;minimoDeAnchoBloque=20;}
            else if(i<1800){maximoDeAnchoBloque=40;minimoDeAnchoBloque=20;}
            else if(i<1900){maximoDeAnchoBloque=40;minimoDeAnchoBloque=20;}
            else{maximoDeAnchoBloque=40;minimoDeAnchoBloque=20;}


            anchuraDelSuelo=(int) Math.floor(Math.random()*(maximoDeAnchoBloque-minimoDeAnchoBloque+1)+maximoDeAnchoBloque);
            anchuraDelSalto=(int) Math.floor(Math.random()*(3-1+1)+3);

            listaDeSuelo.add(factory.crearSuelo(world, i, anchuraDelSuelo, 1));

            iLag=i+anchuraDelSuelo;
            i=i+anchuraDelSuelo+anchuraDelSalto;

            for(int j=iLag-4;j<i+4;j++)espaciosOcupados.add(j);

        }


        //Iterator it = espaciosOcupados.iterator();
        //while(it.hasNext())System.out.println(it.next());

    }



    public static void GenerarRocas(List<EntidadMonte> listaDeRocas, World world, FactoriaDeEntidades factory){


        int i=15,siguienteRoca;

        while(i<2000){

            siguienteRoca= (int) Math.floor(Math.random()*(15-5+1)+15);

            i=i+siguienteRoca;

            if( !espaciosOcupados.contains(i)){
                espaciosOcupados.add(i-1);espaciosOcupados.add(i-2);espaciosOcupados.add(i-3);
                espaciosOcupados.add(i);
                espaciosOcupados.add(i+1);espaciosOcupados.add(i+2);espaciosOcupados.add(i+3);

                listaDeRocas.add(factory.crearMonte(world, i, 1));
            }

        }


    }

    public static void GenerarSetasPositivas(List<EntidadSetaPuntos> listaSetasPositivas, World world, FactoriaDeEntidades factory){


        int i=5,siguienteSeta;

        while(i<2000){

            siguienteSeta= (int) Math.floor(Math.random()*(25-15+1)+15);

            i=i+siguienteSeta;

            if( !espaciosOcupados.contains(i)){
                espaciosOcupados.add(i-1);espaciosOcupados.add(i-2);espaciosOcupados.add(i-3);
                espaciosOcupados.add(i);
                espaciosOcupados.add(i+1);espaciosOcupados.add(i+2);espaciosOcupados.add(i+3);

                listaSetasPositivas.add(factory.crearSetaPuntos(world, i, 1));
            }

        }


    }


    public static void GenerarSinSalto(List<EntidadSetaSinSalto> listaSetasPositivas, World world, FactoriaDeEntidades factory){

        int i=105,siguienteSeta;

        while(i<2000){

            siguienteSeta= (int) Math.floor(Math.random()*(100-50+1)+10);

            i=i+siguienteSeta;

            if( !espaciosOcupados.contains(i)){
                espaciosOcupados.add(i-1);espaciosOcupados.add(i-2);espaciosOcupados.add(i-3);
                espaciosOcupados.add(i);
                espaciosOcupados.add(i+1);espaciosOcupados.add(i+2);espaciosOcupados.add(i+3);

                listaSetasPositivas.add(factory.crearSetaSinSalto(world, i, 0.5f));
            }

        }


    }



}
