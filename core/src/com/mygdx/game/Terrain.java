package com.mygdx.game;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Terrain {
	int[][] nutrient;
	
	Terrain(int x, int y, int groundLevel, TiledMapTileLayer mainMap){ // x is map's width by blocks. y is map's height height by blocks. groundLevel is the position of ground count from top. mainMap needs main map's Layer
		int check;
		nutrient = new int[x][y-groundLevel];
		
		for (int i=0;i<x;i++){
			for (int j=0;j<y-groundLevel;j++){
				check = checkTerrain(mainMap.getProperties());
				nutrient[i][j]=check;
			}
		}
	}
	
	int checkTerrain (MapProperties temp){
		Object ret;
		
		ret = temp.get("nutrient");
		
		if (ret==null)
			return 0;
		else
			return (Integer)ret;
	}
}
