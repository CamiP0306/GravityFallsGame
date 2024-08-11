package com.gravityfalls.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gravityfalls.game.GravityFalls;
import com.gravityfalls.game.elementos.Imagen;
import com.gravityfalls.game.elementos.Musica;
import com.gravityfalls.game.elementos.Texto;
import com.gravityfalls.game.io.EntradasSalidas;
import com.gravityfalls.game.utiles.Config;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class PantallaReglas implements Screen {

	private GravityFalls game;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Imagen fondo, iconoMusica;
	private SpriteBatch b;
	private EntradasSalidas entradas;
	//MUSICA:
	private Musica sonido = new Musica();
	private boolean encendido = true;
	// TEXTO:
	private Texto opciones[] = new Texto[2];
	private String textos[] = { "ATRAS", "SIGUIENTE" };
	private int opcion = 1;

	public PantallaReglas(GravityFalls game) {
		this.game = game;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(Config.ANCHO, Config.ALTO, camera);
		camera.position.set(Config.ANCHO / 2f, Config.ALTO / 2f, 0);
		camera.update();

		b = Render.batch;
		fondo = new Imagen(Recursos.REGLAS);

		entradas = new EntradasSalidas(this);
		Gdx.input.setInputProcessor(entradas);
		//MUSICA (mostrar icono y hacer sonar)
		iconoMusica = new Imagen(Recursos.MUSICAPRENDIDA);
		sonido.mostrarIcono(iconoMusica);
		sonido.sonarMusica(Recursos.MUSICA);
		//TEXTO
		int separacion = 15;
		int totalAncho = 0;
		for (String texto : textos) {
			Texto t = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
			t.setTexto(texto);
			totalAncho += t.getAncho(); // Solo el ancho de los textos
		}
		totalAncho += separacion * opciones.length;
		float xInicial = (Config.ANCHO - totalAncho) / 2;
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition(xInicial + i * (opciones[i].getAncho() + separacion * opciones.length + 35), // Posición X con separación
					((Config.ALTO / 60) + (opciones[i].getAlto() * opciones.length)) // Centra verticalmente
			);
		}
	}

	@Override
	public void render(float delta) {
		camera.update();
		b.setProjectionMatrix(camera.combined);

		Render.limpiarPantalla(0, 0, 0);
		
		//MUSICA
		if (entradas.getMouseX() >= iconoMusica.getX()
				&& entradas.getMouseX() <= iconoMusica.getX() + iconoMusica.getAncho()
				&& entradas.getMouseY() >= iconoMusica.getY() - iconoMusica.getAlto()
				&& entradas.getMouseY() <= iconoMusica.getY()) {
			opcion = 4;
		}
		//TEXTO
		for (int i = 0; i < opciones.length; i++) {
			if (entradas.getMouseX() >= opciones[i].getX()
					&& entradas.getMouseX() <= opciones[i].getX() + opciones[i].getAncho()
					&& entradas.getMouseY() >= opciones[i].getY() - opciones[i].getAlto()
					&& entradas.getMouseY() <= opciones[i].getY()) {

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
			iconoMusica.dibujar();
			for (int i = 0; i < opciones.length; i++) {
				opciones[i].dibujar();
			}
		b.end();

		if (entradas.isClick() && opcion != -1) {
			switch (opcion) {
			case 1:
				Render.app.setScreen(new PantallaMenu(game));
				break;
			case 2:
				Render.app.setScreen(new PantallaComoJugar(game));
				break;
			case 4:
				iconoMusica = sonido.pausarMusica(encendido, iconoMusica);
	            encendido = !encendido;
			}
			entradas.resetInputs();
		}
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
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
		fondo.dispose();
		iconoMusica.dispose();
		for (Texto opcion : opciones) {
			opcion.dispose();
		}
		sonido.dispose();
	}
}