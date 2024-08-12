package com.gravityfalls.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gravityfalls.game.pantallas.PantallaLogo;
import com.gravityfalls.game.utiles.Render;

public class GravityFalls extends Game {
	public SpriteBatch batch; //permite dibujar - mostrar imgs
    public static final int  V_WIDTH = 600;
    public static final int  V_HEIGHT = 280;
    public static final float PPM = 100;//pixeles por metro
    
//    private Musica musica = new Musica();
	
	@Override
	public void create () { //declaracion de objetos y variables
		batch = new SpriteBatch();
        Render.batch = batch; 
        Render.admin();
        Render.app = this;
        this.setScreen(new PantallaLogo(this));
//        musica.generarMusica();
	}

	@Override
	public void render () { //ejecuta el programa en paralelo //dibujar
		super.render();
	}

	@Override
	public void dispose () { //borra las cosas que ya no se usan
		Render.batch.dispose();
		Render.manager.dispose();
//		musica.disposeManager();
	}
}