package com.gravityfalls.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gravityfalls.game.GravityFalls;
import com.gravityfalls.game.accesoriosmapa.B2BodyTerreno;
import com.gravityfalls.game.escenas.Hud;
import com.gravityfalls.game.sprites.Dipper;
import com.gravityfalls.game.utiles.Recursos;
import com.gravityfalls.game.utiles.Render;


public class PantallaEmpezarBusqueda implements Screen {

    private GravityFalls game;
   // private TextureAtlas atlas;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private World world;
    private Box2DDebugRenderer b2Render;
    
    private Dipper jugador;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PantallaEmpezarBusqueda(GravityFalls game) {
    	
    	//atlas = new TextureAtlas("Mario_and_Enemies.pack");
    			
        this.game = game;

        gameCam = new OrthographicCamera();
        gameCam.zoom = 1.0f;
        gamePort = new FitViewport(GravityFalls.V_WIDTH / GravityFalls.PPM, GravityFalls.V_HEIGHT / GravityFalls.PPM, gameCam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load(Recursos.MAPA);
        
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GravityFalls.PPM);
        
        if (map == null) {
            throw new RuntimeException("Mapa no cargado.");
        }
        if (map.getLayers().getCount() == 0) {
            throw new RuntimeException("No hay capas en el mapa.");
        }


        // Configurar la cámara para que comience en el centro del mapa
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2Render = new Box2DDebugRenderer();
        
        jugador = new Dipper(world, this);
        
        new B2BodyTerreno(world,map);
  
    }
    
//    public TextureAtlas getAtlas() {
//    	return atlas;
//    	
//    }
    
    public void handleInput(float dt) {

      if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            jugador.b2Body.applyLinearImpulse(new Vector2(0, 4f), jugador.b2Body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && jugador.b2Body.getLinearVelocity().x <= 2) {
            jugador.b2Body.applyLinearImpulse(new Vector2(0.1f, 0), jugador.b2Body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && jugador.b2Body.getLinearVelocity().x >= -2) {
            jugador.b2Body.applyLinearImpulse(new Vector2(-0.1f, 0), jugador.b2Body.getWorldCenter(), true);
        }
    }


    public void update(float dt) {
        handleInput(dt);

       jugador.update(dt);
        world.step(1 / 60f, 6, 2);
        
        gameCam.position.x = jugador.b2Body.getPosition().x;

        // Actualizar la cámara
        gameCam.update();

        // Configurar la vista del renderer
        renderer.setView(gameCam);
    }



    @Override
    public void show() {
    }

    public void render(float delta) {
        update(delta);

        Render.limpiarPantalla(0, 0, 0);

        // Renderizar el mapa
        renderer.setView(gameCam);
        renderer.render();

        // Dibujar la información de depuración de Box2D
        b2Render.render(world, gameCam.combined);

        // Configurar la matriz de proyección del HUD y dibujar el HUD
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    //reproduccion
    @Override
    public void dispose() {
    	map.dispose();
    	Render.dispose();
    	world.dispose();
    	b2Render.dispose();
    	hud.dispose();
  
    }
}