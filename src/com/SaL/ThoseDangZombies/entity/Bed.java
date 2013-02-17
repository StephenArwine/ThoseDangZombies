package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Bed extends Entity {
	private static int ySlot = 0;
	private static int xSlot = 6;

	public Bed(int x, int y) {
		this.x = x * 16 + 8;
		this.y = y * 16 + 8;
		this.w = 8;
		this.h = 8;
		interactable = true;
		hp = 1;

	}

	public void render(Graphics g, Camera camera) {
		GameScreen.rendertile(0, 0, (x - 8) / 16, (y - 8) / 16, g);
		GameScreen.rendertile(0, 0, (x-8)/16, ((y-8)/16) - 1, g);
		GameScreen.rendertile(xSlot, ySlot, (x - 8) / 16,( (y - 8) / 16) -1, g);
		GameScreen.rendertile(xSlot, ySlot + 1, (x - 8) / 16, (y - 8) / 16, g);
	}
	public void interaction() {
		if (hp ==1){
			msg = new String[] { "You made the bed yey!" };
			level.popup(this);
			hp--;
			ySlot = 2;
			xSlot = 4;
		}
	}
}
