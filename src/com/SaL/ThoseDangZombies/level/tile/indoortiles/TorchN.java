package com.SaL.ThoseDangZombies.level.tile.indoortiles;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.ThoseDangZombies;
import com.SaL.ThoseDangZombies.level.tile.Tile;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class TorchN extends Tile {

	public void Register(int x, int y) {
		loc[x][y] = IndoorTiles.TorchN;
		world[x][y] = loc[x][y];
	}

	public void render(int x, int y, Graphics g) {
		GameScreen.rendertile(1, 0, x, y - 1, g);
		GameScreen.rendertile(0, 0, x, y, g);

		int xSlot = 0;
		if (ThoseDangZombies.anim < 15)
			xSlot = 1;

		GameScreen.rendertile(5, xSlot, x, y-1, g);

	}

	public void overrender(int x, int y, Graphics g) {

	}
}
