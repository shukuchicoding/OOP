package gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resource;

public class EnemyManager {
	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random rand;
	
	private List<Enemy> enemies;
	private MainCharacter mainCharacter;
	
	public EnemyManager(MainCharacter mainCharacter) {
		rand = new Random();
		cactus1 = Resource.getResourceImage("data/cactus1.png");
		cactus2 = Resource.getResourceImage("data/cactus2.png");
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
	}
	
	public void update() {
		for (Enemy e : enemies) {
			e.update();
		}
	}
	
	public void draw(Graphics g) {
		for (Enemy e : enemies) {
			e.draw(g);
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			mainCharacter.upScore();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	private Enemy createEnemy() {
		int type = rand.nextInt(2);
		if (type == 0) {
			return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		}
		else {
			return new Cactus(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		}
	}
	
	public boolean isCollision() {
		for (Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset () {
		enemies.clear();
		enemies.add(createEnemy());
	}
}
