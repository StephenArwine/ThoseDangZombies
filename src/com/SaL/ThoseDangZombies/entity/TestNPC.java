package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.ThoseDangZombies;
import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class TestNPC extends Entity {
	private boolean walking = false;
	private static int ySlot = 1;
	private static int xSlot = 1;

	public TestNPC(int x, int y) {
		this.x = x * 16 + w;
		this.y = y * 16 + h;

	}

	public void update() {
		xa = 0;
		ya = 0;
		if (anim < 30)
			anim++;
		else
			anim = 0;
		 xa++;
		//ya++;
		walking = true;
		move(xa, ya);
	}

	public void render(Graphics g, Camera camera) {
		xSlot = 4;

		if (walking) {
			if (dir == 0)
				ySlot = 0;
			if (dir == 1)
				ySlot = 1;
			if (dir == 2)
				ySlot = 2;
			if (dir == 3)
				ySlot = 3;
			xSlot = 3;
			if (ThoseDangZombies.anim > 10) {
				xSlot = 4;
			}
			if (ThoseDangZombies.anim > 20) {
				xSlot = 5;
			}

		}
		GameScreen.renderplayer(xSlot, ySlot, x - 8, y - 12, g);

	}

}
