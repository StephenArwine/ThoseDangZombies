package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.level.Level;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Table extends IndoorTiles {
	public void Register(int x, int y) {
	loc[x][y] = IndoorTiles.Table;
	Level.world[x][y] = loc[x][y];
	Level.world[x+1][y] = null;
	Level.Solid[x][y] = true;
	Level.Solid[x+1][y] = true;
	Level.OverTiles[x][y] = true;

	}
	
	public void render(int x, int y, Graphics g) {
		//woodfloor mask
		GameScreen.rendertile(0,0,x,y,g);
		GameScreen.rendertile(0,0,x+1,y,g);
		GameScreen.rendertile(8,1,x,y,g);
		GameScreen.rendertile(9,1,x+1,y,g);
	}
	public void overrender(int x, int y, Graphics g) {
		GameScreen.rendertile(8,0,x,y-1,g);
		GameScreen.rendertile(9,0,x+1,y-1,g);
	}
}
