package com.mygdx.game;

import java.util.ArrayList;

public class Pixel {
    public ArrayList<Unit> PlacedUnit;
    public int nutrient;
    public boolean digged;
    public boolean entrance;
    public boolean underground;
    Pixel(){
        PlacedUnit = new ArrayList<Unit>();
        nutrient = 0;
        digged = false;
        entrance = false;
        underground = true;
    }
}