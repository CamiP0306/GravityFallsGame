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

public class PantallaMenu implements Screen {

	private Imagen fondo;
    private SpriteBatch b;
    private EntradasSalidas entradas;
    //TEXTO
    private Texto opciones[] = new Texto[3];
    private String textos[] = {"EMPEZAR BUSQUEDA", "COMO JUGAR", "CERRAR"};
    private int opcion = 1;
    public float tiempo = 0;
    private GravityFalls game;

    public PantallaMenu(GravityFalls game) {
        this.game = game;
    }

    @Override
    public void show() {
        fondo = new Imagen(Recursos.MENU);
        b = Render.batch;  // Utiliza el batch de la instancia de GravityFalls
        entradas = new EntradasSalidas(this);
        Gdx.input.setInputProcessor(entradas);

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

        if (entradas.isAbajo() && tiempo > 0.09f) {
            opcion++;
            if (opcion > 3) {
                opcion = 1;
            }
            tiempo = 0;
        } else if (entradas.isArriba() && tiempo > 0.09f) {
            opcion--;
            if (opcion < 1) {
                opcion = 3;
            }
            tiempo = 0;
        }

        if (entradas.isEnter() || entradas.isClick()) {
            if (opcion == 1) {
            	Render.app.setScreen(new PantallaElegirPersonaje(game));
            }
            if (opcion == 2) {
            	Render.app.setScreen(new PantallaReglas(game));
            }
            
            if (opcion == 3) {
            	 Gdx.app.exit();
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