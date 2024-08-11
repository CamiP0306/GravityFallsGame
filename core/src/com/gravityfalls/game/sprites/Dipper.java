package com.gravityfalls.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gravityfalls.game.GravityFalls;
import com.gravityfalls.game.pantallas.PantallaEmpezarBusqueda;

public class Dipper extends Sprite {
	public World world;
	//private TextureRegion dipperStand;
	public Body b2Body;

	public Dipper(World world, PantallaEmpezarBusqueda  screen) {
	   // super(screen.getAtlas().findRegion("little_mario"));
		this.world = world;
	    defineDipper();
	   // dipperStand = new TextureRegion(getTexture(), 503, 58, 500, 112); // Usa las coordenadas correctas del atlas

	    setBounds(0, 0, 500 / GravityFalls.PPM, 112 / GravityFalls.PPM); // Ajusta según el tamaño de la región
	    //setRegion(dipperStand);
	}
    
	public void update(float dt) {
		setPosition(b2Body.getPosition().x-getWidth()/2,b2Body.getPosition().x-getHeight()/2) ;
	}
	
	public void defineDipper() {
	    BodyDef bDef = new BodyDef();
	    bDef.position.set(32 / GravityFalls.PPM,  100/ GravityFalls.PPM); // Verifica que estas coordenadas están dentro del mapa
	    bDef.type = BodyDef.BodyType.DynamicBody;
	    b2Body = world.createBody(bDef);
	    
	    FixtureDef fDef = new FixtureDef();
	    CircleShape shape = new CircleShape();
	    shape.setRadius(6
	    		/ GravityFalls.PPM);
	    
	    fDef.shape = shape;
	    b2Body.createFixture(fDef);
	}

}
