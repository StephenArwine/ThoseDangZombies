package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.level.tile.Tile;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Bed extends Tile {
	public void Register(int x, int y) {
	loc[x][y] = IndoorTiles.Bed;
	world[x][y] = loc[x][y];
	world[x][y-1] = null;
	//Level.Solid[x][y] = true;
	//Level.Solid[x][y-1] = true;
	}
	
	public void render(int x, int y, Graphics g) {
		//woodfloor mask
		GameScreen.rendertile(0,0,x,y,g);
		GameScreen.rendertile(0,0,x,y-1,g);
		GameScreen.rendertile(6,1,x,y,g);
		GameScreen.rendertile(6,0,x,y-1,g);
	}
	
}
