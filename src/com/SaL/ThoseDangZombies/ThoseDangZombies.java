package com.SaL.ThoseDangZombies;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.SaL.ThoseDangZombies.screen.*;

public class ThoseDangZombies extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 168;
	public static int scale = 3;
	public static String title = "ThoseDangZombies";
	private JFrame frame;
	public static int anim = 0;

	private boolean running = false;
	private Screen screen;
	private Input input = new Input();
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	public ThoseDangZombies() {

		Dimension size = new Dimension(WIDTH * scale, HEIGHT * scale);
		setPreferredSize(size);
		frame = new JFrame();
        this.addKeyListener(this);


	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void setScreen(Screen newScreen) {
		if (screen != null)
			screen.removed();
		screen = newScreen;
		if (screen != null)
			screen.init(this);
	}

	public void run() {

		setScreen(new TitleScreen());

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			Graphics g = image.getGraphics();

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				screen.update(input);
                input.tick();
				updates++;
				delta--;
				
				if (anim < 30)
					anim++;
				else
					anim = 0;
			}
			render(g);
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + "ups, " + +frames
						+ " fps");
				updates = 0;
				frames = 0;
			}
		}

	}

	private void render(Graphics g) {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.render(g);

		g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
    public void keyPressed(KeyEvent ke) {
        input.set(ke.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent ke) {
        input.set(ke.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent ke) {
    }

	public static void main(String[] args) {
		ThoseDangZombies thosedangzombies = new ThoseDangZombies();
		thosedangzombies.frame.setResizable(false);
		thosedangzombies.frame.setTitle(ThoseDangZombies.title);
		thosedangzombies.frame.add(thosedangzombies);
		thosedangzombies.frame.pack();
		thosedangzombies.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thosedangzombies.frame.setLocationRelativeTo(null);
		thosedangzombies.frame.setVisible(true);

		thosedangzombies.start();
	}

}
