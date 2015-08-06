package com.mygdx.game;

abstract class Monster extends Unit{
	Monster(){
		this.point.x = 0;	//have to change -> monster have to be created on cursor
		this.point.y = 0;	//
	}
}

class Moss extends Monster{
	Moss(){
		this.hp = 10;
		this.ad = 1;
		this.df = 0;
	}

	@Override
	void render() {
		// TODO Auto-generated method stub
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