package com.SaL.ThoseDangZombies.level.tile.towntiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class HouseRoof extends TownTiles {

	public void Register(int x, int y) {
		world[x][y] = TownTiles.HouseRoof;
		OverTiles[x][y] = true;

	}

	public void render(int x,int y, Graphics g){
		GameScreen.rendertile(0, 5, x, y-1, g);
		GameScreen.rendertile(0, 5, x+1, y-1, g);
		GameScreen.rendertile(0, 5, x+2, y-1, g);
		GameScreen.rendertile(0, 5, x+3, y-1, g);	
	}
	public void overrender(int x, int y, Graphics g) {
		GameScreen.rendertile(3,6,x,y,g);
		GameScreen.rendertile(4,6,x+1,y,g);
		GameScreen.rendertile(5,6,x+2,y,g);
		GameScreen.rendertile(6,6,x+3,y,g);
		
		GameScreen.rendertile(0,6,x,y-1,g);
		GameScreen.rendertile(1,6,x+1,y-1,g);
		GameScreen.rendertile(8,6,x+2,y-1,g);
		GameScreen.rendertile(9,6,x+3,y-1,g);

		
		
		
	}
}
