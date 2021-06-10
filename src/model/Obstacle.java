package src.model;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * Présnte l'obstable item = fait apparaitre des obstacles sur
 */

public class Obstacle {

	private Point pos;
	public static ArrayList<Obstacle> obstacles = new ArrayList<>();
	private String[] paths = {"src/ressource/image/wall.png", "src/ressource/image/hole.png"};
	private Image image;
	private int x;
	private int y;
	private int nbAppleToEat = 3;
	private static boolean blocked = false;

	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void newObstacles(Snake snake, Item item, int amount) {
		Random rnd = new Random();
		Point p;

		for (int i = 0; i < amount; i++) {
			Obstacle obs = new Obstacle(x, y);
			do {
				p = new Point(rnd.nextInt(x), rnd.nextInt(y));
			} while (item.getPos().equals(p) || snake.getBody().contains(p) || snake.getHead().equals(p));
			obs.setPos(p);

			if (i % 3 == 0) {
				obs.setImage(paths[0]);
			} else {
				obs.setImage(paths[1]);
			}
			obstacles.add(obs);
		}
	}

	public Point getPos() {
		return new Point(pos);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(String path) {
		image = new Image(path);
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public ArrayList<Point> getPointsObstacles() {
		ArrayList<Point> obstacles = new ArrayList<>();
		for (Obstacle obs : this.getObstacles()) {
			obstacles.add(obs.getPos());
		}
		return obstacles;
	}

	public void reset() {
		obstacles.clear();
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public int getNbAppleToEat() {
		return nbAppleToEat;
	}

	public void setNbAppleToEat(int nbAppleToEat) {
		this.nbAppleToEat = nbAppleToEat;
	}

	public void incrAppleToEat() {
		this.nbAppleToEat += 1;
	}

	public void resetNbAppleToEat() {
		this.nbAppleToEat = 3;
	}

	public static boolean getIsBlocked() {
		return blocked;
	}

	public static void setIsBlocked(boolean blocked) {
		Obstacle.blocked = blocked;
	}
}