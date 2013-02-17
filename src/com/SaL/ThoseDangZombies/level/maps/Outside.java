package com.SaL.ThoseDangZombies.level.maps;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.SaL.ThoseDangZombies.entity.ClickEvent;
import com.SaL.ThoseDangZombies.entity.Entity;
import com.SaL.ThoseDangZombies.entity.Events;
import com.SaL.ThoseDangZombies.level.Level;
import com.SaL.ThoseDangZombies.level.tile.towntiles.TownTiles;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Outside extends Level {
	private String[] msg;


	public Outside(GameScreen screen) {
		super(screen);
				
	}
	protected void loadLevel() {
		String path = "/maps/outside.png";
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
		
		if (xLoc == 5 && yLoc == 10) {
			msg = new String[] { "Going Outside!..." };
			TownTiles.HouseDoor.Register(xLoc, yLoc);
			special = true;
		}
		if (auto) {
			add(new Events(xLoc, yLoc, msg, special));
		} else {
			add(new ClickEvent(xLoc, yLoc, msg, special));
		}
		
	}
	
	public void specials(int xSpot, int ySpot,Entity e) {
		

		if (xSpot == 5 && ySpot == 10) {
			GameScreen.DownStairs.xSpawn = 6;
			GameScreen.DownStairs.ySpawn = 1;
			Level newlevel = GameScreen.DownStairs;

			screen.levelswap(newlevel);

		}

	}


}
