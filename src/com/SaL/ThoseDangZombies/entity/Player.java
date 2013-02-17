package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.Input;
import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.screen.TitleScreen;

public class Player extends Entity {
	private static int ySlot = 1;
	private static int xSlot = 1;
	public boolean readSign = false;
	private int anim = 0;
	private int anim2 = 0;
	private boolean reset = false;
	private int delay = 10;

	public Player(int xSpawn, int ySpawn) {
		w = 6;
		h = 5;
		x = (xSpawn << 4) + 8;
		y = (ySpawn << 4) + 13;
		hitpower = -2;
		attackable = true;
		hp = 2;

	}

	public void input(Input input) {

		xa = 0;
		ya = 0;

		if (input.buttons[Input.UP]) {
			ya--;
		}
		if (input.buttons[Input.DOWN]) {
			ya++;
		}
		if (input.buttons[Input.LEFT]) {
			xa--;
		}
		if (input.buttons[Input.RIGHT]) {
			xa++;
		}
		if (input.buttons[Input.ATTACK]) {
			if (!attacking) {
				xa = 0;
				ya = 0;
				anim = 0;
				attacking = true;
			}
		}
		if (input.buttons[Input.ACTION]) {
			tryaction();
		}
		if (xa != 0 || ya != 0) {
			if (!attacking) {
				walking = true;
			}
			move(xa, ya);

		} else {
			walking = false;
			// falling = false;
		}
		// System.out.println("x:"+x+" y:"+y);
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
			level.screen.renderplayer(xSlot, ySlot, x - 7, y - 13, g, 1);
		} else if (attacking) {
			if (ySlot == 0) {
				level.screen.renderplayer(3, ySlot, x - 7, y - 22 - xSlot * 2,
						g, 2);
				level.screen.renderplayer(xSlot, ySlot, x - 7, y - 13 - xSlot,
						g, 2);

			}
			if (ySlot == 1) {

				level.screen.renderplayer(xSlot, ySlot, x - 7 + xSlot, y - 13,
						g, 2);
				level.screen.renderplayer(3, ySlot, x + 7 + xSlot * 2, y - 13,
						g, 2);

			}
			if (ySlot == 2) {

				level.screen.renderplayer(xSlot, ySlot, x - 7, y - 12 + xSlot,
						g, 2);
				level.screen.renderplayer(3, ySlot, x - 7, y + 3 + xSlot * 2,
						g, 2);

			}
			if (ySlot == 3) {

				level.screen.renderplayer(xSlot, ySlot, x - 7 - xSlot, y - 13,
						g, 2);
				level.screen.renderplayer(3, ySlot, x - 21 - xSlot * 2, y - 13,
						g, 2);

			}
			if (anim == 20) {
				checkattack = true;
			}
			if (anim == 40)
				attacking = false;

		} else {
			level.screen.renderplayer(1, ySlot, x - 7, y - 13, g, 1);

		}
		if (hit) {
			level.screen.damagedisplay(OverHead, x - w, y - h - 9, g, negative);
		}

	}

	public void event(Entity e) {
		e.removed = true;
		level.popup(e);
	}

	public void tryaction() {
		java.util.List<Entity> entities = level.getEntities(this);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			switch (dir) {
			case 0:
				if (e.xSpot == this.xSpot && e.ySpot == this.ySpot - 1
						&& e.interactable) {
					e.interaction();
				}

				break;
			case 1:
				if (e.xSpot == this.xSpot + 1 && e.ySpot == this.ySpot
						&& e.interactable) {
					e.interaction();

				}
				break;
			case 2:
				if (e.xSpot == this.xSpot && e.ySpot == this.ySpot + 1
						&& e.interactable) {
					e.interaction();

				}
				break;
			case 3:
				if (e.xSpot == this.xSpot - 1 && e.ySpot == this.ySpot
						&& e.interactable) {
					e.interaction();

				}
				break;
			}

		}
	}

	public void update() {
		if (anim < 40)
			anim++;
		else
			anim = 0;
		if (checkattack) {
			checkattack = false;
			checkattack(this);
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
		if (reset) {
			delay --;
			if (delay == 0){
				level.screen.setScreen(new TitleScreen());
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
		System.out.println(hp);

		OverHead = Integer.toString(hitpower);
		if (hp <= 0) {
			msg = new String[] { "YOU DIED!" };
			level.popup(this);
			reset = true;
		}
	}

}
