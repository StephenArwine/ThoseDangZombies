package com.SaL.ThoseDangZombies.level.tile.towntiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class HouseCenter extends TownTiles {

	public void Register(int x, int y) {
		world[x][y] = TownTiles.HouseCenter;
	}

	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(0,4,x,y,g);
	}
	
}
