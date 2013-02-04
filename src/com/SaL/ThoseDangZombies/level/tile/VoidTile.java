package com.SaL.ThoseDangZombies.level.tile;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.screen.GameScreen;

public class VoidTile extends Tile{

	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(0,1,x,y,g);

	}
}
