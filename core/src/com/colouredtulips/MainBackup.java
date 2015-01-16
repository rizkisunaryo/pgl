package com.colouredtulips;

import com.badlogic.gdx.Game;

import pgl.screen.Classroom;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class MainBackup extends Game {
    public static MainBackup main;
    public static MainBackup getInstance() {
        return main;
    }

	@Override
	public void create () {
		main=this;
        setScreen(new Classroom());
	}
}
