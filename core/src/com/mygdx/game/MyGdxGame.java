package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;

public class MyGdxGame implements ApplicationListener {

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    private OrthographicCamera cam;
    private SpriteBatch batch;

    private Sprite mapSprite;
    private float rotationSpeed;
    TiledMap tilemap;

    @Override
    public void create() {
    	
    	tilemap = new TmxMapLoader().load("temp.tmx");
    	
        rotationSpeed = 0.5f;

        mapSprite = new Sprite(new Texture(Gdx.files.internal("test_map.jpg")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT); 
        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(30, 30 * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        batch = new SpriteBatch();
        
        
    }

    @Override
    public void render() {
        handleInput();
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        mapSprite.draw(batch);
        batch.end();
    }

    private void handleInput() {
    	//zoom in
        //if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        //    cam.zoom += 0.02;
        //}
    	//zoom out
        //if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        //   cam.zoom -= 0.02;
        //}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-2, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(2, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -2, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 2, 0);
        }

        
        //boundary
        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);
        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);

    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height/width;
        cam.update();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        mapSprite.getTexture().dispose();
        batch.dispose();
    }

    @Override
    public void pause() {
    }
}