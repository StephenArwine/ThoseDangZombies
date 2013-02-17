package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;
import java.util.Random;

import com.SaL.ThoseDangZombies.level.Camera;
import com.SaL.ThoseDangZombies.level.Level;

public class Entity {

	public int x, y;
	public int w = 10, h = 10;
	public Level level;
	public int xSpot, ySpot;
	public boolean removed = false;
	public int dir = 0;
	public int ya, xa;
	public int yp = 0, xp = 0;
	protected static Random random = new Random();
	public boolean special,interactable;
	public boolean walking;
	public boolean attacking;
	public boolean attackable = false;
	public String[] msg;
	public int hp = 5;
	public int hitpower = -1;
	public String OverHead = "";
	public boolean hit,negative;
	public boolean checkattack = false;

	

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

	public void remove() {

		removed = true;

	}

	public void hit(int hitpower) {

	}
	public void interaction(){
		
	}

	public void checkattack(Entity ee) {
		java.util.List<Entity> entities = level.getEntities(this);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			switch (dir) {
			case 0:
				if (e.xSpot == ee.xSpot && e.ySpot == ee.ySpot - 1
						&& e.attackable) {
					System.out.println("hit");
					e.hit(ee.hitpower);

				}

				break;
			case 1:
				if (e.xSpot == ee.xSpot + 1 && e.ySpot == ee.ySpot
						&& e.attackable) {
					System.out.println("hit");
					e.hit(ee.hitpower);

				}
				break;
			case 2:
				if (e.xSpot == ee.xSpot && e.ySpot == ee.ySpot + 1
						&& e.attackable) {
					e.hit(ee.hitpower);
					System.out.println("hit");

				}
				break;
			case 3:
				if (e.xSpot == ee.xSpot - 1 && e.ySpot == ee.ySpot
						&& e.attackable) {
					System.out.println("hit");
					e.hit(ee.hitpower);

				}
				break;
			}

		}
	}

}
