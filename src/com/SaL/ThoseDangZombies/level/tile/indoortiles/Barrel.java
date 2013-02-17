package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Barrel extends IndoorTiles{
	public void Register(int x, int y) {
		world[x][y] = IndoorTiles.Barrel;
		
	}
	public void render(int x, int y, Graphics g) {
		//woodfloor mask
		GameScreen.rendertile(0,0,x,y,g);
		GameScreen.rendertile(2,3,x,y,g);

		
	}
	
}
