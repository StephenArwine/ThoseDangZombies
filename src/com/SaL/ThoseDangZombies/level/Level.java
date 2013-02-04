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

				if (tiles[x + y * width] == 0xFFFFFFFF)
					IndoorTiles.WoodFloor.Register(x, y);
				if (tiles[x + y * width] == 0xFF808077)
					IndoorTiles.WallNorth.Register(x, y);
				if (tiles[x + y * width] == 0xFF808079)
					IndoorTiles.WallWest.Register(x, y);
				if (tiles[x + y * width] == 0xFF808080)
					IndoorTiles.WallEast.Register(x, y);
				if (tiles[x + y * width] == 0xFF808069)
					IndoorTiles.WallSouthWest.Register(x, y);
				if (tiles[x + y * width] == 0xFF808068)
					IndoorTiles.WallSouthEast.Register(x, y);
				if (tiles[x + y * width] == 0xFF0094FF)
					IndoorTiles.ArmorRack.Register(x, y);
				if (tiles[x + y * width] == 0xFFFFD800)
					IndoorTiles.TorchN.Register(x, y);
				if (tiles[x + y * width] == 0xFFFF0000)
					IndoorTiles.Bed.Register(x, y);
				if (tiles[x + y * width] == 0xFF73410F)
					IndoorTiles.ChairSouth.Register(x, y);
				if (tiles[x + y * width] == 0xFF73412F)
					IndoorTiles.Table.Register(x, y);
				if (tiles[x + y * width] == 0xFF73411F)
					IndoorTiles.ChairWest.Register(x, y);
				if (tiles[x + y * width] == 0xFF303030) {
					IndoorTiles.WoodFloor.Register(x, y);
					add(new Events(x * 16, y * 16, 2));
				}
				if (tiles[x + y * width] == 0xFF0000FF) {
					IndoorTiles.WoodFloor.Register(x, y);
					add(new TestNPC(x, y));
				}

				if (tiles[x + y * width] == 0xFF808072)
					IndoorTiles.Axe.Register(x, y);
			}
		}

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			int xSlotOld = e.xSpot;
			int ySlotOld = e.xSpot;
			if (!e.removed)
				e.update();
			e.xSpot = (int) ((e.x + e.w / 2.0) / 16);
			e.ySpot = (int) ((e.y + e.h / 2.0) / 16);
			if (e.removed) {
				if (xSlotOld >= 0 && ySlotOld >= 0 && xSlotOld < width
						&& ySlotOld < height) {
					entityMap[xSlotOld][ySlotOld].remove(e);
				}
				entities.remove(i--);
			} else {

				if (e.xSpot != xSlotOld || e.ySpot != ySlotOld) {
					if (xSlotOld >= 0 && ySlotOld >= 0 && xSlotOld < width
							&& ySlotOld < height) {
						entityMap[xSlotOld][ySlotOld].remove(e);
					}
					if (e.xSpot >= 0 && e.ySpot >= 0 && e.xSpot < width
							&& e.ySpot < height) {
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

		e.xSpot = (int) ((e.x + e.w / 2.0) / 16);
		e.ySpot = (int) ((e.y + e.h / 2.0) / 16);
		if (e.xSpot >= 0 && e.ySpot >= 0 && e.xSpot < width && e.ySpot < height) {
			entityMap[e.xSpot][e.ySpot].add(e);
		}
	}

	public void CollisionCheck(int x, int y, int xa, int ya, int h, int w,
			Entity ee) {
		double tiny = 0.5;
		int centerX = x >> 4;
		int centerY = y >> 4;
		int centerXa = (int) ((x + (w * xa)) - tiny) >> 4;
		int centerYa = (int) ((y + (h * ya)) - tiny) >> 4;

		// x check
		if (!Solid[centerXa][centerY]) {
			ee.x += xa;
		}else{
			System.out.println("fail");
		}
		if (!Solid[centerX][centerYa]) {
			ee.y += ya;
		}

	}

	public void popup(Events events) {

		screen.popup(events.id - 1);
	}

	private List<Entity> hits = new ArrayList<Entity>();

	public List<Entity> getEntities(Entity ee) {

		hits.clear();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e.xSpot == ee.xSpot && e.ySpot == ee.ySpot) {
				hits.add(e);
			}
		}
		return hits;
	}
}
