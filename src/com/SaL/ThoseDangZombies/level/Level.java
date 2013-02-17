package com.SaL.ThoseDangZombies.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.SaL.ThoseDangZombies.entity.*;
import com.SaL.ThoseDangZombies.level.tile.Tile;
import com.SaL.ThoseDangZombies.level.tile.indoortiles.IndoorTiles;
import com.SaL.ThoseDangZombies.level.tile.towntiles.TownTiles;
import com.SaL.ThoseDangZombies.screen.GameScreen;

public class Level {

	public int[] tiles;
	public Tile[][] world;
	public boolean[][] Solid;
	public boolean[][] OverTiles;
	public int width, height;
	public int xSpawn, ySpawn;
	public List<Entity> entities;
	public List<Entity>[][] entityMap;
	public Player player;
	public GameScreen screen;

	public Level(GameScreen screen) {

		this.screen = screen;
		loadLevel();

	}

	protected void loadLevel() {

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
		for (int i = 0; i <= entities.size() - 1; i++) {
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
	protected void getTiles() {
		entityMap = new ArrayList[width][height];
		Tile.Solid = new boolean[width][height];
		Tile.OverTiles = new boolean[width][height];
		Tile.world = new Tile[width][height];
		Tile.loc = new Tile[width][height];
		entities = new ArrayList<Entity>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				entityMap[x][y] = new ArrayList<Entity>();

			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x + y * width] == 0xFFB200FF) {
					// xSpawn = x;
					// ySpawn = y;
					events(x, y);
					IndoorTiles.WoodFloor.Register(x, y);
				}
				if (tiles[x + y * width] == 0xFFFFFFFF)
					IndoorTiles.WoodFloor.Register(x, y);
				if (tiles[x + y * width] == 0xFF808077) {
					IndoorTiles.WallNorth.Register(x, y);
					add(new StaticEntity(x, y, 8, 8, 8, 8));
				}
				if (tiles[x + y * width] == 0xFF808079)
					IndoorTiles.WallWest.Register(x, y);
				if (tiles[x + y * width] == 0xFFFF0100)
					IndoorTiles.Blood.Register(x, y);
				if (tiles[x + y * width] == 0xFFFF0010)
					IndoorTiles.Rug.Register(x, y);
				if (tiles[x + y * width] == 0xFF808080)
					IndoorTiles.WallEast.Register(x, y);
				if (tiles[x + y * width] == 0xFF808069)
					IndoorTiles.WallSouthWest.Register(x, y);
				if (tiles[x + y * width] == 0xFF808068)
					IndoorTiles.WallSouthEast.Register(x, y);
				if (tiles[x + y * width] == 0xFF0010FF){
					IndoorTiles.BookShelf.Register(x, y);
					add(new StaticEntity(x,y,4,5,12,8));
					add(new StaticEntity(x+1,y,4,5,3,8));

				}
				if (tiles[x + y * width] == 0xFF0094FF) {
					IndoorTiles.ArmorRack.Register(x, y);
					add(new StaticEntity(x, y, 6, 6, 8, 0));

				}
				if (tiles[x + y * width] == 0xFFFF000F) {
					IndoorTiles.Barrel.Register(x, y);
					add(new StaticEntity(x, y, 7, 4, 8, 8));

				}
				if (tiles[x + y * width] == 0xFFFFD800)
					IndoorTiles.TorchN.Register(x, y);
				
				if (tiles[x + y * width] == 0xFFFF0000) {
					add(new StaticEntity(x, y, 5, 8, 6, 7));
					add(new StaticEntity(x, y - 1, 5, 8, 6, 8));
					IndoorTiles.Bed.Register(x, y);
				}
				if (tiles[x + y * width] == 0xFF73410F) {
					IndoorTiles.ChairSouth.Register(x, y);
					add(new StaticEntity(x, y, 5, 5, 6, 5));
				}
				if (tiles[x + y * width] == 0xFF73412F) {

					add(new StaticEntity(x, y, 5, 8, 8, 5));
					add(new StaticEntity(x + 1, y, 5, 8, 8, 5));
					IndoorTiles.Table.Register(x, y);
				}
				if (tiles[x + y * width] == 0xFF73411F) {
					IndoorTiles.ChairWest.Register(x, y);
					add(new StaticEntity(x, y, 5, 5, 5, 5));
				}
				if (tiles[x + y * width] == 0xFF303030) {
					IndoorTiles.WoodFloor.Register(x, y);
					events(x, y);
				}
				if (tiles[x + y * width] == 0xFF0000FF) {
					IndoorTiles.WoodFloor.Register(x, y);
					add(new TestNPC(x, y));
				}

				if (tiles[x + y * width] == 0xFF808072) {
					IndoorTiles.Axe.Register(x, y);
					add(new StaticEntity(x, y, 5, 5, 0, -1));
					
				}
				
				
				// outside tiles
				
				if (tiles[x + y * width] == 0xFFECECEC)
					TownTiles.Grass.Register(x, y);
				if (tiles[x + y * width] == 0xFF8D8D8D) {
					TownTiles.HouseCenter.Register(x, y);
					add(new StaticEntity(x, y, 8, 8, 8, 8));	
				}
				
				if (tiles[x + y * width] == 0xFF888888) {
					TownTiles.HouseLeft.Register(x, y);
					add(new StaticEntity(x, y, 8, 8, 8, 8));
				}
				
				if (tiles[x + y * width] == 0xFF888887) {
					TownTiles.HouseRight.Register(x, y);
					add(new StaticEntity(x, y, 8, 8, 8, 8));
				}				
				if (tiles[x + y * width] == 0xFFFF0200)
					TownTiles.OutsideBlood.Register(x, y);
				
				if (tiles[x + y * width] == 0xFFAD9278) {
					TownTiles.HouseRoof.Register(x, y);
				//	add(new StaticEntity(x, y, 8, 8, 8, 8));
				}			
				
				
			}
		}
		world = Tile.world;
		Solid = Tile.Solid;
		OverTiles = Tile.OverTiles;

	}

	public void update() {

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			int xSlotOld = e.xSpot;
			int ySlotOld = e.ySpot;
			if (!e.removed)
				e.update();
			e.xSpot = (e.x / 16);
			e.ySpot = (e.y / 16);
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

		e.xSpot = (e.x / 16);
		e.ySpot = (e.y / 16);
		if (e.xSpot >= 0 && e.ySpot >= 0 && e.xSpot < width && e.ySpot < height) {
			entityMap[e.xSpot][e.ySpot].add(e);
		}
	}

	public void CollisionCheck(int x, int y, int xa, int ya, int h, int w,
			Entity ee) {

		int xp = x + xa;
		int yp = y + ya;

		move: {
			java.util.List<Entity> entities = getEntities(ee);
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				if ((x + (w * xa) + xa) == (e.x - (xa * e.w))) {
					if ((y - h) <= (e.y + e.h) && (y + h) >= (e.y - e.h)) {
						ee.walking = false;
						break move;
					}
				}
				if ((y + (h * ya) + ya) == (e.y - (ya * e.h))) {
					if ((x - w) <= (e.x + e.w) && (x + w) >= (e.x - e.w)) {
						ee.walking = false;
						break move;
					}

				}
			}

			if (Solid[(xa * w) + xp >> 4][(ya * h) + yp >> 4]) {
				ee.walking = false;
				break move;
			}
			ee.x += xa;
			ee.y += ya;

		}

	}

	public void popup(Entity e) {

		screen.popup(e);
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
						if (e != ee)
							hits.add(e);
					}
				}
			}
		return hits;
	}

	public void events(int xLoc, int yLoc) {

	}

	public void specials(int xSpot, int ySpot, Entity e) {
		

	}

}
