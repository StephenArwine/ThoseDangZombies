package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;
import java.util.Random;

import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class TestNPC extends Entity {

	private boolean walking = false;
	private static int ySlot = 1;
	private static int xSlot = 1;
	private int anim = 0;
	private static final Random random = new Random();
	private int number = 0;
	private int randir = 0;

	public TestNPC(int x, int y) {
		solid = true;
		this.w = 10;
		this.h = 10;
		this.x = x * 16 + w;
		this.y = y * 16 + h;

	}

	public void update() {

		xa = 0;
		ya = 0;
		randommovement();
		if (anim < 60) anim++;
		else {

			anim = 0;
		}

		//	 xa++;
		//ya++;
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void render(Graphics g, Camera camera) {

		xSlot = 4;

		if (walking) {
			if (dir == 0) ySlot = 0;
			if (dir == 1) ySlot = 1;
			if (dir == 2) ySlot = 2;
			if (dir == 3) ySlot = 3;
			xSlot = 3;
			if (anim > 20) {
				xSlot = 4;
			}
			if (anim > 40) {
				xSlot = 5;
			}

		}
		GameScreen.renderplayer(xSlot, ySlot, x - 8, y - 12, g);

	}

	@SuppressWarnings("static-access")
	public void randommovement() {

		if (anim > 59) {
			if (!walking) {
				number = random.nextInt(3);
				if (number == 1) {
					randir = random.nextInt(4);
					int randis = random.nextInt(200);
					if (randir == 0) yp += -randis;
					if (randir == 1) yp += randis;
					if (randir == 2) xp += -randis;
					if (randir == 3) xp += randis;

				}

			}
		}
		if (yp != 0) {
			if (yp > 0) {
				if (!level.Solid[xSpot][ySpot + 1]) ya++;
				yp--;
			}
			if (yp < 0) {
				if (!level.Solid[xSpot][ySpot - 1]) ya--;
				yp++;
			}
		}
		if (xp != 0) {
			if (xp > 0) {
				if (!level.Solid[xSpot + 1][ySpot]) xa++;
				xp--;
			}
			if (xp < 0) {
				if (!level.Solid[xSpot - 1][ySpot]) xa--;
				xp++;
			}
		}
	}
}
