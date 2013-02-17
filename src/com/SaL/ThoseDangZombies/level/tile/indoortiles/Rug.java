package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Rug extends IndoorTiles{
	public void Register(int x, int y) {
		world[x][y] = IndoorTiles.Rug;
	}
	public void render(int x, int y, Graphics g) {
		//woodfloor mask
		GameScreen.rendertile(0,0,x,y,g);
		GameScreen.rendertile(0,0,x+1,y,g);
		GameScreen.rendertile(0,0,x+2,y,g);
		GameScreen.rendertile(0,0,x,y+1,g);
		GameScreen.rendertile(0,0,x+1,y+1,g);
		GameScreen.rendertile(0,0,x+2,y+1,g);

		GameScreen.rendertile(13,0,x,y,g);
		GameScreen.rendertile(14,0,x+1,y,g);
		GameScreen.rendertile(15,0,x+2,y,g);
		GameScreen.rendertile(13,1,x,y+1,g);
		GameScreen.rendertile(14,1,x+1,y+1,g);
		GameScreen.rendertile(15,1,x+2,y+1,g);

		
	}
	
}
