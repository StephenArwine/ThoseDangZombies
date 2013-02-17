package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.*;
import com.SaL.ThoseDangZombies.entity.*;

import com.SaL.ThoseDangZombies.level.*;
import com.SaL.ThoseDangZombies.level.maps.*;

public class GameScreen extends Screen {
	Level level;
	public static Level SpawnRoom;
	public static Level DownStairs;
	public static Level Outside;
	public static Camera camera = new Camera(ThoseDangZombies.WIDTH,
			ThoseDangZombies.HEIGHT);

	public GameScreen() {

		DownStairs = new DownStairs(this);
		SpawnRoom = new SpawnRoom(this);
		Outside = new Outside(this);
		
		this.level = SpawnRoom;
		level.player = new Player(level.xSpawn, level.ySpawn);
		level.add(level.player);
	}

	public GameScreen(Level newlevel) {

		this.level = newlevel;
		level.screen = this;



	}

	public void render(Graphics g) {
		g.drawImage(Draw.bg, 0, 0, null);
		camera.x = (level.player.x - (camera.width / 2));
		camera.y = (level.player.y - (camera.height / 2));

		level.render(g, camera);
		// player.render(g, camera);
		level.overrender(g, camera);
	}

	public void levelswap(Level newlevel) {
		
		level.player.remove();
		newlevel.player = new Player(newlevel.xSpawn, newlevel.ySpawn);
		newlevel.add(newlevel.player);
		setScreen(new GameScreen(newlevel));

		
	}

	public void update(Input input) {
		level.update();
		level.player.input(input);
		if (!input.oldButtons[Input.ESCAPE] && input.buttons[Input.ESCAPE]) {
			setScreen(new MenuScreen(this));
			return;
		}

	}

	public void popup(Entity e) {
		setScreen(new PopUpScreen(this, e));
	}

	public static void rendertile(int xpos, int ypos, int x, int y, Graphics g) {
		g.drawImage(Draw.tiles[xpos][ypos], (x << 4) - camera.x, (y << 4)
				- camera.y, null);

	}

	public static void rendertilefliped(int xpos, int ypos, int x, int y,
			Graphics g) {
		g.drawImage(Draw.tilesflip[xpos][ypos], (x << 4) - camera.x, (y << 4)
				- camera.y, null);

	}

	public void renderplayer(int xpos, int ypos, int x, int y, Graphics g,
			int type) {
		if (type == 1) {
			g.drawImage(Draw.player[xpos][ypos], (x - camera.x),
					(y - camera.y), null);
		}
		if (type == 2) {
			g.drawImage(Draw.playerAttacking[xpos][ypos], (x - camera.x),
					(y - camera.y), null);
		}

	}

	public void damagedisplay(String damage, int x, int y, Graphics g,
			boolean negative) {
		DamageString(damage, g, x - camera.x, y - camera.y, negative);
	}
}
