package com.SaL.ThoseDangZombies.level.tile.towntiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Grass extends TownTiles {
	
	public void Register(int x, int y) {
		world[x][y] = TownTiles.Grass;
	}

	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(0,5,x,y,g);
	}

}
