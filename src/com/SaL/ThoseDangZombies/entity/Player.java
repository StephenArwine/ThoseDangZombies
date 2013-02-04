package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.Input;
import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.level.Level;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Player extends Entity {
	private boolean walking = false;
	private static int ySlot = 1;
	private static int xSlot = 1;
	public boolean readSign = false;
	private int anim = 0;

	@SuppressWarnings("static-access")
	public Player(Level level) {
		x = level.xSpawn << 4;
		y = level.ySpawn << 4;
		w = 8;
		h = 8;
		solid = true;
	}

	public void input(Input input) {
		xa = 0;
		ya = 0;
		if (anim < 60)
			anim++;
		else
			anim = 0;
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
		if (xa != 0 || ya != 0) {
		walking = true;
			move(xa, ya);

		} else {
			walking = false;
			// falling = false;
		}
	}

	public void render(Graphics g, Camera camera) {
		xSlot = 1;

		if (walking) {
			if (dir == 0)
				ySlot = 0;
			if (dir == 1)
				ySlot = 1;
			if (dir == 2)
				ySlot = 2;
			if (dir == 3)
				ySlot = 3;
			xSlot = 0;
			if (anim > 20) {
				xSlot = 1;
			}
			if (anim > 40) {
				xSlot = 2;
			}
		}
		GameScreen.renderplayer(xSlot, ySlot, x - 8, y - 10, g);

	}

	public void event(Events events) {
		if (events.auto || readSign) {
			events.auto = false;
			level.popup(events);
		}
	}

	public void update() {
		anim++;
	}
}
