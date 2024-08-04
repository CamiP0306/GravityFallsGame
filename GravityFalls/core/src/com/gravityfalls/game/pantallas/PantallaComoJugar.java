package com.gravityfalls.game.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gravityfalls.game.elementos.Imagen;
import com.gravityfalls.game.elementos.Texto;
import com.gravityfalls.game.io.EntradasSalidas;
import com.gravityfalls.game.utiles.Config;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;

public class PantallaComoJugar implements Screen{
	
	Imagen fondo, flechaAvanzar;
	Imagen flechaDerecha, flechaArriba, flechaIzquierda;
	Imagen mabel;
	SpriteBatch b;
	Texto texto;
	EntradasSalidas entradas = new EntradasSalidas();

//	boolean fadeInTerminado=false, termina=false; 
//	float contTiempo=0, tiempoEspera=5;
//	float contTiempoTermina=0, tiempoTermina=5;
	
	@Override
	public void show() { //cuando se muestra la vetana
		b = Render.batch;
		fondo = new Imagen(Recursos.FONDOCOMOJUGAR);
		flechaAvanzar = new Imagen(Recursos.FLECHASIGUIENTE);
		
		
		texto = new Texto(Recursos.FUENTE, 35, Color.WHITE, true);
		texto.setTexto("ATRAS");
		texto.setPosition((Config.ANCHO/2)-(texto.getAncho()/2), 25);
	}

	@Override
	public void render(float delta) { //renderizar/cargar(diferencia en milisegundos que se ejecuta el programa)
		Render.limpiarPantalla(0, 0, 0);
		b.begin();
		fondo.dibujar(); 
		texto.dibujar();
		b.end();
//		procesarFade();
	}

//	private void procesarFade() {//fade:  transicion de pantalla de entrada y salida
//		if(!fadeInTerminado) { //transicion del logo encender
//			a+=0.1f;
//			if(a>1) {
//				a=1;
//				fadeInTerminado = true;
//			}
//		}else { //transicion del logo apagar
//			contTiempo+=0.1f;
//			if(contTiempo>tiempoEspera) {
//				a-=0.05f;
//				if(a<0) {
//					a=0;
//					termina=true;
//				}
//			}
//		}
//		fondo.setTransparencia(a);
//		
//		if(termina) { //cambio de pantalla
//			contTiempoTermina+=0.1f;
//			if(contTiempoTermina>tiempoTermina) {
//				//System.out.println("Cambio de pantalla");
//				Render.app.setScreen(new PantallaReglas()); //cambiar de pantalla
//			}
//		}
//	}

	@Override
	public void resize(int width, int height) {		
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