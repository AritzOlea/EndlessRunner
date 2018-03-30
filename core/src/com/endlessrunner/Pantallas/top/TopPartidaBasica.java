package com.endlessrunner.Pantallas.top;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.endlessrunner.EndlessRunner;

/**
 * Created by Jongui on 30/03/2018.
 */

public class TopPartidaBasica extends com.endlessrunner.Pantallas.partida_basica.BaseScreen {

    private Texture fondoBackground;
    private Stage stage;
    private Skin skin;
    private Image tituloa;
    private TextButton atzera;
    private Table table;
    private Label user1,user2,user3,user4,user5,puntosUser1,puntosUser2,puntosUser3,puntosUser4,puntosUser5;


    public TopPartidaBasica(final EndlessRunner jokoa) {
        super(jokoa);

        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        fondoBackground=jokoa.getManager().get("bg/fondoTops.png");

        atzera = new TextButton("Atzera", skin);

        tituloa = new Image(jokoa.getManager().get("titulo.png", Texture.class));

        atzera.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jokoa.setScreen(jokoa.menuScreen);
            }
        });



        table = new Table();
        table.top();
        table.setFillParent(true);
        //table.row();


        //Puntuazioak lortzeko funtzioari deitu...()
        user1=new Label("User 1",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser1=new Label("000",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(user1).expandX().padTop(10);
        table.add(puntosUser1).expandX().padTop(10);
        table.row();

        user2=new Label("User 2",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser2=new Label("000",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(user2).expandX().padTop(10);
        table.add(puntosUser2).expandX().padTop(10);
        table.row();

        user3=new Label("User 3",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser3=new Label("000",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(user3).expandX().padTop(10);
        table.add(puntosUser3).expandX().padTop(10);
        table.row();

        user4=new Label("User 4",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser4=new Label("000",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(user4).expandX().padTop(10);
        table.add(puntosUser4).expandX().padTop(10);
        table.row();

        user5=new Label("User 5",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        puntosUser5=new Label("000",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(user5).expandX().padTop(10);
        table.add(puntosUser5).expandX().padTop(10);
        table.row();


        table.setPosition(30,200);

        //tituloa.setPosition(320 - tituloa.getWidth() / 2, 320 - tituloa.getHeight());
        tituloa.setPosition( 20, 250 );

        atzera.setSize(160, 50);

        atzera.setPosition(470, 20);


        //stage.addActor(tituloa);
        stage.addActor(atzera);
        stage.addActor(table);
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        jokoa.batch.begin();
        //HAY QUE MULTIPLICAR LA RESOLUCIÓN DE PANTALLA POR ALGÚN NUMERO PARA QUE QUEDE BIEN
        jokoa.batch.draw(fondoBackground,0,0, Gdx.graphics.getWidth() + Gdx.graphics.getWidth() * 1.75f, Gdx.graphics.getHeight() * 1.75f);
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

