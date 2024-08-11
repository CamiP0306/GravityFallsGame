package com.gravityfalls.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gravityfalls.game.GravityFalls;
import com.gravityfalls.game.elementos.Imagen;
import com.gravityfalls.game.elementos.Texto;
import com.gravityfalls.game.io.EntradasSalidas;
import com.gravityfalls.game.utiles.Config;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class PantallaElegirPersonaje implements Screen  {

	    Imagen fondo;
	    SpriteBatch b;
	    Texto opciones[] = new Texto[2];
	    String textos[] = {"DIPPER", "MABEL"};

	    int opcion = 1;
	    public float tiempo = 0;
	    EntradasSalidas entradas;
	    private GravityFalls game;

	    public PantallaElegirPersonaje(GravityFalls game) {
	        this.game = game;
	    }

	    @Override
	    public void show() {
	        fondo = new Imagen(Recursos.PERSONAJES);
	        b = Render.batch;  // Utiliza el batch de la instancia de GravityFalls

	        entradas = new EntradasSalidas(this);
	        Gdx.input.setInputProcessor(entradas);

	        int separacion =  15;
	        int totalAncho = 0;

	     // Calcula el ancho total (ambos textos)
	        for (String texto : textos) {
	            Texto t = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
	            t.setTexto(texto);
	            totalAncho += t.getAncho(); // Solo el ancho de los textos
	        }
	        totalAncho +=separacion*opciones.length;
	        
	        float xInicial = (Config.ANCHO - totalAncho) / 2;
	     
	        
	        for (int i = 0; i < opciones.length; i++) {
	            opciones[i] = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
	            opciones[i].setTexto(textos[i]);
				opciones[i].setPosition(
						 xInicial + i * (opciones[i].getAncho() + separacion*opciones.length+35),  // Posición X con separación
	                ((Config.ALTO/2)+(opciones[i].getAlto()* opciones.length))  // Centra verticalmente
	            );
	        }
	    }

	    @Override
	    public void render(float delta) {
	        tiempo += delta;

	        for (int i = 0; i < opciones.length; i++) {
	            if (entradas.getMouseX() >= opciones[i].getX() &&
	                entradas.getMouseX() <= opciones[i].getX() + opciones[i].getAncho() &&
	                entradas.getMouseY() >= opciones[i].getY() - opciones[i].getAlto() &&
	                entradas.getMouseY() <= opciones[i].getY()) {
	                
	                opcion = i + 1;
	            }
	        }

	        for (int i = 0; i < opciones.length; i++) {
	            if (i == opcion - 1) {
	                opciones[i].setColor(Color.VIOLET);
	            } else {
	                opciones[i].setColor(Color.WHITE);
	            }
	        }

	        b.begin();
	        fondo.dibujar();
	        for (int i = 0; i < opciones.length; i++) {
	            opciones[i].dibujar();
	        }
	        b.end();

	        if (entradas.isIzquierda() && tiempo > 0.09f) {
	            opcion++;
	            if (opcion > 2) {
	                opcion = 1;
	            }
	            tiempo = 0;
	        } else if (entradas.isDerecha() && tiempo > 0.09f) {
	            opcion--;
	            if (opcion < 1) {
	                opcion = 2;
	            }
	            tiempo = 0;
	        }

	        if (entradas.isEnter() || entradas.isClick()) {
	            if (opcion == 1||opcion == 2) {
	            	game.setScreen(new PantallaEmpezarBusqueda(game));
	            }
	        }
	    }

	    @Override
	    public void resize(int width, int height) {}

	    @Override
	    public void pause() {}

	    @Override
	    public void resume() {}

	    @Override
	    public void hide() {}

	    @Override
	    public void dispose() {}

}
