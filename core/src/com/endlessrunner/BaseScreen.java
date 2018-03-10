package com.endlessrunner;

import com.badlogic.gdx.Screen;

/**
 * Created by aritz on 10/03/2018.
 */

public abstract class BaseScreen implements Screen {

    protected EndlessRunner jokoa;

    public BaseScreen(EndlessRunner jokoa){
        this.jokoa = jokoa;
    }


}
