package com.gravityfalls.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.gravityfalls.game.GravityFalls;
import com.gravityfalls.game.elementos.Imagen;
import com.gravityfalls.game.elementos.Musica;
import com.gravityfalls.game.elementos.Texto;
import com.gravityfalls.game.io.EntradasSalidas;
import com.gravityfalls.game.utiles.Config;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class PantallaMenu implements Screen {

	private Imagen fondo;
    private EntradasSalidas entradas;
    private GravityFalls game;
    //TEXTO
    private Texto opciones[] = new Texto[3];
    private String textos[] = {"EMPEZAR BUSQUEDA", "COMO JUGAR", "CERRAR"};
    private int opcion = 1;
    public float tiempo = 0;
    //MUSICA
    private Imagen iconoMusica;
    private Musica sonido = new Musica();
	private boolean encendido = true;

    public PantallaMenu(GravityFalls game) {
        this.game = game;
    }

    @Override
    public void show() {
        fondo = new Imagen(Recursos.MENU);
        entradas = new EntradasSalidas(this);
        Gdx.input.setInputProcessor(entradas);
        //Mostrar musica (icono y sonido)
      	iconoMusica = new Imagen(Recursos.MUSICAPRENDIDA);
      	sonido.mostrarIcono(iconoMusica);
      	sonido.sonarMusica(Recursos.MUSICA);
      	//Mostras texto
        int avance = 35;
        for (int i = 0; i < opciones.length; i++) {
            opciones[i] = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
            opciones[i].setTexto(textos[i]);
            opciones[i].setPosition(
                (Config.ANCHO / 2) - (opciones[i].getAncho() / 2),
                ((Config.ALTO / 2) + (opciones[0].getAlto() / 2)) - ((opciones[i].getAlto() * i) + (i * avance))
            );
        }
    }

    @Override
    public void render(float delta) {
    	Render.limpiarPantalla(0, 0, 0);
		// MUSICA
    	opcion = sonido.delimitarMouse(iconoMusica, opcion);
		//TEXTO:
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

        Render.batch.begin();
        	fondo.dibujar();
        	iconoMusica.dibujar();
        	for (int i = 0; i < opciones.length; i++) {
        		opciones[i].dibujar();
        	}
        Render.batch.end();

        if (entradas.isClick()&& opcion != -1) {
        	switch(opcion) {
        	case 1:
        		Render.app.setScreen(new PantallaElegirPersonaje(game));
        		break;
        	case 2:
        		Render.app.setScreen(new PantallaComoJugar(game));
        		break;
        	case 3:
        		Gdx.app.exit();
        		break;
        	case 4:
        		iconoMusica = sonido.pausarMusica(encendido, iconoMusica);
	            encendido = !encendido;
        		break;
        	}
        	entradas.resetInputs();
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
    public void dispose() {
    	fondo.dispose();
		iconoMusica.dispose();
		for (Texto opcion : opciones) {
			opcion.dispose();
		}
		sonido.dispose();
    }
}