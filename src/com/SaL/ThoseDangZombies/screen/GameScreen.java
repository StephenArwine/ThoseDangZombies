package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.*;
import com.SaL.ThoseDangZombies.entity.Player;
import com.SaL.ThoseDangZombies.level.*;
import com.SaL.ThoseDangZombies.level.maps.*;

public class GameScreen extends Screen {
	private Level level;
	public static Player player;
	public static Camera camera = new Camera(ThoseDangZombies.WIDTH,
			ThoseDangZombies.HEIGHT);

	public GameScreen() {
		level = new SpawnRoom("/maps/spawnroom.png",this);
		player = new Player(level);
		level.add(player);
	}

	public void render(Graphics g) {
		g.drawImage(Draw.bg, 0, 0, null);
		camera.x = (player.x - (camera.width / 2));
		camera.y = (player.y - (camera.height / 2));

		level.render(g, camera);
	//	player.render(g, camera);
		level.overrender(g, camera);
	}

	public void update(Input input) {
		level.update();
		player.input(input);
        if (!input.oldButtons[Input.ESCAPE] && input.buttons[Input.ESCAPE]) {
            setScreen(new MenuScreen(this));
            return;
        }

	}
	
    public void popup(int id) {
        setScreen(new PopUpScreen(this, id));
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

	public static void renderplayer(int xpos, int ypos, int x, int y, Graphics g) {
		
		g.drawImage(Draw.player[xpos][ypos], (x-camera.x), (y-camera.y), null);

	}
}
