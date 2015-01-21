package com.colouredtulips;

import com.badlogic.gdx.Game;

import pgl.screen.Classroom;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class Main extends Game {
    public static Main main;
    public static ActionResolver actionResolver;

    public Main(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    @Override
    public void create () {
        main=this;
        setScreen(new Classroom());
    }
}
