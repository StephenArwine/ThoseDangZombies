package com.SaL.ThoseDangZombies.level.maps;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.SaL.ThoseDangZombies.entity.*;
import com.SaL.ThoseDangZombies.level.Level;
import com.SaL.ThoseDangZombies.level.tile.indoortiles.IndoorTiles;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class SpawnRoom extends Level {
	private String[] msg;

	public SpawnRoom(GameScreen screen) {

		super(screen);
		xSpawn = 11;
		ySpawn = 3;
	}

	protected void loadLevel() {
		String path = "/maps/spawnroom.png";
		try {
			BufferedImage image = ImageIO.read(SpawnRoom.class
					.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Excetions! could not load level file!");
		}
		getTiles();

	}

	public void events(int xLoc, int yLoc) {
		boolean auto = false;
		boolean special = false;
		if (xLoc == 11 && yLoc == 3) {
			auto = true;
			msg = new String[] { "There is something suspicious going on...",
					"the other bed hasnt been made", "but noone is in it!" };
			 //special = true;
		}
		// if (xLoc == 1 && yLoc == 4) {
		// msg = new String[] { "this is some stuff", "this is some more",
		// "lets try this with a really long line" };
		// auto = true;
		// special = true;
		// }
		if (xLoc == 4 && yLoc == 0) {
			msg = new String[] { "its locked..." };
			IndoorTiles.Door.Register(xLoc, yLoc);
		}
		if (xLoc == 1 && yLoc == 4) {
			msg = new String[] { "heading downstairs..." };
			IndoorTiles.StairDown.Register(xLoc, yLoc);
			special = true;
		}
		if (xLoc == 10 && yLoc == 1) {

			add(new Chest(xLoc, yLoc));
			return;
		}

		if (auto) {
			add(new Events(xLoc, yLoc, msg, special));
		} else {
			add(new ClickEvent(xLoc, yLoc, msg, special));
		}

	}

	public void specials(int xSpot, int ySpot, Entity e) {
		if (xSpot == 1 && ySpot == 4) {
			// level = new LongHall("/maps/longhall.png", screen);
			// screen.setScreen(new GameScreen(level));

		}

		if (xSpot == 1 && ySpot == 4) {
			GameScreen.DownStairs.xSpawn = 2;
			GameScreen.DownStairs.ySpawn = 4;
			Level newlevel = GameScreen.DownStairs;

			screen.levelswap(newlevel);

		}

		if (xSpot == 11 && ySpot == 3) {
			e.msg = new String[] {" Use the Aarow keys to move."};
			e.special = false;
			e.level.popup(e);

		}
	}
}
