package com.SaL.ThoseDangZombies.level.maps;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.SaL.ThoseDangZombies.entity.ClickEvent;
import com.SaL.ThoseDangZombies.entity.Entity;
import com.SaL.ThoseDangZombies.entity.Events;
import com.SaL.ThoseDangZombies.level.Level;
import com.SaL.ThoseDangZombies.level.tile.indoortiles.IndoorTiles;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class DownStairs extends Level {

	private String[] msg;

	public DownStairs(GameScreen screen) {
		super(screen);
	}
	


	protected void loadLevel() {
		String path = "/maps/downstairs.png";
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
		if (xLoc == 1 && yLoc == 4) {
			msg = new String[] { "Heading Upstairs!..." };
			IndoorTiles.StairUp.Register(xLoc, yLoc);
			special = true;
		}
		if (xLoc == 6 && yLoc == 0) {
			msg = new String[] { "Going Outside!..." };
			IndoorTiles.Door.Register(xLoc, yLoc);
			special = true;
		}

		if (auto) {
			add(new Events(xLoc, yLoc, msg, special));
		} else {
			add(new ClickEvent(xLoc, yLoc, msg, special));
		}
	}

	public void specials(int xSpot, int ySpot,Entity e) {

		if (xSpot == 1 && ySpot == 4) {
			GameScreen.SpawnRoom.xSpawn = 2;
			GameScreen.SpawnRoom.ySpawn = 4;
			Level newlevel = GameScreen.SpawnRoom;

			screen.levelswap(newlevel);

		}
		
		if (xSpot == 6 && ySpot == 0) {
			GameScreen.Outside.xSpawn = 5;
			GameScreen.Outside.ySpawn = 11;
			Level newlevel = GameScreen.Outside;

			screen.levelswap(newlevel);

		}

	}

}
