package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.*;

public class MenuScreen extends Screen {
	private GameScreen parent;
	private int selected = 0;

	private String[] options = { "BACK TO GAME", "QUIT TO TITLE" };

	public MenuScreen(GameScreen parent) {
		this.parent = parent;
	}

	public void render(Graphics g) {
		parent.render(g);
		int xs = 0;
		int ys = options.length;
		for (int y = 0; y < options.length; y++) {
			int s = options[y].length();
			if (s > xs)
				xs = s;
		}
		xs += 1;
		int xp = 40;
		int yp = 40;
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
				g.drawImage(Draw.string[xf][yf], xp + x * 6, yp + y * 6, null);
			}
		}
		for (int y = 0; y < options.length; y++) {
			if (y == selected) {
				WordsYo("+", g, xp, yp + y * 6);
			}
			WordsYo(options[y], g, xp + 6, yp + y * 6);
		}
	}

	public void update(Input input) {
		if (!input.oldButtons[Input.ESCAPE] && input.buttons[Input.ESCAPE]) {
			setScreen(parent);
			return;
		}
		if (input.buttons[Input.UP] && !input.oldButtons[Input.UP]) {
			selected--;
			if (selected < 0)
				selected += options.length;
		}
		if (input.buttons[Input.DOWN] && !input.oldButtons[Input.DOWN]) {
			selected++;
			if (selected >= options.length)
				selected -= options.length;
		}
		if (input.buttons[Input.SHOOT] && !input.oldButtons[Input.SHOOT]) {
			if (selected == 0) {
				setScreen(parent);
			} else if (selected == 1) {
				// parent.level.player.die();
				// setScreen(parent);
			} else if (selected == 2) {
				 setScreen(new TitleScreen());
			} else if (selected == 3) {

			}
		}
	}
}
