package com.gravityfalls.game.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gravityfalls.game.GravityFalls;

public class Render {
	public static SpriteBatch batch; 
	public static AssetManager manager;
	public static GravityFalls app;
		
	public static void limpiarPantalla(float r, float g, float b ) {
		Gdx.gl.glClearColor(r, g, b, 1);//limpia la imagen y define el color(rojo, verde, azul, transparencia)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //limpia el buffer de color
    }

    public static void initialize() {
        batch = new SpriteBatch();
    }
    
    public static void admin() {
    	manager = new AssetManager();
    	manager.load(Recursos.MUSICA, Music.class);
    	manager.finishLoading();
    }

    public static void dispose() {
        batch.dispose();
    }
}