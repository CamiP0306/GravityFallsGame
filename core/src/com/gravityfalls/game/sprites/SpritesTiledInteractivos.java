package com.gravityfalls.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gravityfalls.game.GravityFalls;

public abstract class  SpritesTiledInteractivos {
    
	protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle   bounds;
    protected Body body;
    
    
    public SpritesTiledInteractivos(World world, TiledMap map, Rectangle bounds) {
    	
    	this.world = world;
    	this.map = map;
    	this.bounds = bounds;
    	
    	 BodyDef bDef = new BodyDef();
    	 FixtureDef fDef = new FixtureDef();
    	 PolygonShape shape = new PolygonShape();
    	 
    	 bDef.type = BodyDef.BodyType.StaticBody;
         bDef.position.set((bounds.getX() + bounds.getWidth() / 2) / GravityFalls.PPM, (bounds.getY() + bounds.getHeight() / 2) / GravityFalls.PPM);

         body = world.createBody(bDef);

         shape.setAsBox(bounds.getWidth() / 2 / GravityFalls.PPM, bounds.getHeight() / 2 / GravityFalls.PPM);
         fDef.shape = shape;
         body.createFixture(fDef);
    }
    
    
    
}
