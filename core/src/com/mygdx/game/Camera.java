package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends ApplicationAdapter {
	OrthographicCamera mainCam;
	
	Camera (float w, float h) {
		this.mainCam = new OrthographicCamera();
		mainCam.setToOrtho(false,w,h);
		mainCam.update();
	}
	
}
