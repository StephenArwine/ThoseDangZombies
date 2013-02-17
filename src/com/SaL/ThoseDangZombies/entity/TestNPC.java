package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;
import java.util.Random;

import com.SaL.ThoseDangZombies.level.Camera;

public class TestNPC extends Entity {

	private static int ySlot = 1;
	private static int xSlot = 1;
	private int anim = 0;
	private int anim2 = 0;
	private static final Random random = new Random();
	private int number = 0;
	private int randir = 0;

	public TestNPC(int x, int y) {
		this.w = 5;
		this.h = 9;
		this.x = x * 16 + 2 * w;
		this.y = y * 16 + h;
		attackable = true;
		hp = 3;
		interactable = true;

	}

	public void update() {

		xa = 0;
		ya = 0;
		randommovement();
		if (anim < 60)
			anim++;
		else {

			anim = 0;
		}

		// xa++;
		// ya++;
		if (xa != 0 || ya != 0) {
			walking = true;
			move(xa, ya);
		} else {
			walking = false;
		}
		if (hit) {
			if (anim2 < 60)
				anim2++;
			else {
				anim2 = 0;
				hit = false;
				OverHead = "";
			}
		}
	}

	public void render(Graphics g, Camera camera) {

		xSlot = 4;
		if (dir == 0)
			ySlot = 0;
		if (dir == 1)
			ySlot = 1;
		if (dir == 2)
			ySlot = 2;
		if (dir == 3)
			ySlot = 3;
		if (walking) {

			xSlot = 3;
			if (anim > 20) {
				xSlot = 4;
			}
			if (anim > 40) {
				xSlot = 5;
			}

		}
		level.screen.renderplayer(xSlot, ySlot, x - 7, y - 8, g, 1);
		if (hit) {
			level.screen.damagedisplay(OverHead, x - w, y - h - 3, g, negative);
		}
	}

	public void randommovement() {

		if (anim > 59) {
			if (!walking) {
				number = random.nextInt(3);
				if (number == 1) {
					randir = random.nextInt(4);
					int randis = random.nextInt(200);
					if (randir == 0)
						yp += -randis;
					if (randir == 1)
						yp += randis;
					if (randir == 2)
						xp += -randis;
					if (randir == 3)
						xp += randis;

				}

			}
		}
		if (yp != 0) {
			if (yp > 0) {
				ya++;
				yp--;
			}
			if (yp < 0) {
				ya--;
				yp++;
			}
		}
		if (xp != 0) {
			if (xp > 0) {
				xa++;
				xp--;
			}
			if (xp < 0) {
				xa--;
				xp++;
			}
		}
	}

	public void hit(int hitpower) {

		 hp += hitpower;
		hit = true;
		if (hitpower < 0) {
			negative = true;
		} else {
			negative = false;
		}
		OverHead = Integer.toString(hitpower);
		if (hp <= 0) {
			removed = true;
		}
	}
	public void interaction(){
		msg = new String[] { "HELP!!","The ZoMbIeS are coming!","HELP!!" };
		level.popup(this);

	}
}
