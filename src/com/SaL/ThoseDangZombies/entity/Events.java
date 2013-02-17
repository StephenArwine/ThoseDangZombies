package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.level.Camera;

public class Events extends Entity {


	public Events(int x, int y, String[] msg, boolean special) {
		this.x = x * 16;
		this.y = y * 16;
		this.w = 0;
		this.h = 0;
		xa = ya = 0;
		this.msg = msg;
		this.special = special;
	}

	public void update() {
		java.util.List<Entity> entities = level.getEntities(this);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e instanceof Player) {
				Player player = (Player) e;
				player.event(this);
				removed = true;
			}
		}
	}

	public void render(Graphics g, Camera camera) {
	}

}
