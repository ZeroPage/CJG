package com.mygdx.game;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.javafx.scene.traversal.Direction;

abstract class Monster extends Unit{
	Monster(SpriteBatch batch, Pixel[][] map, int x, int y){
		super(batch, map);
		this.point.x = x;	//have to change -> monster have to be created on cursor
		this.point.y = y;	//
	}
	abstract void changeDir();
	abstract int nextMoveX();
	abstract int nextMoveY();
}

class Moss extends Monster{
	private float timer;
	
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
		this.batch.draw(this.img, this.point.x * ONE_BLOCK, this.point.y * ONE_BLOCK);	//Can it work on tiled map?
		timer += Gdx.graphics.getDeltaTime();
		if(timer >= 1){
			timer -= 1;
			if(this.move() == false){
				attack();
			}
		}
	}

	@Override
	void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean move() {
		// TODO Auto-generated method stub
		boolean temp = false;
		int whileNum = 0;
		//this version, it just move, cannot attack
		while(!temp && whileNum < 4){
			if(!isInMap(nextMoveX(), nextMoveY())){
				changeDir();
				whileNum++;
				continue;
			}
			temp = map[nextMoveX()][nextMoveY()].digged;
			if(map[nextMoveX()][nextMoveY()].digged){
				point.x = nextMoveX();
				point.y = nextMoveY();
			}
			else changeDir();
			whileNum++;
		}
		return true;
	}
	@Override
	int nextMoveX(){
		if(movDir == Direction.RIGHT) return point.x + 1;
		else if(movDir == Direction.LEFT) return point.x - 1;
		else return point.x;
	}
	@Override
	int nextMoveY(){
		if(movDir == Direction.UP) return point.y + 1;
		else if(movDir == Direction.DOWN) return point.y - 1;
		else return point.y;
	}
	@Override
	void changeDir(){
		if(movDir == Direction.RIGHT) movDir = Direction.DOWN;
		else if(movDir == Direction.DOWN) movDir = Direction.LEFT;
		else if(movDir == Direction.LEFT) movDir = Direction.UP;
		else if(movDir == Direction.UP) movDir = Direction.RIGHT;
	}
}

class Dragon extends Monster{
	private float timer;
	
	Dragon(SpriteBatch batch, Pixel[][] map, int x, int y){
		super(batch, map, x, y);
		this.hp = 500;
		this.ad = 30;
		this.df = 20;
		this.img = new Texture("dragon.jpg");
		this.movDir = Direction.RIGHT;
	}

	@Override
	void render() {
		// TODO Auto-generated method stub
		this.batch.draw(this.img, this.point.x * ONE_BLOCK, this.point.y * ONE_BLOCK);	//Can it work on tiled map?
		timer += Gdx.graphics.getDeltaTime();
		if(timer >= 1){
			timer -= 1;
			if(this.move() == false){
				attack();
			}
		}
	}

	@Override
	void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean move() {
		// TODO Auto-generated method stub
		boolean temp = false;
		int whileNum = 0;
		//this version, it just move, cannot attack
		while(!temp && whileNum < 2){
			if(!isInMap(nextMoveX(), nextMoveY())){
				changeDir();
				whileNum++;
				continue;
			}
			temp = map[nextMoveX()][nextMoveY()].digged;
			if(!map[nextMoveX()][nextMoveY()].PlacedUnit.isEmpty()){
				return false; //attack!!
			}
			else if(map[nextMoveX()][nextMoveY()].digged){
				point.x = nextMoveX();
				point.y = nextMoveY();
			}
			else changeDir();
			whileNum++;
		}
		return true;
	}
	@Override
	int nextMoveX(){
		if(movDir == Direction.RIGHT) return point.x + 1;
		else if(movDir == Direction.LEFT) return point.x - 1;
		else return point.x;
	}
	@Override
	int nextMoveY(){
		return point.y;
	}
	@Override
	void changeDir(){
		if(movDir == Direction.RIGHT) movDir = Direction.LEFT;
		else if(movDir == Direction.LEFT) movDir = Direction.RIGHT;
	}
}