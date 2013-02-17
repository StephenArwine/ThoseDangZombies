package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class StairUp extends IndoorTiles {
	public void Register(int x, int y) {
		loc[x][y] = IndoorTiles.StairUp;
		world[x][y] = loc[x][y];
		Solid[x][y] = true;
		OverTiles[x][y] = true;
	}
	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(0,0,x,y,g);
		GameScreen.rendertile(12,1,x,y,g);
	}

	public void overrender(int x, int y, Graphics g) {
		GameScreen.rendertile(12,0,x,y-1,g);

	}
}
