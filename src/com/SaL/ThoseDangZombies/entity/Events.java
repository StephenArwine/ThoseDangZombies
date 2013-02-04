package com.SaL.ThoseDangZombies.entity;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.level.Camera;


public class Events extends Entity {
    public int id;
    public boolean auto = false;

    public Events(int x, int y, int id) {
        this.x = x*16;
        this.y = y*16;
        this.w = 10;
        this.h = 10;
        xa = ya = 0;
        this.id = id;
        auto = id == 1;
        if (id==2) auto = true;
    }
    public void update() {
        java.util.List<Entity> entities = level.getEntities(this);
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e instanceof Player) {
                Player player = (Player) e;
                player.event(this);
            }
        }
    }

    public void render(Graphics g, Camera camera) {
    }

}
