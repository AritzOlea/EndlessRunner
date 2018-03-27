package com.endlessrunner.entidades;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.endlessrunner.entidades.actorAventurero.ActorAventurero;
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
        return new ActorAventurero(world, texturaJugador, posicion);
    }


    public EntidadSuelo crearSuelo(World world, float x, float width, float y) {
        Texture floorTexture = manager.get("paisajes/dia/png/Tiles/2.png");
        Texture overfloorTexture = manager.get("paisajes/dia/png/Tiles/2.png");
        return new EntidadSuelo(world, floorTexture, overfloorTexture, x, width, y);
    }


}
