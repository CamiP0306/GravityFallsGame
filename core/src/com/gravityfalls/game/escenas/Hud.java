package com.gravityfalls.game.escenas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gravityfalls.game.GravityFalls;

public class Hud implements Disposable {

	public Stage stage;
	private Viewport viewport;
	private int worldTimer;
	private float timeCount;
	private int libros;
	
	
	Label tiempoRestanteLabel;
	Label librosLabel;
	Label tiempoLabel;
	Label levelLabel;
	Label mundoLabel;
	Label vidaslabel;
	
	public Hud(SpriteBatch sb) { 
		worldTimer = 300;
		timeCount = 0;
		libros = 0;
		
		// camara que mantiene fija la informacion relevante y promorcionar la interfaz de usuario (informacion relevante)
		viewport = new FitViewport (GravityFalls.V_WIDTH, GravityFalls.V_HEIGHT, new OrthographicCamera());
		stage = new Stage (viewport, sb);
		
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		
		tiempoRestanteLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		librosLabel = new Label(String.format("%03d",libros), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		tiempoLabel = new Label("TIEMPO", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		levelLabel = new Label("1 - 1", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		mundoLabel = new Label("MUNDO", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		vidaslabel = new Label("VIDAS", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		
		table.add(vidaslabel).expandX().padTop(10);
		table.add(mundoLabel).expandX().padTop(10);
		table.add(tiempoLabel).expandX().padTop(10);
		table.row();
		table.add(librosLabel).expandX().padTop(10);
		table.add(levelLabel).expandX().padTop(10);
		table.add(tiempoRestanteLabel).expandX().padTop(10);;
    
	    stage.addActor(table);
	}

	public void dispose() {
		stage.dispose();
	}
}