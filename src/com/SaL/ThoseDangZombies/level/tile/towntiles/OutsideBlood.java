package com.SaL.ThoseDangZombies.level.tile.towntiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class OutsideBlood extends TownTiles{
	public void Register(int x, int y) {
		loc[x][y] = TownTiles.OutsideBlood;
		world[x][y] = loc[x][y];
	}
	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(0,5,x,y,g);
		GameScreen.rendertile(11,0,x,y,g);
	}

}
