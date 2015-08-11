package com.mygdx.game;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.javafx.scene.traversal.Direction;

public abstract class Unit {
	int hp,ad,df,mr;	//mr == magic resist
	Point point;
	Texture img;
	Direction movDir;
	Batch batch;
	Pixel[][] map;
	Unit(SpriteBatch batch, Pixel[][] map){
		this.batch = batch;
		this.map = map;
		
	}
	abstract void render();
	abstract void attack();
	abstract boolean move();
}