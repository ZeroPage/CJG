package com.mygdx.game;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Tilemap {
	
	TiledMap tiledMap;
	TiledMapTileLayer mapLayer;
	TiledMapRenderer tiledMapRenderer;
	
	TiledMap blockTile;
	TiledMapTileLayer blockLayer;
	
	Tilemap(){
		tiledMap = new TmxMapLoader().load("temp.tmx");
		mapLayer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		blockTile = new TmxMapLoader().load("block.tmx");
		blockLayer = (TiledMapTileLayer)blockTile.getLayers().get(0);
		
	}
	
	boolean digging (int x, int y) {
		MapProperties curPro = mapLayer.getCell(x, y).getTile().getProperties();
		
		if(curPro.containsKey("blocked")){        	       
            mapLayer.setCell(x, y, blockLayer.getCell(3,0)); 
            
            return true;
        }
		else
			return false;
	}
	
	void nutriDiffusion(int x, int y) {
		
		if(x>0)
		checkNutrition(x-1,y);
		
		if(x<29)
		checkNutrition(x+1,y);
		
		if(y>0)
		checkNutrition(x,y-1);
		
		if(y<25)
		checkNutrition(x,y+1);
		
	}
	void checkNutrition(int x, int y){
		MapProperties curPro = mapLayer.getCell(x, y).getTile().getProperties();
		
		if (curPro.containsKey("blocked")){
			int curNutri = Integer.parseInt((String) curPro.get("nutrient"));
			System.out.println(curNutri);
			if(curNutri<3){
				mapLayer.setCell(x,y,blockLayer.getCell(curNutri+5,0));	
			}
			
		}
	}
	
}
