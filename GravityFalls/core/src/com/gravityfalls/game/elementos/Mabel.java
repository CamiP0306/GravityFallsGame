package com.gravityfalls.game.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gravityfalls.game.utiles.Render;

public class Mabel {
	
	public Texture textura;
	public Sprite spr;
	
	public Mabel(float x, float y, float ancho, float alto) {
		textura = new Texture("mabel.png"); //cargar img y mostrarla por pantalla
		spr = new Sprite(textura); //es un objeto que permite cargar una textura a ese objeto
		spr.setPosition(x, y); //posicion de la imagen
		spr.setSize(ancho, alto);
	}

	public void dibujar(SpriteBatch batch) {
		spr.draw(Render.batch);
	}

	public void setX(float x) {
		spr.setX(x);	
	}
	
	public void setY(float y) {
		spr.setY(y);	
	}
}
