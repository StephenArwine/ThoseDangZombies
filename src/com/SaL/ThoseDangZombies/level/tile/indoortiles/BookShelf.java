package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class BookShelf extends IndoorTiles{
	public void Register(int x, int y) {
		world[x][y] = IndoorTiles.BookShelf;
		
	}
	public void render(int x, int y, Graphics g) {
		//woodfloor mask
		GameScreen.rendertile(0,0,x,y,g);
		GameScreen.rendertile(0,0,x+1,y,g);
		
		GameScreen.rendertile(0,3,x,y,g);
		GameScreen.rendertile(1,3,x+1,y,g);
		GameScreen.rendertile(0,2,x,y-1,g);
		GameScreen.rendertile(1,2,x+1,y-1,g);
		
	}
}
