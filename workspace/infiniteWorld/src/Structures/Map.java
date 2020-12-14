package Structures;

import java.awt.Graphics;

import engine.engine;

public class Map extends engine {
	String assetsPath;

	public Map() {

		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
	}

	public void drawBackground(Graphics g) {

		addImage(g, "\\Backgrounds\\background.png", x, (int) y);

	}

}
