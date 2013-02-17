package com.SaL.ThoseDangZombies;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Draw {
	public static BufferedImage bg = scale(load("/background.png"), 8);
	public static BufferedImage[][] string = split(load("/string.png"), 6, 6);
    public static BufferedImage[][] tiles = split(load("/textures/tiles.png"), 16, 16);
    public static BufferedImage[][] tilesflip = mirrorsplit(load("/textures/tiles.png"), 16, 16);
    public static BufferedImage[][] player = split(load("/textures/player.png"), 16, 18);
    public static BufferedImage[][] playerAttacking = split(load("/textures/attackstance.png"), 16, 18);
	public static BufferedImage[][] redstring = split(load("/redstring.png"), 6, 6);
	public static BufferedImage[][] greenstring = split(load("/greenstring.png"), 6, 6);

    
    
	public static BufferedImage load(String name) {
		try {
			BufferedImage org = ImageIO.read(Draw.class.getResource(name));
			BufferedImage res = new BufferedImage(org.getWidth(),
					org.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = res.getGraphics();
			g.drawImage(org, 0, 0, null, null);
			g.dispose();
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    private static BufferedImage[][] mirrorsplit(BufferedImage src, int xs, int ys) {
        int xSlices = src.getWidth() / xs;
        int ySlices = src.getHeight() / ys;
        BufferedImage[][] res = new BufferedImage[xSlices][ySlices];
        for (int x = 0; x < xSlices; x++) {
            for (int y = 0; y < ySlices; y++) {
                res[x][y] = new BufferedImage(xs, ys, BufferedImage.TYPE_INT_ARGB);
                Graphics g = res[x][y].getGraphics();
                g.drawImage(src, xs, 0, 0, ys, x * xs, y * ys, (x + 1) * xs, (y + 1) * ys, null);
                g.dispose();
            }
        }
        return res;
    }

	private static BufferedImage scale(BufferedImage src, int scale) {
		int w = src.getWidth() * scale;
		int h = src.getHeight() * scale;
		BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = res.getGraphics();
		g.drawImage(src.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING), 0,
				0, null);
		g.dispose();
		return res;
	}

	private static BufferedImage[][] split(BufferedImage src, int xs, int ys) {
		int xSlices = src.getWidth() / xs;
		int ySlices = src.getHeight() / ys;
		BufferedImage[][] res = new BufferedImage[xSlices][ySlices];
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				res[x][y] = new BufferedImage(xs, ys,
						BufferedImage.TYPE_INT_ARGB);
				Graphics g = res[x][y].getGraphics();
				g.drawImage(src, -x * xs, -y * ys, null);
				g.dispose();
			}
		}
		return res;
	}

}
