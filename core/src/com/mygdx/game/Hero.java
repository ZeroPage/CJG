package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


abstract class Hero extends Unit{
	Hero(SpriteBatch batch, Pixel[][] map) {
		super(batch, map);
		// TODO Auto-generated constructor stub
	}
	int mp;
	ArrayList<Skill> skills = new ArrayList<Skill>();
}