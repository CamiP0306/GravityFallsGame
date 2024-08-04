package com.gravityfalls.game.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.gravityfalls.game.utiles.Render;

public class Texto {
	BitmapFont fuente; //textura a la fuente
	GlyphLayout layout; //obtener el ancho y el alto
    private float x = 0, y = 0;
    private String texto = "";
    private Color color = Color.WHITE; // Default color
    
    public Texto(String rutaFuente, int dimension, Color color, boolean sombra) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente)); //sirve para cargar la fuente
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();//sirve para poner los parametros
        parameter.size = dimension;
        parameter.color = color;
        if (sombra) {
            parameter.shadowColor = Color.BLACK;
            parameter.shadowOffsetX = 1;
            parameter.shadowOffsetY = 1;
        }
        fuente = generator.generateFont(parameter);
        layout = new GlyphLayout();
    }
    
    public void dibujar() {
        fuente.setColor(color); // aplicar el color luego de dibujarlo
        fuente.draw(Render.batch, texto, x, y);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
		layout.setText(fuente, texto);
	}

    public float getAncho() {
        return layout.width;
    }

    public float getAlto() {
        return layout.height;
    }

    public void setColor(Color color) {
        this.color = color; //  actualiza el color
    }
}