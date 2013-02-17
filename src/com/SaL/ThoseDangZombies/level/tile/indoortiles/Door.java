package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Door extends IndoorTiles  {
	public void Register(int x, int y) {
		loc[x][y] = IndoorTiles.Door;
		world[x][y] = loc[x][y];
	}
	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(1,0,x,y,g);
		GameScreen.rendertile(0,1,x,y,g);
	}
}
