package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class WallWest extends IndoorTiles {
	public void Register(int x, int y) {
		loc[x][y] = IndoorTiles.WallWest;
		world[x][y] = loc[x][y];
		Solid[x][y] = true;

	}

	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(2,0,x,y,g);

	}
}
