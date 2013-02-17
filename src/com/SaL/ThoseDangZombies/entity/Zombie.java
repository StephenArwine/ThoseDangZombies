package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;
import java.util.Random;

import com.SaL.ThoseDangZombies.level.Camera;

public class Zombie extends Entity {

	private static int ySlot = 1;
	private static int xSlot = 1;
	private int anim = 0;
	private int anim2 = 0;
	private static final Random random = new Random();
	private int number = 0;
	private int randir = 0;

	public Zombie(int x, int y) {
		this.w = 5;
		this.h = 9;
		this.x = x * 16 + 2 * w;
		this.y = y * 16 + h;
		attackable = true;
		hp = 2;
		interactable = true;

	}

	public void update() {

		tryattack();
		xa = 0;
		ya = 0;
		if (!attacking) {
			randommovement();
		}
		if (anim < 40)
			anim++;
		else {

			anim = 0;
		}

		if (xa != 0 || ya != 0) {
			walking = true;
			move(xa, ya);
		} else {
			walking = false;
		}
		if (hit) {
			if (anim2 < 40)
				anim2++;
			else {
				anim2 = 0;
				hit = false;
				OverHead = "";
			}
		}

		if (checkattack) {
			checkattack = false;
			checkattack(this);
		}
	}

	public void render(Graphics g, Camera camera) {

		xSlot = 0;
		if (dir == 0)
			ySlot = 0;
		if (dir == 1)
			ySlot = 1;
		if (dir == 2)
			ySlot = 2;
		if (dir == 3)
			ySlot = 3;

		xSlot = 0;
		if (anim > 10) {
			xSlot = 1;
		}
		if (anim > 20) {
			xSlot = 2;
		}
		if (anim > 30) {
			xSlot = 1;
		}

		if (walking) {
			level.screen.renderplayer(xSlot, ySlot, x - 7, y - 13, g, 3);
		} else if (attacking) {
			if (ySlot == 0) {
				level.screen.renderplayer(xSlot, ySlot, x - 7, y - 13 - xSlot,
						g, 4);
			}
			if (ySlot == 1) {
				level.screen.renderplayer(xSlot, ySlot, x - 7 + xSlot, y - 13,
						g, 4);
			}
			if (ySlot == 2) {
				level.screen.renderplayer(xSlot, ySlot, x - 7, y - 12 + xSlot,
						g, 4);
			}
			if (ySlot == 3) {
				level.screen.renderplayer(xSlot, ySlot, x - 7 - xSlot, y - 13,
						g, 4);
			}
			if (anim == 20) {
				checkattack = true;
			}
			if (anim == 40)
				attacking = false;

		} else {
			level.screen.renderplayer(1, ySlot, x - 7, y - 13, g, 3);

		}
		if (hit) {
			level.screen.damagedisplay(OverHead, x - w, y - h - 3, g, negative);
		}
	}

	public void randommovement() {

		if (anim > 39) {
			if (!walking) {
				number = random.nextInt(3);
				if (number == 1) {
					randir = random.nextInt(4);
					int randis = random.nextInt(25);
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

	public void interaction() {
		msg = new String[] { "BRAINS!" };
		level.popup(this);

	}

	public void tryattack() {
		java.util.List<Entity> entities = level.getEntities(this);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			switch (dir) {
			case 0:
				if (e.xSpot == this.xSpot && e.ySpot == this.ySpot - 1) {
					if (e instanceof Player) {
						attacking = true;
					}
				}

				break;
			case 1:
				if (e.xSpot == this.xSpot + 1 && e.ySpot == this.ySpot) {
					if (e instanceof Player) {
						attacking = true;

					}

				}
				break;
			case 2:
				if (e.xSpot == this.xSpot && e.ySpot == this.ySpot + 1) {
					if (e instanceof Player) {
						attacking = true;

					}

				}
				break;
			case 3:
				if (e.xSpot == this.xSpot - 1 && e.ySpot == this.ySpot) {
					if (e instanceof Player) {
						attacking = true;

					}

				}
				break;
			}

		}

	}

}
