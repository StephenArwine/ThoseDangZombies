package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.Draw;
import com.SaL.ThoseDangZombies.Input;

public class TitleScreen extends Screen {
	private String[] msg = { "USE AAROW KEYS TO MOVE,", " ", "X TO INTERACT,",
			" ", "AND SPACEBAR TO ATTACK!" };
	private int delay = 200;

	public void render(Graphics g) {
		g.drawImage(Draw.bg, 0, 0, null);
		// if (time>100){
		// WordsYo(msg, g, 160 , 140 - (int) (Math.abs(Math.sin(time * 0.05) *
		// 10)));
		// }
		int xs = 0;
		int ys = msg.length + 3;
		for (int y = 0; y < msg.length; y++) {
			int s = msg[y].length();
			if (s > xs)
				xs = s;
		}
		int xp = 160;
		int yp = 100 - ys * 3;
		for (int x = 0 - 1; x < xs + 1; x++) {
			for (int y = 0 - 1; y < ys + 1; y++) {

			}
		}
		for (int y = 0; y < msg.length; y++) {
			WordsYo(msg[y], g, xp, yp + y * 6);
		}
		if (delay == 0)
			WordsYo("PRESS X", g, xp, yp + (msg.length + 4) * 6);
	}

	public void update(Input input) {
		if (delay > 0)
			delay--;
		if (delay == 0 && input.buttons[Input.ACTION]
				&& !input.oldButtons[Input.ACTION]) {

			setScreen(new GameScreen());
			input.releaseAllKeys();
		}
	}
}
