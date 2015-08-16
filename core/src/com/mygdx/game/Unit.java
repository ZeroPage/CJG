package com.mygdx.game;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.javafx.scene.traversal.Direction;

public abstract class Unit {
	final int ONE_BLOCK = 32;
	int hp,ad,df,mr;	//mr == magic resist
	Point point;
	Texture img;
	Direction movDir;
	SpriteBatch batch;
	Pixel[][] map;
	Unit(SpriteBatch batch, Pixel[][] map){
		this.batch = batch;
		this.map = map;
		point = new Point();
		
	}
	abstract void render();
	abstract void attack();
	abstract boolean move();
}