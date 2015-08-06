package com.mygdx.game;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.sun.javafx.scene.traversal.Direction;

public abstract class Unit {
	int hp,ad,df,mr;	//mr == magic resist
	Point point;
	Texture img;
	Direction movDir;
	Unit(){
		
	}
	abstract void render();
	abstract void attack();
	abstract boolean move();
}