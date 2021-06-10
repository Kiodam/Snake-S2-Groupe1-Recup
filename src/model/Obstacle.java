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
	private String[] paths = {"src/ressource/image/Food/wall.png", "src/ressource/image/Food/hole.png"};
	private Image image;
	private int x;
	private int y;
	private int nbObstacleItemToEat = 3;
	private static boolean blocked = false;

	/**
	 * @param x largeur de la fenêtre
	 * @param y hauteur de la fenêtre
	 */
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param snake position du snake pour ne pas apparaître sur le corps
	 * @param item positon de l'item pour ne pas apparaître sur le corps
	 * @param amount nombre d'obstacle à créer
	 */
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

	/**
	 * @return la position d'un obstacle
	 */
	public Point getPos() {
		return new Point(pos);
	}

	/**
	 * @return l'image de l'item
	 */
	public Image getImage() {
		return image;
	}

	public void setImage(String path) {
		image = new Image(path);
	}

	/**
	 * @return la liste des obstacles
	 */
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	/**
	 * @return la position de chaque obstacle
	 */
	public ArrayList<Point> getPointsObstacles() {
		ArrayList<Point> obstacles = new ArrayList<>();
		for (Obstacle obs : this.getObstacles()) {
			obstacles.add(obs.getPos());
		}
		return obstacles;
	}

	/**
	 * enève tous les obstacles
	 */
	public void reset() {
		obstacles.clear();
	}

	/**
	 * définir la position du point
	 * @param pos position à affecter
	 */
	public void setPos(Point pos) {
		this.pos = pos;
	}

	/**
	 * @return le nombre d'item mangé
	 */
	public int getNbObstableItemToEat() {
		return nbObstacleItemToEat;
	}

	/**
	 * incrémentation
	 */
	public void incrAppleToEat() {
		this.nbObstacleItemToEat += 1;
	}

	public void resetNbObstacleItemToEat() {
		this.nbObstacleItemToEat = 3;
	}

	public static boolean getIsBlocked() {
		return blocked;
	}

	public static void setIsBlocked(boolean blocked) {
		Obstacle.blocked = blocked;
	}
}