package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.javafx.scene.traversal.Direction;

abstract class Monster extends Unit{
	Monster(SpriteBatch batch, Pixel[][] map, int x, int y){
		super(batch, map);
		this.point.x = x;	//have to change -> monster have to be created on cursor
		this.point.y = y;	//
	}
}

class Moss extends Monster{
	Moss(SpriteBatch batch, Pixel[][] map, int x, int y){
		super(batch, map, x, y);
		this.hp = 10;
		this.ad = 1;
		this.df = 0;
		this.img = new Texture("moss.jpg");
		this.movDir = Direction.RIGHT;
	}

	@Override
	void render() {
		// TODO Auto-generated method stub
		this.batch.draw(this.img, this.point.x * 64, this.point.y * 64);	//Can it work on tiled map?
		if(this.move() == false){
			attack();
		}
	}

	@Override
	void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean move() {
		// TODO Auto-generated method stub
		return true;
	}
}