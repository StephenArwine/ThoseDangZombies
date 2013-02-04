package com.SaL.ThoseDangZombies.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.SaL.ThoseDangZombies.entity.*;
import com.SaL.ThoseDangZombies.level.tile.Tile;
import com.SaL.ThoseDangZombies.level.tile.indoortiles.IndoorTiles;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Level {

	public static int[] tiles;
	public static Tile[][] world;
	public static boolean[][] Solid;
	public static boolean[][] OverTiles;
	public static int width, height;
	public static int xSpawn, ySpawn;
	public static Tile levelType;
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Entity>[][] entityMap;
	public Player player;
	private GameScreen screen;

	public Level(String path, GameScreen screen) {

		this.screen = screen;

		loadLevel(path);
		getTiles();

	}

	protected void loadLevel(String Path) {

	}

	public void render(Graphics g, Camera camera) {

		int x0 = camera.x >> 4;
		int x1 = (camera.x + camera.width + 16) >> 4;
		int y0 = camera.y >> 4;
		int y1 = (camera.y + camera.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				if (x >= 0 && y >= 0 && x < width && y < height) {
					if (world[x][y] != null) {
						world[x][y].render(x, y, g);

					}
				}
			}
		}
		for (int i = entities.size() - 1; i >= 0; i--) {
			Entity e = entities.get(i);
			e.render(g, camera);
		}

	}

	public void overrender(Graphics g, Camera camera) {

		int x0 = camera.x >> 4;
		int x1 = (camera.x + camera.width + 16) >> 4;
		int y0 = camera.y >> 4;
		int y1 = (camera.y + camera.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				if (x >= 0 && y >= 0 && x < width && y < height) {
					if (OverTiles[x][y]) {
						world[x][y].overrender(x, y, g);

					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void getTiles() {

		entityMap = new ArrayList[width][height];
		Solid = new boolean[width][height];
		OverTiles = new boolean[width][height];
		world = new Tile[width][height];
		Tile.loc = new Tile[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				entityMap[x][y] = new ArrayList<Entity>();

				if (tiles[x + y * width] == 0xFFFFFFFF) IndoorTiles.WoodFloor.Register(x, y);
				if (tiles[x + y * width] == 0xFF808077) IndoorTiles.WallNorth.Register(x, y);
				if (tiles[x + y * width] == 0xFF808079) IndoorTiles.WallWest.Register(x, y);
				if (tiles[x + y * width] == 0xFF808080) IndoorTiles.WallEast.Register(x, y);
				if (tiles[x + y * width] == 0xFF808069) IndoorTiles.WallSouthWest.Register(x, y);
				if (tiles[x + y * width] == 0xFF808068) IndoorTiles.WallSouthEast.Register(x, y);
				if (tiles[x + y * width] == 0xFF0094FF) IndoorTiles.ArmorRack.Register(x, y);
				if (tiles[x + y * width] == 0xFFFFD800) IndoorTiles.TorchN.Register(x, y);
				if (tiles[x + y * width] == 0xFFFF0000) IndoorTiles.Bed.Register(x, y);
				if (tiles[x + y * width] == 0xFF73410F) IndoorTiles.ChairSouth.Register(x, y);
				if (tiles[x + y * width] == 0xFF73412F) IndoorTiles.Table.Register(x, y);
				if (tiles[x + y * width] == 0xFF73411F) IndoorTiles.ChairWest.Register(x, y);
				if (tiles[x + y * width] == 0xFF303030) {
					IndoorTiles.WoodFloor.Register(x, y);
					//				add(new Events(x, y, 2));
				}
				if (tiles[x + y * width] == 0xFF0000FF) {
					IndoorTiles.WoodFloor.Register(x, y);
					add(new TestNPC(x, y));
				}

				if (tiles[x + y * width] == 0xFF808072) IndoorTiles.Axe.Register(x, y);
			}
		}

	}

	public void update() {

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			int xSlotOld = e.xSpot;
			int ySlotOld = e.ySpot;
			if (!e.removed) e.update();
			e.xSpot = (e.x / 16);
			e.ySpot = (e.y / 16);
			if (e.removed) {
				if (xSlotOld >= 0 && ySlotOld >= 0 && xSlotOld < width && ySlotOld < height) {
					entityMap[xSlotOld][ySlotOld].remove(e);
				}
				entities.remove(i--);
			} else {

				if (e.xSpot != xSlotOld || e.ySpot != ySlotOld) {
					if (xSlotOld >= 0 && ySlotOld >= 0 && xSlotOld < width && ySlotOld < height) {
						entityMap[xSlotOld][ySlotOld].remove(e);
					}
					if (e.xSpot >= 0 && e.ySpot >= 0 && e.xSpot < width && e.ySpot < height) {
						entityMap[e.xSpot][e.ySpot].add(e);
					} else {
						e.outOfBounds();
					}

				}
			}
		}
	}

	public void add(Entity e) {

		entities.add(e);
		e.init(this);

		e.xSpot = (e.x / 16);
		e.ySpot = (e.y / 16);
		if (e.xSpot >= 0 && e.ySpot >= 0 && e.xSpot < width && e.ySpot < height) {
			entityMap[e.xSpot][e.ySpot].add(e);
		}
	}

	public void CollisionCheck(int x, int y, int xa, int ya, int h, int w, Entity ee) {

		int centerX = x >> 4;
		int centerY = y >> 4;
		int centerXa = (int) ((x + (w * xa))) >> 4;
		int centerYa = (int) ((y + (h * ya))) >> 4;

		move: {
			java.util.List<Entity> entities = getEntities(ee);
			for (int i = 0; i < entities.size(); i++) {
				System.out.println("hit");

				if (entities.get(i).xSpot == centerXa && entities.get(i).ySpot == centerYa) {
					System.out.println("hit");
					break move;
				}
			}
			if (!Solid[centerXa][centerY]){
				ee.x += xa;
			}

			if (!Solid[centerX][centerYa]){
				ee.y += ya;
			}
		
		}
	}

	public void popup(Events events) {

		screen.popup(events.id - 1);
	}

	private List<Entity> hits = new ArrayList<Entity>();

	public List<Entity> getEntities(Entity ee) {

		hits.clear();
		int x0 = ee.xSpot - 1;
		int y0 = ee.ySpot - 1;
		int x1 = ee.xSpot + 1;
		int y1 = ee.ySpot + 1;
		for (int x = x0; x <= x1; x++)
			for (int y = y0; y <= y1; y++) {
				if (x >= 0 && y >= 0 && x < width && y < height) {
					List<Entity> es = entityMap[x][y];
					for (int i = 0; i < es.size(); i++) {
						Entity e = es.get(i);
						if (e != ee) hits.add(e);
					}
				}
			}
		return hits;
	}
}
