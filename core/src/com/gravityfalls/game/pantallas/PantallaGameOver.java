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
import com.gravityfalls.game.elementos.Texto;
import com.gravityfalls.game.io.EntradasSalidas;
import com.gravityfalls.game.utiles.Config;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class PantallaGameOver implements Screen {

	private OrthographicCamera camera;
	private Viewport viewport;

	Imagen fondo;
	SpriteBatch b;
	Texto textoGeneral;
	Texto opciones[] = new Texto[2];
	String textos[] = { "SI", "NO" };

	int opcion = 1;
	public float tiempo = 0;
	EntradasSalidas entradasSalidas;
	private GravityFalls game;

	public PantallaGameOver(GravityFalls game) {
		this.game = game;

		// Configurar la cámara
		camera = new OrthographicCamera();
		viewport = new FitViewport(Config.ANCHO, Config.ALTO, camera);
		camera.position.set(Config.ANCHO / 2f, Config.ALTO / 2f, 0);
		camera.update();
	}

	@Override
	public void show() {
		fondo = new Imagen(Recursos.GAMEOVER);
		b = Render.batch; // Utiliza el batch de la instancia de GravityFalls

		entradasSalidas = new EntradasSalidas(this);
		Gdx.input.setInputProcessor(entradasSalidas);

		textoGeneral = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
		textoGeneral.setTexto("JUGAR DE NUEVO?");
		textoGeneral.setPosition((Config.ANCHO / 2) - (textoGeneral.getAncho() / 2),
				((Config.ALTO / 2) + (textoGeneral.getAlto() / 2)) + 10 // Ajusta la posición 
				
		);

		int avance = 35;

		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO / 2) - (opciones[i].getAncho() / 2),
					((Config.ALTO / 2) + (opciones[0].getAlto() / 2)) - ((opciones[i].getAlto() * i) + (i * avance))-50);
		}
	}

	@Override
	public void render(float delta) {
		tiempo += delta;

		// Actualizar la cámara y el viewport
		viewport.apply();
		camera.update();
		b.setProjectionMatrix(camera.combined);

		for (int i = 0; i < opciones.length; i++) {
			if (entradasSalidas.getMouseX() >= opciones[i].getX()
					&& entradasSalidas.getMouseX() <= opciones[i].getX() + opciones[i].getAncho()
					&& entradasSalidas.getMouseY() >= opciones[i].getY() - opciones[i].getAlto()
					&& entradasSalidas.getMouseY() <= opciones[i].getY()) {

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
		textoGeneral.dibujar(); // Dibuja el texto general
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		b.end();

		if (entradasSalidas.isAbajo() && tiempo > 0.09f) {
			opcion++;
			if (opcion > 3) {
				opcion = 1;
			}
			tiempo = 0;
		} else if (entradasSalidas.isArriba() && tiempo > 0.09f) {
			opcion--;
			if (opcion < 1) {
				opcion = 3;
			}
			tiempo = 0;
		} else if (entradasSalidas.isIzquierda() && tiempo > 0.09f) {
			opcion++;
			if (opcion > 2) {
				opcion = 1;
			}
			tiempo = 0;
		} else if (entradasSalidas.isDerecha() && tiempo > 0.09f) {
			opcion--;
			if (opcion < 1) {
				opcion = 2;
			}
			tiempo = 0;
		}

		if (entradasSalidas.isEnter() || entradasSalidas.isClick()) {

			if (opcion == 1) {
				game.setScreen(new PantallaEmpezarBusqueda(game));

			} else if (opcion == 2) {
				game.setScreen(new PantallaMenu(game));
			}
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
	}
}
