package com.gravityfalls.game.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.gravityfalls.game.pantallas.PantallaLogo;
import com.gravityfalls.game.utiles.Config;
//import com.gravityfalls.game.pantallas.PantallaMenu;

public class EntradasSalidas implements InputProcessor{
    
	private PantallaLogo pantallaLogo;
//    private PantallaMenu pantallaMenu;
    
    private boolean
    abajo = false, 
    arriba = false, 
    enter = false ,
    click = false,
    izquierda = false,
    derecha = false;
    
    private int mouseX = 0, mouseY = 0;

    // Constructor para PantallaMenu
//    public EntradasSalidas(PantallaMenu pantallaMenu) {
//        this.pantallaMenu = pantallaMenu;
//    }
//
//    // Constructor para PantallaLogo
//    public EntradasSalidas(PantallaLogo pantallaLogo) {
//        this.pantallaLogo = pantallaLogo;
//    }

	@Override
	public boolean keyDown(int keycode) { //detecta cuando se presiona una tecla
		System.out.println("Key down: " + keycode);
        if (keycode == Keys.DOWN) {
            abajo = true;
        } else if (keycode == Keys.UP) {
            arriba = true;
        } else if (keycode == Keys.LEFT) {
            izquierda = true;
        } else if (keycode == Keys.RIGHT) {
            derecha = true;
        } else if (keycode == Keys.ENTER) {
            enter = true;
            if (pantallaLogo != null) {
                pantallaLogo.terminarPantalla();
            }
        }
        return false;
	}

	@Override
	public boolean keyUp(int keycode) { //detecta cuando se suelta la tecla
		System.out.println("Key up: " + keycode);
        if (keycode == Keys.DOWN) {
            abajo = false;
        } else if (keycode == Keys.UP) {
            arriba = false;
        } else if (keycode == Keys.LEFT) {
            izquierda = false;
        } else if (keycode == Keys.RIGHT) {
            derecha = false;
        } else if (keycode == Keys.ENTER) {
            enter = false;
        }
        return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {//detecta cuando se clickea
		click= true;
    	return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {//detecta lo contrario del de arriba
		click =false;
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// Ajustar la coordenada Y si es necesario
        mouseX = screenX;
        mouseY = Config.ALTO - screenY; // Gdx.graphics.getHeight() en lugar de Config.ALTO
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	public boolean isAbajo() {
        return abajo;
    }

    public boolean isArriba() {
        return arriba;
    }

    public boolean isEnter() {
        return enter;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

	public boolean isClick() {
		return click;
	}

	public boolean isIzquierda() {
		return  izquierda;
	}

	public boolean isDerecha() {
		return derecha;
	}
}