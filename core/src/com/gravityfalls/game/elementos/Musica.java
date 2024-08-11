package com.gravityfalls.game.elementos;

import com.badlogic.gdx.audio.Music;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class Musica {
	
	private Music musica;

	public void mostrarIcono(Imagen iconoMusica) {
		iconoMusica.setSize(60, 60);
		iconoMusica.setPosition(1012, 565);
	}
	
	public void sonarMusica(String ruta) {
		musica = Render.manager.get(ruta, Music.class);
		musica.setLooping(true);
		musica.setVolume(0.01f);
		musica.play();
	}
	
	public Imagen pausarMusica(boolean encendido, Imagen iconoMusica) {
		if (encendido) {
			iconoMusica = new Imagen(Recursos.MUSICAPRENDIDA);
			mostrarIcono(iconoMusica);
            musica.play();
        } else {
        	iconoMusica = new Imagen(Recursos.MUSICAAPAGADA);
        	mostrarIcono(iconoMusica);
            musica.pause();
        }
		return iconoMusica;
	}
	
	public void dispose() {
		musica.dispose();
	}
}
