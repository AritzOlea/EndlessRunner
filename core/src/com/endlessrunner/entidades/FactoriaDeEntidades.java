package com.endlessrunner.entidades;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.endlessrunner.ayuda.Ajustes;
import com.endlessrunner.entidades.actorAventurero.ActorAventurero;
import com.endlessrunner.entidades.objetos.EntidadSetaPuntos;
import com.endlessrunner.entidades.objetos.EntidadSetaSinSalto;
import com.endlessrunner.entidades.obstaculos.EntidadAgua;
import com.endlessrunner.entidades.obstaculos.EntidadMonte;
import com.endlessrunner.entidades.obstaculos.EntidadSegundosPisos;
import com.endlessrunner.entidades.obstaculos.EntidadSuelo;
import com.endlessrunner.entidades.obstaculos.EntidadSueloDerecha;
import com.endlessrunner.entidades.obstaculos.EntidadSueloIzquierda;


/**
 * Created by Jongui on 27/03/2018.
 */

public class FactoriaDeEntidades {

    private AssetManager manager;


    public FactoriaDeEntidades(AssetManager manager) {
        this.manager = manager;
    }


    public ActorAventurero crearJugador(World world, Vector2 posicion) {
        Texture texturaJugador = manager.get("personajes/"+ Ajustes.Personaje +"/Idle__000.png");

        Texture[] corriendo = new  Texture[10];
        corriendo[0] = manager.get("personajes/"+Ajustes.Personaje +"/Run__000.png",Texture.class);
        corriendo[1] = manager.get("personajes/"+Ajustes.Personaje +"/Run__001.png",Texture.class);
        corriendo[2] = manager.get("personajes/"+Ajustes.Personaje +"/Run__002.png",Texture.class);
        corriendo[3] = manager.get("personajes/"+Ajustes.Personaje +"/Run__003.png",Texture.class);
        corriendo[4] = manager.get("personajes/"+Ajustes.Personaje +"/Run__004.png",Texture.class);
        corriendo[5] = manager.get("personajes/"+Ajustes.Personaje +"/Run__005.png",Texture.class);
        corriendo[6] = manager.get("personajes/"+Ajustes.Personaje +"/Run__006.png",Texture.class);
        corriendo[7] = manager.get("personajes/"+Ajustes.Personaje +"/Run__007.png",Texture.class);
        corriendo[8] = manager.get("personajes/"+Ajustes.Personaje +"/Run__008.png",Texture.class);
        corriendo[9] = manager.get("personajes/"+Ajustes.Personaje +"/Run__009.png",Texture.class);

        Texture[] saltando = new  Texture[10];
        saltando[0] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__000.png",Texture.class);
        saltando[1] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__001.png",Texture.class);
        saltando[2] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__002.png",Texture.class);
        saltando[3] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__003.png",Texture.class);
        saltando[4] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__004.png",Texture.class);
        saltando[5] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__005.png",Texture.class);
        saltando[6] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__006.png",Texture.class);
        saltando[7] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__007.png",Texture.class);
        saltando[8] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__008.png",Texture.class);
        saltando[9] = manager.get("personajes/"+Ajustes.Personaje +"/Jump__009.png",Texture.class);

        Texture[] muerto = new Texture[10];
        muerto[0] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__000.png",Texture.class);
        muerto[1] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__001.png",Texture.class);
        muerto[2] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__002.png",Texture.class);
        muerto[3] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__003.png",Texture.class);
        muerto[4] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__004.png",Texture.class);
        muerto[5] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__005.png",Texture.class);
        muerto[6] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__006.png",Texture.class);
        muerto[7] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__007.png",Texture.class);
        muerto[8] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__008.png",Texture.class);
        muerto[9] = manager.get("personajes/"+Ajustes.Personaje +"/Dead__009.png",Texture.class);


        return new ActorAventurero(world, texturaJugador, posicion,corriendo,saltando, muerto);
    }


    public EntidadSuelo crearSuelo(World world, float x, float width, float y) {
        Texture floorTexture = manager.get("paisajes/dia/png/Tiles/2.png");
        Texture overfloorTexture = manager.get("paisajes/dia/png/Tiles/2.png");
        return new EntidadSuelo(world, floorTexture, overfloorTexture, x, width, y);
    }

    public EntidadMonte crearMonte(World world, float x, float y) {
        Texture spikeTexture = manager.get("paisajes/dia/png/Object/Stone.png");
        return new EntidadMonte(world, spikeTexture, x, y);
    }

    public EntidadSetaPuntos crearSetaPuntos(World world, float x, float y) {
        Texture spikeTexture = manager.get("paisajes/dia/png/Object/Mushroom_2.png");
        return new EntidadSetaPuntos(world, spikeTexture, x, y);
    }

    public EntidadSetaSinSalto crearSetaSinSalto(World world, float x, float y) {
        Texture spikeTexture = manager.get("paisajes/dia/png/Object/charcoCola.png");
        return new EntidadSetaSinSalto(world, spikeTexture, x, y);
    }

    public EntidadSegundosPisos crearSegundosPisos(World world, float x, float width, float y){
        Texture floorTexture = manager.get("paisajes/dia/png/Tiles/14.png");
        return new EntidadSegundosPisos(world, floorTexture, x, width, y);
    }

    public EntidadAgua crearAgua(World world, float x, float width, float y) {
        Texture texture = manager.get("paisajes/dia/png/Tiles/17.png");
        return new EntidadAgua(world, texture, x, width, y);
    }

    public EntidadSueloIzquierda crearSueloIzquierda(World world, float x, float width, float y) {
        Texture texture = manager.get("paisajes/dia/png/Tiles/1.png");
        return new EntidadSueloIzquierda(world, texture, x, width, y);
    }

    public EntidadSueloDerecha crearSueloDerecha(World world, float x, float width, float y) {
        Texture texture = manager.get("paisajes/dia/png/Tiles/3.png");
        return new EntidadSueloDerecha(world, texture, x, width, y);
    }


}
