package com.gravityfalls.game.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gravityfalls.game.utiles.Config;
import com.gravityfalls.game.utiles.Render;

public class Imagen {
	public Texture tex;
    private  Sprite spr;
    private float x=0, y=0;
    private float alto, ancho;

    public Imagen(String ruta) {
        tex = new Texture(ruta);
        spr = new Sprite(tex);
        spr.setSize(Config.ANCHO,Config.ALTO);
        spr.setPosition(x, y);
    }

	public void dibujar() {
        spr.draw(Render.batch);
    }

    public void setTransparencia(float a) {
    	spr.setAlpha(a);
    }
    
    public void setPosition(float x, float y) {
    	spr.setPosition(x, y);
    }
    
    public void setSize(float ancho, float alto) {
    	this.ancho=ancho;
    	this.alto=alto;
    	spr.setSize(this.ancho, this.alto);
    }
    
    public float getAncho() {
    	return ancho;
    }
    
    public float getAlto() {
    	return alto;
    }
    
    public void setX(float x) {
        spr.setX(x);
    }

    public void setY(float y) {
        spr.setY(y);
    }
    
    public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
