package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;
import java.util.Random;

import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.level.Level;

public class Entity {
	public int x, y;
	public int w = 10, h = 10;
	protected Level level;
	public int xSpot, ySpot;
	public boolean removed = false;
	public int dir = 0;
	public int ya, xa;
	protected static Random random = new Random();
	public int anim = 0;

	public boolean interactsWithWorld = false;

	public void init(Level level) {
		this.level = level;
	}

	public void move(int xa, int ya) {

		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;
		level.CollisionCheck(x, y, xa, ya, h, w, this);
	}

	public void render(Graphics g, Camera camera) {

	}

	public void update() {

	
	}

	public void outOfBounds() {
		if (y < 0)
			return;
		remove();
	}

	private void remove() {
		removed = true;

	}

}
