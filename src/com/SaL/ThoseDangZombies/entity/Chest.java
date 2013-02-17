package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Chest extends Entity {

	private static int ySlot = 2;
	private static int xSlot = 3;

	public Chest(int x, int y) {
		this.x = x * 16 + 8;
		this.y = y * 16 + 8;
		this.w = 8;
		this.h = 8;
		interactable = true;
		hp = 3;

	}

	public void render(Graphics g, Camera camera) {
		GameScreen.rendertile(xSlot, ySlot, (x-8)/16, (y-8)/16, g);
	}

	public void interaction() {

		if (hp == 3) {
			msg = new String[] { "tresure!!" };
			level.popup(this);
			hp--;
			xSlot --;
			return;
		}
		if (hp ==2){
			hp--;
			xSlot++;
			ySlot--;
			return;
		}
	}

}
