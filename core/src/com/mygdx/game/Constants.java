package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


class Constants{	
	public int oneblock, cur_x, cur_y, max_x, max_y, tileX, tileY;
	
	Constants(TiledMapTileLayer mapLayer){
		oneblock=(int) mapLayer.getTileHeight();
		cur_x=0;
		cur_y=0;
		tileX=mapLayer.getWidth();
		max_x=tileX-1;
		tileY=mapLayer.getHeight();
		max_y=tileY-1;
	}
}