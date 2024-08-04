package com.gravityfalls.game.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gravityfalls.game.elementos.Imagen;
import com.gravityfalls.game.elementos.Texto;
import com.gravityfalls.game.io.EntradasSalidas;
import com.gravityfalls.game.utiles.Config;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class PantallaReglas implements Screen{
	
	Imagen fondo, iconoMusica, flechaAtras;
	SpriteBatch b;
	Texto t;
	EntradasSalidas entradas = new EntradasSalidas();
	Music musica;
	float a=0;
	
	@Override
	public void show() {
		b = Render.batch;
		fondo = new Imagen(Recursos.FONDOREGLAS);
		fondo.setTransparencia(a);//0 trasnsparente /1 opaco
		
		iconoMusica = new Imagen(Recursos.MUSICAPRENDIDA);
		iconoMusica.setSize(15, 15);
		iconoMusica.setPosition(1080, 20);
		
		musica = Render.manager.get(Recursos.MUSICA, Music.class);
		musica.setLooping(true);
		musica.play();
		
		flechaAtras = new Imagen(Recursos.FLECHAATRAS);
		flechaAtras.setSize(20, 20);
		flechaAtras.setPosition(20, (Config.ALTO/2)-(flechaAtras.getX()/2));
		
		t = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
		t.setTexto("ATRAS");
		t.setPosition((Config.ANCHO/2)-(t.getAncho()/2), 25);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0);
		b.begin();
		fondo.dibujar();
		flechaAtras.dibujar();
		t.dibujar();
		b.end();
		
		//VOLVER AL MENU O A COMO JUGAR
		if((entradas.getMouseX() >= t.getX() && entradas.getMouseX() <= t.getX() + t.getAncho()) && 
		(entradas.getMouseY() >= t.getY() - t.getAlto() && entradas.getMouseY() <= t.getY())) {
			t.setColor(Color.VIOLET);
			if(entradas.isClick()) {
				Render.app.setScreen(new PantallaMenu());
			}
		}else if((entradas.getMouseX() >= flechaAtras.getX() && entradas.getMouseX() <= flechaAtras.getX() + flechaAtras.getAncho()) && 
				(entradas.getMouseY() >= flechaAtras.getY() - flechaAtras.getAlto() && entradas.getMouseY() <= flechaAtras.getY())) {
			t.setColor(Color.VIOLET);
			if(entradas.isClick()) {
				Render.app.setScreen(new PantallaComoJugar());
			}
		}else {
			t.setColor(Color.WHITE);
		}
		
		//MUSICA
		if((entradas.getMouseX() >= iconoMusica.getX() && entradas.getMouseX() <= iconoMusica.getX() + iconoMusica.getAncho()) && 
		(entradas.getMouseY() >= iconoMusica.getY() - t.getAlto() && entradas.getMouseY() <= iconoMusica.getY())) {
			if(entradas.isClick()) {
				iconoMusica = new Imagen(Recursos.MUSICAAPAGADA);
				musica.stop();
			}
		}
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

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}