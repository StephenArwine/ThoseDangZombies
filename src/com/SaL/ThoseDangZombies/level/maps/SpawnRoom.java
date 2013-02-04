package com.SaL.ThoseDangZombies.level.maps;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.SaL.ThoseDangZombies.level.Level;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class SpawnRoom extends Level{
		
		public SpawnRoom(String path, GameScreen screen) {

			super(path, screen);
		}

		protected void loadLevel(String path) {
			Level.xSpawn = 6;
			Level.ySpawn = 4;
//			Level.levelType = IndoorTiles.woodfloor;

			try {
				BufferedImage image = ImageIO.read(SpawnRoom.class.getResource(path));
				SpawnRoom.width = image.getWidth();
				SpawnRoom.height= image.getHeight();
				tiles = new int[width * height];
				image.getRGB(0, 0, width, height, tiles, 0, width);

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Excetions! could not load level file!");
			}

		}

}
