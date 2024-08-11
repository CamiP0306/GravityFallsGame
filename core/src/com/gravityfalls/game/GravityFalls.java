package com.gravityfalls.game;

import com.badlogic.gdx.Game;
import com.gravityfalls.game.pantallas.PantallaLogo;
import com.gravityfalls.game.utiles.Render;

public class GravityFalls extends Game {
//	public SpriteBatch batch; //permite dibujar - mostrar imgs
    public static final int  V_WIDTH = 600;
    public static final int  V_HEIGHT = 280;
    public static final float PPM = 100;//pixeles por metro
//    public static AssetManager manager;
	
	@Override
	public void create () { //declaracion de objetos y variables
//		batch = new SpriteBatch(); && Render.batch = batch; ==
		Render.initialize();
		Render.app = this;
		this.setScreen(new PantallaLogo()); //cargue una pantalla
//		manager = new AssetManager(); ==
		Render.admin();
	}

	@Override
	public void render () { //ejecuta el programa en paralelo //dibujar
		super.render();
	}

	@Override
	public void dispose () { //borra las cosas que ya no se usan
		Render.batch.dispose();
		Render.manager.dispose();
	}
}