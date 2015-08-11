package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
    TiledMap tiledMap;
    TiledMap blockTile;
    TiledMapTileLayer blockLayer;
    TiledMapTileLayer.Cell cell;

    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;
    TiledMapTileLayer mapLayer;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch spriteBatch;
    Pixel[][] map;
    ArrayList<Unit> unitArrayList;

    static final int oneblock = 32, tileX = 30, tileY = 30;
    static final int max_x=29, max_y = 29;
    static int cur_x=0, cur_y=0;
    
    public void testing () {
       mapLayer.setCell(cur_x, cur_y,mapLayer.getCell(0,19));
    }
    
    void cursor(){
       shapeRenderer.begin(ShapeType.Line);
       shapeRenderer.identity();
       shapeRenderer.translate(20,12,2);
       shapeRenderer.rotate(0, 0, 1, 90);
       shapeRenderer.rect(-oneblock/2, -oneblock/2, oneblock, oneblock);
       shapeRenderer.end();
    }
    
    @Override
    public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        spriteBatch = new SpriteBatch();

        map = new Pixel[tileX][];
        for(int i = 0; i < tileX ; i++){
            map[i] = new Pixel[tileY];
            for(int j = 0; j < tileY ; j++){
                map[i][j] = new Pixel();
                if(h - 4 < i) map[i][j].underground = false;
                map[i][j].nutrient = 1;
            }
        }
        map[2][4].digged = true;

        blockTile = new TmxMapLoader().load("block.tmx");
        blockLayer = (TiledMapTileLayer)blockTile.getLayers().get(0);
        cell = blockLayer.getCell(0,0);

        unitArrayList = new ArrayList<Unit>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMap = new TmxMapLoader().load("temp.tmx");
        mapLayer=(TiledMapTileLayer)tiledMap.getLayers().get(0);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        for(int i = 0; i < unitArrayList.size(); i++){
            Unit newunit = unitArrayList.get(i);
            newunit.render();
        }
        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT){
           if(cur_x>0){
              camera.translate(-oneblock,0);
              cur_x--;
           }
        }
        if(keycode == Input.Keys.RIGHT){
           if(cur_x<max_x){
              camera.translate(oneblock,0);
              cur_x++;
           }
        }
        if(keycode == Input.Keys.UP){
           if(cur_y<max_y){
              camera.translate(0,oneblock);
              cur_y++;
           }
        }
        if(keycode == Input.Keys.DOWN){
           if(cur_y>0){
              camera.translate(0,-oneblock);
              cur_y--;
           }
        }
        if(keycode == Input.Keys.SPACE)
           testing();
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int x = cur_x + screenX/oneblock, y = cur_y + (Gdx.graphics.getHeight()-screenY)/oneblock;

        System.out.println(x + " " + y);

        /*
        if(canDig(x,y)){
            System.out.println("digging");

            mapLayer.setCell(x, y, cell);
            if(!map[x][y].digged){
                Moss newMob = new Moss(spriteBatch, map, x, y);
                unitArrayList.add(newMob);
                map[x][y].digged = true;
            }
        }*/

        mapLayer.setCell(x, y, cell);


        if(!map[x][y].digged){
            Moss newMob = new Moss(spriteBatch, map, x, y);
            unitArrayList.add(newMob);
            map[x][y].digged = true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private boolean canDig(int x, int y){
        boolean ret = false;
        if(!map[x][y].underground) return false;
        if(y < max_y) if(map[x][y+1].digged == true) ret = true;
        if(y > 0) if(map[x][y-1].digged == true) ret = true;
        if(x > 0) if(map[x-1][y].digged == true) ret = true;
        if(x < max_x) if(map[x+1][y].digged == true) ret = true;

        return ret;
    }
}