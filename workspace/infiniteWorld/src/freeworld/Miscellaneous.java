package freeworld;
import java.awt.Graphics;

import engine.engine;

public class Miscellaneous extends engine{

	int width;
	int height;
	String assetsPath;
	
	public Miscellaneous() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		
		width = 2000;
		height = 2000;
	}
	
}
