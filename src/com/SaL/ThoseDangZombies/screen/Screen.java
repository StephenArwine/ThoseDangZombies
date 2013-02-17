package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.*;
import com.SaL.ThoseDangZombies.level.Level;

public class Screen {
	private ThoseDangZombies thosedangzombies;

	public final void init(ThoseDangZombies thosedangzombies) {
		this.thosedangzombies = thosedangzombies;
	}

	public void setScreen(Screen screen) {
		thosedangzombies.setScreen(screen);
	}

	String[] chars = { "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
			".,!?:;\"'+-=/\\< " };

	public void WordsYo(String string, Graphics g, int x, int y) {
		string = string.toUpperCase();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			for (int ys = 0; ys < chars.length; ys++) {
				int xs = chars[ys].indexOf(ch);
				if (xs >= 0) {
					g.drawImage(Draw.string[xs][ys],
							(x - (string.length() * 3)) + i * 6, y, null);
				}
			}
		}
	}
	public void levelswap(Level newlevel){
		
	}

	public void DamageString(String string, Graphics g, int x, int y,
			boolean negative) {
		string = string.toUpperCase();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			for (int ys = 0; ys < chars.length; ys++) {
				int xs = chars[ys].indexOf(ch);
				if (xs >= 0) {
					if (negative) {
						g.drawImage(Draw.redstring[xs][ys],
								(x - (string.length() * 3)) + i * 6, y, null);
					}else{
						g.drawImage(Draw.greenstring[xs][ys],
								(x - (string.length() * 3)) + i * 6, y, null);
					}
				}
			}
		}
	}

	public void removed() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
	}

	public void update(Input input) {
	}

}
