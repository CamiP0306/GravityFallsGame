package com.gravityfalls.game.pantallas;

	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.Screen;
	import com.badlogic.gdx.audio.Music;
	import com.badlogic.gdx.graphics.Color;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import com.gravityfalls.game.elementos.Imagen;
	import com.gravityfalls.game.elementos.Texto;
    import com.gravityfalls.game.io.EntradasSalidas;
    import com.gravityfalls.game.GravityFalls;
	import com.gravityfalls.game.utiles.Config;
	import com.gravityfalls.game.utiles.Recursos;
	import com.gravityfalls.game.utiles.Render;

	public class PantallaControles implements Screen {

	    Imagen fondo, iconoMusica;
	    SpriteBatch b;
	    Texto opciones[] = new Texto[2];
	    String textos[] = {"ATRAS", "SIGUIENTE"};
	    int opcion = 1;
	    public float tiempo = 0;
	    EntradasSalidas entradas;
	    Music musica;
	    private GravityFalls game;

	    public PantallaControles(GravityFalls game) {
	        this.game = game;
	    }

	    @Override
	    public void show() {

	        if (!Render.manager.isLoaded(Recursos.MUSICA)) {
	            Render.manager.load(Recursos.MUSICA, Music.class);
	            Render.manager.finishLoading(); // espera hasta que se termine la carga
	        }

	        b = Render.batch;
	        fondo = new Imagen(Recursos.REGLAS);
	        
	        entradas = new EntradasSalidas(this);
	        Gdx.input.setInputProcessor(entradas);

	    	iconoMusica = new Imagen(Recursos.MUSICAPRENDIDA);
			iconoMusica.setSize(60, 60);
			iconoMusica.setPosition(980, 500);

	        musica = Render.manager.get(Recursos.MUSICA, Music.class);
	        musica.setLooping(true);
	        musica.play();

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
	                ((Config.ALTO/60)+(opciones[i].getAlto()* opciones.length))  // Centra verticalmente
	            );
	        }
	    }


	    @Override
	    public void render(float delta) {
	        tiempo += delta;

	        // Detectar si el mouse está sobre la opción
	        for (int i = 0; i < opciones.length; i++) {
	            if (entradas.getMouseX() >= opciones[i].getX() &&
	                entradas.getMouseX() <= opciones[i].getX() + opciones[i].getAncho() &&
	                Config.ALTO - entradas.getMouseY() >= opciones[i].getY() - opciones[i].getAlto() &&
	                Config.ALTO - entradas.getMouseY() <= opciones[i].getY()) {
	                opcion = i + 1;
	            }
	        }

	        // Cambiar el color de la opción seleccionada
	        for (int i = 0; i < opciones.length; i++) {
	            if (i == opcion - 1) {
	                opciones[i].setColor(Color.VIOLET);
	            } else {
	                opciones[i].setColor(Color.WHITE);
	            }
	        }

	        Render.limpiarPantalla(0, 0, 0);
	        b.begin();
	        fondo.dibujar();
	        for (Texto opcion : opciones) {
	            opcion.dibujar();
	        }
	        iconoMusica.dibujar();
	        b.end();

	        if (entradas.isAbajo() && tiempo > 0.09f) {
	            opcion++;
	            if (opcion > 2) {
	                opcion = 1;
	            }
	            tiempo = 0;
	        } else if (entradas.isArriba() && tiempo > 0.09f) {
	            opcion--;
	            if (opcion < 1) {
	                opcion = 2;
	            }
	            tiempo = 0;
	        }

	        if (entradas.isEnter() || entradas.isClick()) {
	            if (opcion == 1) {
	                game.setScreen(new PantallaMenu(game));
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
	    public void dispose() {
	        fondo.dispose();
	        iconoMusica.dispose();
	        for (Texto opcion : opciones) {
	            opcion.dispose();
	        }
	        musica.dispose();
	    }

}
