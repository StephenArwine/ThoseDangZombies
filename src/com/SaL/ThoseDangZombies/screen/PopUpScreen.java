package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.Draw;
import com.SaL.ThoseDangZombies.Input;
import com.SaL.ThoseDangZombies.entity.Entity;

public class PopUpScreen extends Screen {
	private Screen parent;
	private Entity e;
	private String[] msg;

	private int delay = 60;

	public PopUpScreen(Screen parent, Entity e) {
		this.e = e;
		this.parent = parent;
		this.msg = e.msg;
	}

	public void render(Graphics g) {
		parent.render(g);
		int xs = 0;
		int ys = msg.length + 3;
		for (int y = 0; y < msg.length; y++) {
			int s = msg[y].length();
			if (s > xs)
				xs = s;
		}
		int xp = 160;
		int yp = 180 - ys * 3;
		for (int x = 0 - 1; x < xs + 1; x++) {
			for (int y = 0 - 1; y < ys + 1; y++) {
				int xf = 1;
				int yf = 3;
				if (x < 0)
					xf--;
				if (y < 0)
					yf--;
				if (x >= xs)
					xf++;
				if (y >= ys)
					yf++;
				g.drawImage(Draw.string[xf][yf],(xp-(xs*3)) +x * 6, yp + y * 6, null);
			}
		}
		for (int y = 0; y < msg.length; y++) {
			WordsYo(msg[y], g, xp, yp + y * 6);
		}
		if (delay == 0)
			WordsYo("PRESS X", g, xp + (xs - 8), yp + (msg.length + 2) * 6);
	}

	public void update(Input input) {
		if (!input.oldButtons[Input.ESCAPE] && input.buttons[Input.ESCAPE]) {
			setScreen(parent);
			return;
		}
		if (delay > 0)
			delay--;
		if (delay == 0 && input.buttons[Input.ACTION]
				&& !input.oldButtons[Input.ACTION]) {
			input.releaseAllKeys();
			if (e.special) {
				e.level.specials(e.xSpot,e.ySpot, e);
			} else {
				setScreen(parent);
			}
		}
	}
}
