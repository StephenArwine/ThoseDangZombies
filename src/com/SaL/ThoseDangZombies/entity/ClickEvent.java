package com.SaL.ThoseDangZombies.entity;

public class ClickEvent extends Entity {
	public ClickEvent(int x, int y, String[] msg, boolean special) {
		this.x = x * 16 + 8;
		this.y = y * 16 + 8;
		this.w = 8;
		this.h = 8;
		xa = ya = 0;
		this.msg = msg;
		this.special = special;
		interactable = true;

	}

	public void interaction() {
		level.popup(this);

	}
}
