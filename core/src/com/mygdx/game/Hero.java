package com.mygdx.game;

import java.util.ArrayList;


abstract class Hero extends Unit{
	int mp;
	ArrayList<Skill> skills = new ArrayList<Skill>();
}