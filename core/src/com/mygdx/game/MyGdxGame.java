package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	Constants numinfo;
	Camera camera;
	Tilemap tiles;

    SpriteBatch spriteBatch;
    Pixel[][] map;
    ArrayList<Unit> unitArrayList;

    
    public void testing () {
       tiles.mapLayer.setCell(numinfo.cur_x, numinfo.cur_y,tiles.mapLayer.getCell(0,19));
    }

    @Override
    public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        camera = new Camera(w,h);
        tiles = new Tilemap();
        numinfo = new Constants(tiles.mapLayer);
        
        spriteBatch = new SpriteBatch();

        map = new Pixel[numinfo.tileX][];
        for(int i = 0; i < numinfo.tileX ; i++){
            map[i] = new Pixel[numinfo.tileY];
            for(int j = 0; j < numinfo.tileY ; j++){
                map[i][j] = new Pixel();
                if(h - 4 < i) map[i][j].underground = false;
                map[i][j].nutrient = 1;
            }
        }
        
        
        unitArrayList = new ArrayList<Unit>();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        camera.mainCam.update();
        tiles.tiledMapRenderer.setView(camera.mainCam);
        tiles.tiledMapRenderer.render();
        

        spriteBatch.setProjectionMatrix(camera.mainCam.combined);
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
           if(numinfo.cur_x>0){
              camera.mainCam.translate(-numinfo.oneblock,0);
              numinfo.cur_x--;
           }
        }
        if(keycode == Input.Keys.RIGHT){
           if(numinfo.cur_x<numinfo.max_x){
              camera.mainCam.translate(numinfo.oneblock,0);
              numinfo.cur_x++;
           }
        }
        if(keycode == Input.Keys.UP){
           if(numinfo.cur_y<numinfo.max_y){
              camera.mainCam.translate(0,numinfo.oneblock);
              numinfo.cur_y++;
           }
        }
        if(keycode == Input.Keys.DOWN){
           if(numinfo.cur_y>0){
              camera.mainCam.translate(0,-numinfo.oneblock);
              numinfo.cur_y--;
           }
        }
        if(keycode == Input.Keys.SPACE)
           testing();
        if(keycode == Input.Keys.NUM_1)
            tiles.tiledMap.getLayers().get(0).setVisible(!tiles.tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiles.tiledMap.getLayers().get(1).setVisible(!tiles.tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int x = numinfo.cur_x + screenX/numinfo.oneblock, y = numinfo.cur_y + (Gdx.graphics.getHeight()-screenY)/numinfo.oneblock;
        boolean digcheck=false;
        MapProperties curPro = tiles.mapLayer.getCell(x,y).getTile().getProperties();
        
        digcheck=tiles.digging(x,y);
        
        if (digcheck==true){
        	Monster newMob;
        	switch (Integer.parseInt((String)curPro.get("nutrient"))){
        	case 0:
        		break;
        	case 1:
        		newMob = new Moss(spriteBatch, map, x, y);
                unitArrayList.add(newMob);
        		break;
        	case 2:
        		newMob = new Dragon(spriteBatch, map, x, y);
                unitArrayList.add(newMob);
        		break;
        	case 3:
        		newMob = new Dragon(spriteBatch, map, x, y);
                unitArrayList.add(newMob);
        		break;
        		
        	}
            map[x][y].digged = true;
        }
        
        /*
        if(tiles.mapLayer.getCell(x, y).getTile().getProperties().containsKey("blocked")){
        	if (tiles.mapLayer.getCell(x,y).getTile().getProperties().get("nurtrient")!=null){
        		
        	}
        	
            tiles.mapLayer.setCell(x, y, tiles.blockLayer.getCell(3, 0));
             
            
            if(!map[x][y].digged && map[x][y].underground){
            	Monster newMob;
            	if(y%2 == 0 || x != 5) newMob = new Moss(spriteBatch, map, x, y);
            	else newMob = new Dragon(spriteBatch, map, x, y);
                unitArrayList.add(newMob);
                map[x][y].digged = true;
            }
        }
        */
        
        
        /*
        tiles.mapLayer.setCell(x, y, tiles.blockLayer.getCell(0, 0));
        if(!map[x][y].digged && map[x][y].underground){
        	Monster newMob;
        	if(y%2 == 0 || x != 5) newMob = new Moss(spriteBatch, map, x, y);
        	else newMob = new Dragon(spriteBatch, map, x, y);
            unitArrayList.add(newMob);
            map[x][y].digged = true;
        }
        */

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
        if(y < numinfo.max_y) if(map[x][y+1].digged == true) ret = true;
        if(y > 0) if(map[x][y-1].digged == true) ret = true;
        if(x > 0) if(map[x-1][y].digged == true) ret = true;
        if(x < numinfo.max_x) if(map[x+1][y].digged == true) ret = true;

        return ret;
    }
}