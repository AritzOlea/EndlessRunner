package com.endlessrunner.entidades;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.endlessrunner.entidades.actorAventurero.ActorAventurero;
import com.endlessrunner.entidades.obstaculos.EntidadMonte;
import com.endlessrunner.entidades.obstaculos.EntidadSuelo;


/**
 * Created by Jongui on 27/03/2018.
 */

public class FactoriaDeEntidades {

    private AssetManager manager;


    public FactoriaDeEntidades(AssetManager manager) {
        this.manager = manager;
    }


    public ActorAventurero crearJugador(World world, Vector2 posicion) {
        Texture texturaJugador = manager.get("personajes/aventurero/Idle__000.png");

        Texture[] corriendo = new  Texture[10];
        corriendo[0] = manager.get("personajes/aventurero/Run__000.png",Texture.class);
        corriendo[1] = manager.get("personajes/aventurero/Run__001.png",Texture.class);
        corriendo[2] = manager.get("personajes/aventurero/Run__002.png",Texture.class);
        corriendo[3] = manager.get("personajes/aventurero/Run__003.png",Texture.class);
        corriendo[4] = manager.get("personajes/aventurero/Run__004.png",Texture.class);
        corriendo[5] = manager.get("personajes/aventurero/Run__005.png",Texture.class);
        corriendo[6] = manager.get("personajes/aventurero/Run__006.png",Texture.class);
        corriendo[7] = manager.get("personajes/aventurero/Run__007.png",Texture.class);
        corriendo[8] = manager.get("personajes/aventurero/Run__008.png",Texture.class);
        corriendo[9] = manager.get("personajes/aventurero/Run__009.png",Texture.class);

        Texture[] saltando = new  Texture[10];
        saltando[0] = manager.get("personajes/aventurero/Jump__000.png",Texture.class);
        saltando[1] = manager.get("personajes/aventurero/Jump__001.png",Texture.class);
        saltando[2] = manager.get("personajes/aventurero/Jump__002.png",Texture.class);
        saltando[3] = manager.get("personajes/aventurero/Jump__003.png",Texture.class);
        saltando[4] = manager.get("personajes/aventurero/Jump__004.png",Texture.class);
        saltando[5] = manager.get("personajes/aventurero/Jump__005.png",Texture.class);
        saltando[6] = manager.get("personajes/aventurero/Jump__006.png",Texture.class);
        saltando[7] = manager.get("personajes/aventurero/Jump__007.png",Texture.class);
        saltando[8] = manager.get("personajes/aventurero/Jump__008.png",Texture.class);
        saltando[9] = manager.get("personajes/aventurero/Jump__009.png",Texture.class);

        Texture[] muerto = new Texture[10];
        muerto[0] = manager.get("personajes/aventurero/Dead__000.png",Texture.class);
        muerto[1] = manager.get("personajes/aventurero/Dead__001.png",Texture.class);
        muerto[2] = manager.get("personajes/aventurero/Dead__002.png",Texture.class);
        muerto[3] = manager.get("personajes/aventurero/Dead__003.png",Texture.class);
        muerto[4] = manager.get("personajes/aventurero/Dead__004.png",Texture.class);
        muerto[5] = manager.get("personajes/aventurero/Dead__005.png",Texture.class);
        muerto[6] = manager.get("personajes/aventurero/Dead__006.png",Texture.class);
        muerto[7] = manager.get("personajes/aventurero/Dead__007.png",Texture.class);
        muerto[8] = manager.get("personajes/aventurero/Dead__008.png",Texture.class);
        muerto[9] = manager.get("personajes/aventurero/Dead__009.png",Texture.class);


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


}
