package com.SaL.ThoseDangZombies.entity;

public class StaticEntity extends Entity {

	public StaticEntity(int x, int y, int h, int w, int xOffset, int yOffset) {
		this.w = w;
		this.h = h;
		this.x = x * 16 + xOffset;
		this.y = y * 16 + yOffset;

	}
}
