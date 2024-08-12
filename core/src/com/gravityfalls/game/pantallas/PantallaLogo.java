package com.gravityfalls.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.gravityfalls.game.GravityFalls;
import com.gravityfalls.game.elementos.Imagen;
import com.gravityfalls.game.elementos.Texto;
import com.gravityfalls.game.io.EntradasSalidas;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class PantallaLogo implements Screen {
    private Imagen fondo;
    private Texto t;
    private EntradasSalidas entradasSalidas;
    private GravityFalls game;
    
    private boolean efectoPantalla = false, termina = false; //fadeIn
    private float a = 0;
    private float contTiempo = 0, efectoEspera = 0;
    private float efectoTermina = 1.0f, contTiempoTermina = 0;

    public PantallaLogo(GravityFalls game) {
        this.game = game;
    }

    @Override
    public void show() { //cuando se muestra la vetana
        entradasSalidas = new EntradasSalidas(this);
        Gdx.input.setInputProcessor(entradasSalidas);
        
        fondo = new Imagen(Recursos.LOGO);
        fondo.setTransparencia(0);
        
        t = new Texto(Recursos.FUENTE, 25, Color.WHITE, true);
        t.setTexto("( PRESIONA ENTER PARA SALTAR LA INTRO )");
        t.setPosition(315, 40);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla(0, 0, 0);
        Render.batch.begin();
        fondo.dibujar();
        t.dibujar();
        Render.batch.end();
        procesarEfecto();
    }

    private void procesarEfecto() {//fade:  transicion de pantalla de entrada y salida
        if (!efectoPantalla) {//transicion del logo encender
            a += 0.01f;
            if (a > 1) {
                a = 1;
                efectoPantalla = true; //transicion completa
            }
        } else {//transicion del logo -apagar
            contTiempo += 0.05f;
            if (contTiempo > efectoEspera) {
                a -= 0.01f;
                if (a < 0) {
                    a = 0;
                    efectoPantalla = true;
                    termina = true;
                }
            }
        }
        fondo.setTransparencia(a);
        if (termina) {//cambio de pantalla
            contTiempoTermina += 0.1f;
            if (contTiempoTermina > efectoTermina) {
                Render.app.setScreen(new PantallaMenu(game));
            }
        }
    }

    public void terminarPantalla() {
        a = 0;
        efectoPantalla = true;
        termina = true;
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